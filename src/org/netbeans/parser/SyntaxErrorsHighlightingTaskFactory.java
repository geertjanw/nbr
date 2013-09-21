/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.parser;

import java.util.Collection;
import java.util.Collections;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.spi.SchedulerTask;
import org.netbeans.modules.parsing.spi.TaskFactory;

/**
 *
 * @author constantin
 */
public class SyntaxErrorsHighlightingTaskFactory extends TaskFactory{

    @Override
    public Collection<? extends SchedulerTask> create(Snapshot snpsht) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return Collections.singleton (new SyntaxErrorsHighlightingTask ());
    }
    
}
