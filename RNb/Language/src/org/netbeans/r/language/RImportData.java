package org.netbeans.r.language;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import org.netbeans.r.options.RconfigurationPanel;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
import org.openide.util.NbPreferences;

@ActionID(
        category = "Build",
        id = "org.netbeans.r.language.RImportData"
)
@ActionRegistration(
        iconBase = "org/netbeans/r/language/Import.jpg",
        displayName = "#CTL_RImportData"
)
@Messages("CTL_RImportData=Import data")
@ActionReference(path = "ImportData")
public final class RImportData implements ActionListener {

    private final List<DataObject> context;

    public RImportData(List<DataObject> context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        for (DataObject dataObject : context) {
            String executableFilePath = NbPreferences.forModule(RconfigurationPanel.class).get("RConfiguration.path", "");
            File file = new File(executableFilePath);
            
            

        }
    }
}
