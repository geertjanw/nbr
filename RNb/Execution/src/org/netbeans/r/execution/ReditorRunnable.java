package org.netbeans.r.execution;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.regex.Matcher;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.netbeans.r.options.RconfigurationPanel;
import org.netbeans.r.pdfviewer.PlotViewerTopComponent;
import org.openide.ErrorManager;
import org.openide.execution.NbProcessDescriptor;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.util.NbPreferences;
import org.openide.windows.IOColorLines;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
import org.openide.windows.OutputWriter;

/**
 *
 * @author constantin
 */
public class ReditorRunnable implements Runnable {

    private final DataObject dataObject;
    private String commandLineArgs;
    private String Rexecutable = NbPreferences.forModule(RconfigurationPanel.class).get("RConfiguration.path", "");
    private int executionStatus;
    private String fileExecuted;
    private final PlotViewerTopComponent win = PlotViewerTopComponent.findInstance();

    public ReditorRunnable(DataObject dataObject, String commandLineArgs) {
        this.dataObject = dataObject;
        this.commandLineArgs = commandLineArgs;
        win.close();
        JScrollPane scp = new JScrollPane();
        win.add(scp);
        win.setLayout(new BorderLayout());
        win.open();
    }

    @Override
    public void run() {
        FileObject fileObject = dataObject.getPrimaryFile();
        File file = FileUtil.toFile(fileObject);
        File fileToRemove  = new File(file.getParent().concat("/img/Rplots.pdf"));
        fileToRemove.delete();
        File pdffile = new File("Rplots.pdf");  //PDF file to be opened at the end of execution
        pdffile.renameTo(new File(file.getParent().concat("/img/Rplots.pdf")));

        InputOutput io = IOProvider.getDefault().getIO(file.getName() + "(run)", true);
        io.select();
        OutputWriter writer = io.getOut();
        OutputWriter errWriter = io.getErr();
        ProgressHandle myprogress = ProgressHandleFactory.createSystemHandle(file.getName() + "running", null);

        if (new File(getRexecutable()).exists()) {

            try {
                writer.reset();
                myprogress.start();
                //Execute the instruction and send to Output window
                processDataObject(writer, getRexecutable(), dataObject, io);
                //Test the status of the exection
                if (this.getExecutionStatus() == 0) {
                    IOColorLines.println(io, "EXECUTED SUCCESSFUL", Color.GREEN.darker());

                    if (pdffile.exists()) {

                        final JScrollPane scrollpane = new JScrollPane();
                        InputStream inputStream = Files.newInputStream(pdffile.toPath());
                        SwingController controller = new SwingController();
                        SwingViewBuilder factory = new SwingViewBuilder(controller);
                        controller.openDocument(inputStream, "Plot Result", pdffile.getPath());
                        JPanel viewerComponentPanel = factory.buildViewerPanel();
                        JPanel pn = new JPanel();
                        pn.add(scrollpane);
                        pn.setLayout(new BorderLayout());
                         
                        pn.add(viewerComponentPanel);
                        win.add(pn, BorderLayout.CENTER);                        
                        
                    }
                } else {
                    IOColorLines.println(io, "Revise your code please !", Color.RED.darker());
                }
                myprogress.finish();
            } catch (IOException ex) {
                ErrorManager.getDefault().notify(ErrorManager.WARNING, ex);
            } catch (InterruptedException ex) {
                ErrorManager.getDefault().notify(ErrorManager.WARNING, ex);
            }

        } else {
            errWriter.println("Executable not found. Please select a valid path in the Options panel (Tools->Options->R)");
        }
        writer.close();
        errWriter.close();
    }

    public String getCommandLineArgs() {
        return commandLineArgs;
    }

    public void setCommandLineArgs(String commandLineArgs) {
        this.commandLineArgs = commandLineArgs;
    }

    public int getExecutionStatus() {
        return executionStatus;
    }

    public void setExecutionStatus(int executionStatus) {
        this.executionStatus = executionStatus;
    }

    public String getFileExecuted() {
        return fileExecuted;
    }

    public void setFileExecuted(String fileExecuted) {
        this.fileExecuted = fileExecuted;
    }

    public String getRexecutable() {
        return Rexecutable;
    }

    public void setRexecutable(String Rexecutable) {
        this.Rexecutable = Rexecutable;
    }

    void processDataObject(final OutputWriter writer, final String executable, final DataObject dataObject, InputOutput io) throws InterruptedException, IOException {
        //final DataObject dataObject
        FileObject fileObject = dataObject.getPrimaryFile();
        File file = FileUtil.toFile(fileObject);
        NbProcessDescriptor rProcessDesc;
        if (getCommandLineArgs() != null) {
            rProcessDesc = new NbProcessDescriptor(executable, getCommandLineArgs() + "\"" + file.getAbsolutePath() + "\"");
        } else {
            rProcessDesc = new NbProcessDescriptor(executable, "\"" + file.getAbsolutePath() + "\"");
        }
        Process process = rProcessDesc.exec();

        ReadOutput(writer, dataObject, process.getInputStream(), process.getErrorStream(), io);
        process.waitFor();
        setExecutionStatus(process.exitValue());
        writer.flush();
    }

    static void ReadOutput(OutputWriter writer, DataObject dataObject, InputStream routputStream, InputStream rErrorStream, InputOutput io) throws IOException {
        ROutputListener listener = new ROutputListener(dataObject);
        StringBuilder ErrorOutput = new StringBuilder();
        try {
            BufferedReader rfileReader = new BufferedReader(new InputStreamReader(routputStream));
            BufferedReader rfileReaderError = new BufferedReader(new InputStreamReader(rErrorStream));
            String outputString = null;

            while ((outputString = rfileReader.readLine()) != null) {
                Matcher matcher = ROutputListener.PATTERN.matcher(outputString);
                if (matcher.matches()) {
                    writer.println(outputString, listener);
                } else {
                    writer.println(outputString);
                }
            }

            //Treate here the error
            while ((outputString = rfileReaderError.readLine()) != null) {
                Matcher matcher = ROutputListener.PATTERN.matcher(outputString);
                ErrorOutput = ErrorOutput.append(" . " + outputString);
                if (matcher.matches()) {
                    // System.out.println(outputString);
                    IOColorLines.println(io, outputString, listener, true, Color.RED.darker());
                    // writer.println(outputString, listener);
                } else {

                    IOColorLines.println(io, outputString, Color.red.darker());
                    //writer.println(outputString);
                }
            }

        } catch (IOException e) {
            StringBuffer exceptionBuffer = new StringBuffer();
            exceptionBuffer.append(e.getMessage());
            IOColorLines.println(io, exceptionBuffer, Color.ORANGE.darker());
            
        }
    }

}
