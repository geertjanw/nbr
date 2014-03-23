package org.netbeans.r.project.panels;

import javax.swing.JComponent;
import javax.swing.JPanel;
import org.netbeans.r.project.panels.Bundle;
import org.netbeans.spi.project.ui.support.ProjectCustomizer;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;

/**
 *
 * @author constantin Drabo
 */
public class GeneralRProjectProperties implements ProjectCustomizer.CompositeCategoryProvider {

    private static final String GENERAL = "General";

    @ProjectCustomizer.CompositeCategoryProvider.Registration(
            projectType = "org-netbeans-project", position = 10)
    public static GeneralRProjectProperties createGeneral() {
        return new GeneralRProjectProperties();
    }

    @NbBundle.Messages("LBL_Config_General=General")
    @Override
    public ProjectCustomizer.Category createCategory(Lookup lkp) {
        return ProjectCustomizer.Category.create(GENERAL, Bundle.LBL_Config_General(), null);
    }

    @Override
    public JComponent createComponent(ProjectCustomizer.Category ctgr, Lookup lkp) {
      return new JPanel();
    }

}
