/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.lexer;

import org.netbeans.api.lexer.PartType;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;

/**
 *
 * @author constantin Drabo
 */
public class NbRLexer implements Lexer<RTokenId> {

    private LexerRestartInfo<RTokenId> info;
    private RLexer rlexer;

    public NbRLexer(LexerRestartInfo<RTokenId> info) {

        this.info = info;
        AntlrCharStream charStream = new AntlrCharStream(info.input(), "REditor", true);
        rlexer = new RLexer(charStream);
    }

    @Override
    public org.netbeans.api.lexer.Token<RTokenId> nextToken() {
        org.netbeans.api.lexer.Token result = null;
        RTokenId tokenId = RLanguageHierarchy.getToken(RLexer.WS);
        org.antlr.v4.runtime.Token token = rlexer.nextToken();
       // RTokenId tokenId = RLanguageHierarchy.getToken(token.getType());

        if (token.getType() != RLexer.EOF) {
            tokenId = RLanguageHierarchy.getToken(token.getType());
            if (tokenId != null) {
                result = info.tokenFactory().createToken(tokenId);
            } else {
                result = info.tokenFactory().createToken(RLanguageHierarchy.getToken(RLexer.WS));
            }
        } else if (info.input().readLength() > 0) {
         
            tokenId = RLanguageHierarchy.getToken(RLexer.WS);
            result = info.tokenFactory().createToken(tokenId, info.input().readLength(), PartType.MIDDLE);
        }
        return result;
    }

    @Override
    public Object state() {
        return null;
    }

    @Override
    public void release() {
    }

    public RLexer getRlexer() {
        return rlexer;
    }

    public void setRlexer(RLexer rlexer) {
        this.rlexer = rlexer;
    }
}
