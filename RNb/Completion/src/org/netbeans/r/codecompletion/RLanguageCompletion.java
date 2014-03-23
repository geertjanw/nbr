package org.netbeans.r.codecompletion;

import java.net.URL;
import javax.swing.Action;
import org.netbeans.spi.editor.completion.CompletionDocumentation;

/**
 *
 * @author Constantin Drabo
 */

public class RLanguageCompletion implements CompletionDocumentation {
    
    private  RLanguageCompletionItem item; 
    
    public RLanguageCompletion(RLanguageCompletionItem item) {
        this.item = item;
    }

    @Override
    public String getText() {       
       return item.getText();
    }

    @Override
    public URL getURL() {
        return null;
    }

    @Override
    public CompletionDocumentation resolveLink(String link) {
        return null;
    }

    @Override
    public Action getGotoSourceAction() {
     return null;
    }
    
}
