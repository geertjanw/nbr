package org.netbeans.r.language;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import org.netbeans.r.execution.ReditorRunnable;
import org.netbeans.r.options.RconfigurationPanel;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.loaders.DataObject;
import org.openide.util.NbBundle.Messages;
import org.openide.util.NbPreferences;
import org.openide.util.RequestProcessor;

@ActionID(
        category = "Debug",
        id = "org.netbeans.r.language.RunRFileAction"
)
@ActionRegistration(
        iconBase = "org/netbeans/r/language/Execute.jpg",
        displayName = "#CTL_RunRFileAction"
)
@Messages("CTL_RunRFileAction=Execute")

@ActionReference(path = "RFileExecution")
public final class RunRFileAction implements ActionListener {

    private final List<DataObject> context;
    private RequestProcessor processor = null;

    public RunRFileAction(List<DataObject> context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        String executableFilePath = NbPreferences.forModule(RconfigurationPanel.class).get("RConfiguration.path", "");
        File file = new File(executableFilePath);

        for (DataObject dataObject : context) {
            processor = new RequestProcessor("Exec", 1, true);
            if ("Rscript".equals(file.getName())) {
                processor.execute(new ReditorRunnable(dataObject, ""));
            } else {
                processor.execute(new ReditorRunnable(dataObject, " -q -f "));
            }
        }
    }
}
