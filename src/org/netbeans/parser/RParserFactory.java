/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.parser;

import java.util.Collection;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.spi.Parser;
import org.netbeans.modules.parsing.spi.ParserFactory;

/**
 *
 * @author constantin
 */
public class RParserFactory extends ParserFactory {
    @Override
    public Parser createParser(Collection<Snapshot> clctn) {
        return new NbParser();
    }
    
}
