package org.netbeans.r.palette;

import java.awt.Image;
import java.util.Properties;
import org.openide.util.ImageUtilities;
import org.openide.util.Utilities;


/**
 *
 * @author constantin
 */
public class MyItemData {
    
    private Properties props; 
    private Image icon16; 
    private Image icon32; 
    
    public static final String PROP_ID= "id"; 
    public static final String PROP_NAME="displayname"; 
    public static final String PROP_COMMENT="comment"; 
    public static final String PROP_ICON16="icon16"; 
    public static final String PROP_ICON32="icon32";

    public MyItemData(Properties props) {
        this.props = props;
    }
    
    public String getId() {
        return props.getProperty(PROP_ID);
    }
    
    
    public String getDisplayName(){
        return props.getProperty(PROP_NAME);
    }
    
    public String getComment() {
        return props.getProperty(PROP_COMMENT);
    }
    
    public Image getSmallImage() {
        return icon16;
    }
    
    public Image getBigImage(){
        return icon32;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj instanceof MyItemData){
            return getId().equals(((MyItemData)obj).getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.props != null ? this.props.hashCode() : 0);
        hash = 71 * hash + (this.icon16 != null ? this.icon16.hashCode() : 0);
        hash = 71 * hash + (this.icon32 != null ? this.icon32.hashCode() : 0);
        return hash;
    }
    
    private void loadIcons(){
        String iconId = props.getProperty(PROP_ICON16);
        icon16  = ImageUtilities.loadImage(iconId);
        iconId  =  props.getProperty(PROP_ICON32);
        icon32  = ImageUtilities.loadImage(iconId);
    }
}
