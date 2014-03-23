package org.netbeans.r.samples;
import org.netbeans.spi.project.*;
import org.netbeans.spi.project.ui.PrivilegedTemplates;
@ProjectServiceProvider(projectType = "org-netbeans-modules-r-project", service = PrivilegedTemplates.class)
public class RPrivilegedTemplates implements PrivilegedTemplates {
    private static final String[] PRIVILEGED_NAMES = new String[] {
        "Templates/R/LineChart1.r",
        "Templates/R/LineChart2.r",
        "Templates/R/LineChart3.r",
        "Templates/R/LineChart4.r",
    };
    @Override
    public String[] getPrivilegedTemplates() {
        return PRIVILEGED_NAMES;
    }
}