package org.netbeans.r.navigator;

import java.awt.BorderLayout;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.swing.JComponent;
import org.netbeans.r.language.RfileTypeDataObject;
import org.netbeans.spi.navigator.NavigatorPanel;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.view.BeanTreeView;
import org.openide.filesystems.FileAttributeEvent;
import org.openide.filesystems.FileChangeListener;
import org.openide.filesystems.FileEvent;
import org.openide.filesystems.FileRenameEvent;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.BeanNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.Utilities;
import org.openide.windows.TopComponent;

/**
 *
 * @author constantin Drabo
 *
 */
@NavigatorPanel.Registration(mimeType = "text/x-r", displayName = "R file content")
public class RNavigatorPanel extends TopComponent implements NavigatorPanel, ExplorerManager.Provider {

    private final ExplorerManager manager = new ExplorerManager();

    public RNavigatorPanel() {
        setLayout(new BorderLayout());
        BeanTreeView view = new BeanTreeView();
        view.setRootVisible(true);
        add(view, BorderLayout.CENTER);
        AbstractNode node = new AbstractNode(Children.create(new RChildFactory(), true));
        node.setDisplayName("Root");
        manager.setRootContext(node);
    }

    @Override
    public String getDisplayName() {
        return "R file navigator";
    }

    @Override
    public String getDisplayHint() {
        return "hint";
    }

    @Override
    public JComponent getComponent() {
        return this;
    }

    @Override
    public void panelActivated(Lookup lkp) {
    }

    @Override
    public void panelDeactivated() {
    }

    @Override
    public Lookup getLookup() {
        return Lookup.EMPTY;
    }

    private class RChildFactory extends ChildFactory.Detachable<String> implements LookupListener {

        private Lookup.Result<RfileTypeDataObject> result = null;

        @Override
        protected void addNotify() {
            result = Utilities.actionsGlobalContext().lookupResult(RfileTypeDataObject.class);
            result.addLookupListener(this);

        }

        @Override
        protected void removeNotify() {
            result.removeLookupListener(this);
        }

        @Override
        protected boolean createKeys(List<String> list) {
            Collection<? extends RfileTypeDataObject> rfiles = result.allInstances();
            if (!rfiles.isEmpty()) {

                RfileTypeDataObject rtdo = rfiles.iterator().next();

                rtdo.getPrimaryFile().addFileChangeListener(new FileChangeListener() {

                    @Override
                    public void fileFolderCreated(FileEvent fe) {
                    }

                    @Override
                    public void fileDataCreated(FileEvent fe) {
                    }

                    @Override
                    public void fileChanged(FileEvent fe) {
                        refresh(true);
                    }

                    @Override
                    public void fileDeleted(FileEvent fe) {
                    }

                    @Override
                    public void fileRenamed(FileRenameEvent fre) {
                    }

                    @Override
                    public void fileAttributeChanged(FileAttributeEvent fae) {
                    }
                });

                try {
                    for (String s : rtdo.getPrimaryFile().asLines()) {
                        
                        if (!s.isEmpty()) {
                            if (!s.startsWith("#")) {
                                if (s.contains("#")) {
                                    int positionDollar = s.indexOf("#");
                                    s = s.substring(0, positionDollar);
                                }
                                list.add(s);
                            }
                        }
                    }

                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }

            }
            return true;
        }

        @Override
        protected Node createNodeForKey(String key) {
            BeanNode beanNode = null;
            try {
                beanNode = new BeanNode(key);
                beanNode.setDisplayName(key);
            } catch (IntrospectionException ex) {
                Exceptions.printStackTrace(ex);
            }
            return beanNode;
        }

        @Override
        public void resultChanged(LookupEvent le) {
            refresh(true);
        }

    }

    @Override
    public ExplorerManager getExplorerManager() {
        return manager;
    }

}
