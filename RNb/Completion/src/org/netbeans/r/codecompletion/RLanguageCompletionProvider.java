package org.netbeans.r.codecompletion;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import javax.swing.JEditorPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import org.jsoup.Jsoup;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.r.options.RconfigurationPanel;
import org.netbeans.spi.editor.completion.CompletionProvider;
import org.netbeans.spi.editor.completion.CompletionResultSet;
import org.netbeans.spi.editor.completion.CompletionTask;
import org.netbeans.spi.editor.completion.support.AsyncCompletionQuery;
import org.netbeans.spi.editor.completion.support.AsyncCompletionTask;
import org.openide.ErrorManager;
import org.openide.util.NbPreferences;

/**
 *
 * @author constantin Drabo
 */
@MimeRegistration(mimeType = "text/x-r", service = CompletionProvider.class)
public class RLanguageCompletionProvider implements CompletionProvider {

    Set<String> codes = new TreeSet<String>();

    @Override
    public CompletionTask createTask(int queryType, JTextComponent component) {

        JEditorPane editor = new JEditorPane();
        editor.setContentType("text/html");
        component.add(editor);

        if (queryType == CompletionProvider.COMPLETION_QUERY_TYPE) {

            return new AsyncCompletionTask(new AsyncCompletionQuery() {
                @Override
                protected void query(final CompletionResultSet resultSet, Document doc, final int caretOffset) {

                    final StyledDocument bDoc = (StyledDocument) doc;

                    class ROperation implements Runnable {

                        String filter = null;
                        int startOffset = caretOffset - 1;

                        @Override
                        public void run() {

                            try {
                                
                                final int lineStartOffset = getRowFirstNonWhite(bDoc, caretOffset);
                                final char[] line = bDoc.getText(lineStartOffset, caretOffset - lineStartOffset).toCharArray();
                                final int whiteOffset = indexOfWhite(line);
                                filter = new String(line, whiteOffset + 1, line.length - whiteOffset - 1);
                                if (whiteOffset > 0) {
                                    startOffset = lineStartOffset + whiteOffset + 1;
                                } else {
                                    startOffset = lineStartOffset;
                                }

                                String cheminsLib = NbPreferences.forModule(RconfigurationPanel.class).get("RConfiguration.libpath", "R librairies path");
                                
                                System.out.println("This is the path " + cheminsLib);

                                StringTokenizer mesChemins = new StringTokenizer(cheminsLib, ";");
                                while (mesChemins.hasMoreTokens()) {
                                    HelpFileManager helpM = new HelpFileManager(new File(mesChemins.nextToken()));
                                    Iterator<File> iterFile = helpM.htmlFileList().iterator();

                                    while (iterFile.hasNext()) {
                                        org.jsoup.nodes.Document documens = Jsoup.parse(iterFile.next(), "UTF-8");
                                        org.jsoup.select.Elements elementTable = documens.getAllElements();
                                        org.jsoup.nodes.Element element = elementTable.select("td").first();
                                        org.jsoup.select.Elements elementDocu = elementTable.select("body");
                                        StringBuilder chaine = new StringBuilder(element.text());
                                        chaine.append("-");
                                        chaine.append(elementDocu.html());
                                        if (element != null) {
                                            codes.add(chaine.toString());
                                        }
                                    }
                                }

                                for (String code : codes) {
                                    if (!code.equals("") && code.startsWith(filter)) {
                                        resultSet.addItem(new RLanguageCompletionItem(code.substring(0, code.indexOf("-")), startOffset, caretOffset, code.substring(code.indexOf("-") + 1)));
                                    }
                                }
                                resultSet.finish();
                            } catch (BadLocationException ex) {
                                ErrorManager.getDefault().notify(ex);
                            } catch (IOException io) {
                                io.printStackTrace();
                            }

                        } //End of the method Run

                    } // End of the class ROperation

                    ROperation oper = new ROperation();
                    bDoc.render(oper);
                }
            }, component);
        } //End of the case the querytype is COMPLETION_QUERY_TYPE

        return null;
    } //End of the createTask method

    @Override
    public int getAutoQueryTypes(JTextComponent component, String typedText) {
        return 0;
    }

    static int getRowFirstNonWhite(StyledDocument doc, int offset)
            throws BadLocationException {
        Element lineElement = doc.getParagraphElement(offset);
        int start = lineElement.getStartOffset();
        while (start + 1 < lineElement.getEndOffset()) {
            try {
                if (doc.getText(start, 1).charAt(0) != ' ') {
                    break;
                }
            } catch (BadLocationException ex) {
                throw (BadLocationException) new BadLocationException(
                        "calling getText(" + start + ", " + (start + 1)
                        + ") on doc of length: " + doc.getLength(), start
                ).initCause(ex);
            }
            start++;
        }
        return start;
    }

    static int indexOfWhite(char[] line) {
        int i = line.length;
        while (--i > -1) {
            final char c = line[i];
            if (Character.isWhitespace(c)) {
                return i;
            }
        }
        return -1;
    }

    //Class to provide Documentation to user
    private final class RDocumentationQueries extends AsyncCompletionQuery {

        @Override
        protected void query(CompletionResultSet resultSet, Document doc, int caretOffset) {

        }

    }

}
