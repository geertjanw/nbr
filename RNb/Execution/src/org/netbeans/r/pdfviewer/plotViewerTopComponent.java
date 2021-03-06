package org.netbeans.r.pdfviewer;

import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//org.netbeans.pdfviewer//plotViewer//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "plotViewerTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.netbeans.pdfviewer.plotViewerTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_plotViewerAction",
        preferredID = "plotViewerTopComponent"
)
@Messages({
    "CTL_plotViewerAction=plotViewer",
    "CTL_plotViewerTopComponent=Graphic",
    "HINT_plotViewerTopComponent=This is a plot window"
})
public final class plotViewerTopComponent extends TopComponent {

    private static plotViewerTopComponent instance;

    public plotViewerTopComponent() {
        initComponents();
        setName(Bundle.CTL_plotViewerTopComponent());
//        setToolTipText(Bundle.HINT_plotViewerTopComponent());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 637, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 448, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
       
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
        instance = null; 
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    public static plotViewerTopComponent findInstance() {
        if (instance == null) {
            instance = new plotViewerTopComponent();
            return instance;
        }
        return instance;
    }

}
