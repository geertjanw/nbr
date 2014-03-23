package org.netbeans.r.navigator;

import java.util.Arrays;
import java.util.List;
import org.netbeans.r.language.RfileTypeDataObject;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;

/**
 *
 * @author constantin
 */
public class RfileExplorerFactory extends ChildFactory<RfileTypeDataObject> {

    private RfileTypeDataObject rfile = null;

    public RfileExplorerFactory(RfileTypeDataObject rfile) {
        this.rfile = rfile;
    }
    
    @Override
    protected Node createNodeForKey(RfileTypeDataObject key) {
        return new RFileNode(key, Children.create(new RfileExplorerFactory(key), true));
    }

    @Override
    protected boolean createKeys(List<RfileTypeDataObject> list) {

        list.addAll(Arrays.asList(rfile));
        return true;
    }

}
