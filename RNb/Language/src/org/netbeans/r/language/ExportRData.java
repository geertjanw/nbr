package org.netbeans.r.language;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Build",
        id = "org.netbeans.r.language.ExportRData"
)
@ActionRegistration(
        iconBase = "org/netbeans/r/language/Export.png",
        displayName = "#CTL_ExportRData"
)
@Messages("CTL_ExportRData=Export data")

@ActionReference(path = "ExportDataAction")
public final class ExportRData implements ActionListener {

    private final List<DataObject> context;

    public ExportRData(List<DataObject> context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        for (DataObject dataObject : context) {
            // TODO use dataObject
        }
    }
}
