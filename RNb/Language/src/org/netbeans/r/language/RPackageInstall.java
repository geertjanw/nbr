package org.netbeans.r.language;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import org.netbeans.r.execution.ReditorRunnable;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
import org.openide.util.RequestProcessor;

@ActionID(
        category = "Bugtracking",
        id = "org.netbeans.r.language.RPackageInstall"
)
@ActionRegistration(
        iconBase = "org/netbeans/r/language/Packages.png",
        displayName = "#CTL_RPackageInstall"
)
@Messages("CTL_RPackageInstall=Install package")

@ActionReference(path = "InstallPackageAction")
public final class RPackageInstall implements ActionListener {

    private RequestProcessor processor = null;

    private final List<DataObject> context;

    public RPackageInstall(List<DataObject> context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        for (DataObject dataObject : context) {
             processor = new RequestProcessor("Exec", 1, true);
             processor.execute(new ReditorRunnable(dataObject, "R CMD INSTALL "));
        }
    }
}
