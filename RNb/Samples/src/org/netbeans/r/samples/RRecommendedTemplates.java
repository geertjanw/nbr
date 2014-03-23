package org.netbeans.r.samples;
import org.netbeans.spi.project.*;
import org.netbeans.spi.project.ui.RecommendedTemplates;
@ProjectServiceProvider(projectType = "org-netbeans-modules-r-project", service = RecommendedTemplates.class)
public class RRecommendedTemplates implements RecommendedTemplates {
    private static final String[] PRIVILEGED_NAMES = new String[] {
        "Templates/R",
    };
    @Override
    public String[] getRecommendedTypes() {
        return PRIVILEGED_NAMES;
    }
}