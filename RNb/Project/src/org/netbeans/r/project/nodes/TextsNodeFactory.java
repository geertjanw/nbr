package org.netbeans.r.project.nodes;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ChangeListener;
import org.netbeans.api.project.Project;
import org.netbeans.r.project.RProject;
import org.netbeans.spi.project.ui.support.NodeFactory;
import org.netbeans.spi.project.ui.support.NodeList;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;

/**
 *
 * @author constantin
 */
@NodeFactory.Registration(projectType = "org-netbeans-modules-r-project", position = 10)
public class TextsNodeFactory implements NodeFactory {

    @Override
    public NodeList<?> createNodes(Project prjct) {
        RProject p = prjct.getLookup().lookup(RProject.class);
        assert p != null;
        return new TextsNodeList(p);
    }

    private class TextsNodeList implements NodeList<Node> {

        RProject project;

        public TextsNodeList(RProject project) {
            this.project = project;
        }

        @Override
        public List<Node> keys() {
            FileObject textsFolder = project.getProjectDirectory().getFileObject("Files");
            List<Node> result = new ArrayList<Node>();
            if (textsFolder != null) {
                for (FileObject textsFolderFile : textsFolder.getChildren()) {
                    try {

                        result.add(DataObject.find(textsFolderFile).getNodeDelegate());

                    } catch (DataObjectNotFoundException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }

            }

            return result;
        }

        @Override
        public void addChangeListener(ChangeListener cl) {

        }

        @Override
        public void removeChangeListener(ChangeListener cl) {

        }

        @Override
        public Node node(Node k) {
            return new FilterNode(k);
        }

        @Override
        public void addNotify() {

        }

        @Override
        public void removeNotify() {

        }

    }

}
