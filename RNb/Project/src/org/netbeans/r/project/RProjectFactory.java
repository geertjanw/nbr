package org.netbeans.r.project;

import java.io.IOException;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ProjectFactory;
import org.netbeans.spi.project.ProjectState;
import org.openide.filesystems.FileObject;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author constantin Drabo
 */
@ServiceProvider(service=ProjectFactory.class)
public class RProjectFactory implements ProjectFactory {
    
    public static final String PROJECT_FILE = "dummy.r";

    @Override
    public boolean isProject(FileObject fo) {
        return fo.getFileObject(PROJECT_FILE) != null ;
    }

    @Override
    public Project loadProject(FileObject fo, ProjectState ps) throws IOException {
       return isProject(fo) ? new RProject(fo,ps) : null;
    }

    @Override
    public void saveProject(Project prjct) throws IOException, ClassCastException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
    
    
}
