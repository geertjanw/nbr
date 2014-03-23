package org.netbeans.r.codecompletion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.JToolTip;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import org.netbeans.api.editor.completion.Completion;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionResultSet;
import org.netbeans.spi.editor.completion.CompletionTask;
import org.netbeans.spi.editor.completion.support.AsyncCompletionQuery;
import org.netbeans.spi.editor.completion.support.AsyncCompletionTask;
import org.netbeans.spi.editor.completion.support.CompletionUtilities;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;

/**
 *
 * @author constantin Drabo
 * 
 */

public class RLanguageCompletionItem implements CompletionItem {

    private static final ImageIcon ICON = new ImageIcon(ImageUtilities.loadImage("org/netbeans/codecompletion/Rlogo.png"));

    private String text;
    private final int caretOffset;
    private final int dotOffset;
    private String textDocument ; 

    public RLanguageCompletionItem(String text, int dotOffset, int caretOffset , String textDocument) {
        this.text = text;
        this.caretOffset = caretOffset;
        this.dotOffset = dotOffset;
        this.textDocument  = textDocument ;
    }

    @Override
    public void defaultAction(JTextComponent component) {
        
        StringTokenizer stringtoken = new StringTokenizer(text);
        String choice = stringtoken.nextToken();
        
        try {
            StyledDocument doc = (StyledDocument) component.getDocument();
            doc.remove(dotOffset, caretOffset-dotOffset);
            doc.insertString(dotOffset,choice, null);           
            Completion.get().hideAll();
        } catch (BadLocationException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    @Override
    public void processKeyEvent(KeyEvent evt) {

    }

    @Override
    public int getPreferredWidth(Graphics g, Font defaultFont) {
        return CompletionUtilities.getPreferredWidth(text, null, g, defaultFont);
    }

    @Override
    public void render(Graphics g, Font defaultFont, Color defaultColor, Color backgroundColor, int width, int height, boolean selected) {
        CompletionUtilities.renderHtml(ICON, text, null, g, defaultFont,
                (selected ? Color.white : getColor()), width, height, selected);
    }

    @Override
    public CompletionTask createDocumentationTask() {
        return new AsyncCompletionTask(new AsyncCompletionQuery() {

            @Override
            protected void query(CompletionResultSet resultSet, Document doc, int caretOffset) {
                resultSet.setDocumentation(new RLanguageCompletion(RLanguageCompletionItem.this));
                resultSet.finish();
            }
        });
    }

    @Override
    public CompletionTask createToolTipTask() {
        return new AsyncCompletionTask(new AsyncCompletionQuery() {

            @Override
            protected void query(CompletionResultSet resultSet, Document doc, int caretOffset) {
                JToolTip toolTip = new JToolTip();
                toolTip.setTipText("Press Enter to insert \"" + text + "\"");
                resultSet.setToolTip(toolTip);
                resultSet.finish();
            }
        });
    }

    @Override
    public boolean instantSubstitution(JTextComponent component) {
        return false;
    }

    @Override
    public int getSortPriority() {
        return 0;
    }

    @Override
    public CharSequence getSortText() {
        return text;
    }

    @Override
    public CharSequence getInsertPrefix() {
        return text;
    }

    public String getText() {        
        return textDocument;        
    }

    public void setText(String text) {
        this.textDocument = text.concat("()");
    }

    protected Color getColor() {
        return Color.decode("0x0000B2");
    }

}
