package org.netbeans.r.navigator;

import org.netbeans.r.language.RfileTypeDataObject;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;

/**
 *
 * @author constantin
 */
public class RFileNode  extends AbstractNode {

    public RFileNode(RfileTypeDataObject node, Children ch) {
        super(ch);
        setDisplayName(node.getName());
        String iconBase  = node.getName(); 
        if(iconBase != null)
            setIconBaseWithExtension(iconBase);
    }
    
}
