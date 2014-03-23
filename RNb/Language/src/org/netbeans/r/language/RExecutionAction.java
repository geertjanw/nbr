package org.netbeans.r.language;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import org.netbeans.r.execution.ReditorRunnable;
import org.netbeans.r.options.RconfigurationPanel;
import org.openide.loaders.DataObject;
import org.openide.util.NbPreferences;
import org.openide.util.RequestProcessor;

public final class RExecutionAction implements ActionListener {

    private final List<DataObject> context;
    private RequestProcessor processor = null;

    public RExecutionAction(List<DataObject> context) {
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
                processor.execute(new ReditorRunnable(dataObject, "-q -f "));
            }
        }

    }
}
