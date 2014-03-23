package org.netbeans.r.codecompletion;

import java.io.File;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author constantin Drabo
 * 
 */

public class HelpFileManager {
    
    File directory  ; 

    public HelpFileManager(File directory) {
        this.directory = directory;
    }

    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }
    
    List<File> htmlFileList() {
        String [] extensions  = new String[] {"html"};
        if(!directory.isDirectory()) {
            directory  = new File("./");
            JOptionPane.showMessageDialog(null, "You should configure the libraries path in Tools => Options => R Configurations");
        }
       
        List<File> files = (List<File>) FileUtils.listFiles(directory, extensions, true);
        return files;
        
        
    }   
    
}
