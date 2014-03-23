package org.netbeans.r.palette;

import java.io.IOException;
import javax.swing.Action;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.spi.palette.PaletteActions;
import org.netbeans.spi.palette.PaletteController;
import org.netbeans.spi.palette.PaletteFactory;
import org.openide.util.Lookup;
 
/**
 *
 * @author constantin
 */
public class RSourceFileLayerPaletteFactory {

    private static PaletteController palette = null;
   
//    @MimeRegistration(mimeType = "text/x-r", service = PaletteController.class)
    public static PaletteController createPalette() {

        try {
            if(palette == null) {
                palette = PaletteFactory.createPalette("RTools", new MyActions(), null, null);
            }
            return palette;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static final class MyActions extends PaletteActions {

        public Action[] getImportActions() {
            return null;
        }

        public Action[] getCustomPaletteActions() {
            return null;
        }

        public Action[] getCustomCategoryActions(Lookup lookup) {
            return null;
        }

        public Action[] getCustomItemActions(Lookup lookup) {
            return null;
        }

        public Action getPreferredAction(Lookup lookup) {
            return null;
        }
    }
}
