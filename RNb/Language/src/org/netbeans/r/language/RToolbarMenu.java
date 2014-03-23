package org.netbeans.r.language;

import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPopupMenu;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.awt.DropDownButtonFactory;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;
import org.openide.util.actions.Presenter;

@ActionID(
        category = "Build",
        id = "org.netbeans.r.language.RToolbarMenu"
)
@ActionRegistration(
        iconBase = "org/netbeans/r/language/RlogoMen.png",
        displayName = "#CTL_RToolbarMenu"
)
@ActionReferences({
    @ActionReference(path = "Menu/BuildProject", position = 0),
    @ActionReference(path = "Toolbars/Build")
})
@Messages("CTL_RToolbarMenu=Run R")
public final class RToolbarMenu extends AbstractAction implements Presenter.Toolbar {

    JPopupMenu popup = new JPopupMenu();
    final String RFILE_EXECUTION = "RFileExecution";
    final String DATA_IMPORT  = "ImportData";
    final String DATA_EXPORT  = "ExportDataAction";
    final String PACKAGE_INSTALL  = "InstallPackageAction";

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Component getToolbarPresenter() {
        for (Action a : Utilities.actionsForPath(RFILE_EXECUTION)) {
            popup.add(a);
        }
        
        for (Action b : Utilities.actionsForPath(DATA_IMPORT)) {
            popup.add(b);
        }
        
        for (Action c : Utilities.actionsForPath(DATA_EXPORT)) {
            popup.add(c);
        }
        
        for (Action d : Utilities.actionsForPath(PACKAGE_INSTALL)) {
            popup.add(d);
        }
        
        return DropDownButtonFactory.createDropDownButton(ImageUtilities.loadImageIcon("org/netbeans/r/language/Rlogo.png", false), popup);
    }

}
