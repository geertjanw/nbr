/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.parser;

import java.util.ArrayList;
import java.util.List;
import javax.swing.text.Document;
import org.antlr.v4.runtime.RecognitionException;
import org.netbeans.modules.parsing.spi.Parser.Result;
import org.netbeans.modules.parsing.spi.ParserResultTask;
import org.netbeans.modules.parsing.spi.Scheduler;
import org.netbeans.modules.parsing.spi.SchedulerEvent;
import org.netbeans.parser.RParser.SyntaxError;
import org.netbeans.spi.editor.hints.ErrorDescription;
import org.netbeans.spi.editor.hints.ErrorDescriptionFactory;
import org.netbeans.spi.editor.hints.HintsController;
import org.netbeans.spi.editor.hints.Severity;

/**
 *
 * @author constantin
 */
public class SyntaxErrorsHighlightingTask extends ParserResultTask {

    public SyntaxErrorsHighlightingTask() {
    }

    @Override
    public void run(Result result, SchedulerEvent se) {
        
        try {
        
            NbParser.REditorParserResult sjResult = (NbParser.REditorParserResult) result;
            List<SyntaxError> syntaxErrors = sjResult.getRparser().syntaxErrors;
            Document document = result.getSnapshot().getSource().getDocument(false);
            List<ErrorDescription> errors = new ArrayList<ErrorDescription>();
            for (SyntaxError syntaxError : syntaxErrors) {
    
                RecognitionException exception = syntaxError.exception;
                String message = syntaxError.message;

                int line = syntaxError.line;
                if (line <= 0) {
                    continue;
                }
                
                ErrorDescription errorDescription = ErrorDescriptionFactory.createErrorDescription(
                        Severity.ERROR,
                        message,
                        document,
                        line);
                errors.add(errorDescription);
            }
            
            HintsController.setErrors(document, "R editor", errors);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public int getPriority() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return 100 ;
    }

    @Override
    public Class<? extends Scheduler> getSchedulerClass() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return Scheduler.EDITOR_SENSITIVE_TASK_SCHEDULER ; 
    }

    @Override
    public void cancel() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
