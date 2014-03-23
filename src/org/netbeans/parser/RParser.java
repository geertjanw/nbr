// Generated from /home/constantin/Documents/Logiciel/RGrammarANTL4/R.g4 by ANTLR 4.0
package org.netbeans.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RParser extends Parser {

    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    public static final int T__53 = 1, T__52 = 2, T__51 = 3, T__50 = 4, T__49 = 5, T__48 = 6, T__47 = 7, T__46 = 8,
            T__45 = 9, T__44 = 10, T__43 = 11, T__42 = 12, T__41 = 13, T__40 = 14, T__39 = 15, T__38 = 16,
            T__37 = 17, T__36 = 18, T__35 = 19, T__34 = 20, T__33 = 21, T__32 = 22, T__31 = 23,
            T__30 = 24, T__29 = 25, T__28 = 26, T__27 = 27, T__26 = 28, T__25 = 29, T__24 = 30,
            T__23 = 31, T__22 = 32, T__21 = 33, T__20 = 34, T__19 = 35, T__18 = 36, T__17 = 37,
            T__16 = 38, T__15 = 39, T__14 = 40, T__13 = 41, T__12 = 42, T__11 = 43, T__10 = 44,
            T__9 = 45, T__8 = 46, T__7 = 47, T__6 = 48, T__5 = 49, T__4 = 50, T__3 = 51, T__2 = 52,
            T__1 = 53, T__0 = 54, HEX = 55, INT = 56, FLOAT = 57, COMPLEX = 58, STRING = 59, ID = 60,
            USER_OP = 61, NL = 62, WS = 63;
    public static final String[] tokenNames = {
        "<INVALID>", "'&'", "'['", "'*'", "'<'", "'!='", "'<='", "'<<-'", "'next'",
        "'TRUE'", "'}'", "'[['", "'->'", "'->>'", "')'", "'NaN'", "'::'", "'@'",
        "'='", "'repeat'", "'NA'", "'|'", "'!'", "']'", "'in'", "','", "'while'",
        "'-'", "'('", "':'", "'if'", "'?'", "'{'", "'...'", "'break'", "'else'",
        "'<-'", "'$'", "':::'", "'FALSE'", "'^'", "'NULL'", "'function'", "'+'",
        "'for'", "';'", "'&&'", "'||'", "'>'", "':='", "'/'", "'=='", "'~'", "'Inf'",
        "'>='", "HEX", "INT", "FLOAT", "COMPLEX", "STRING", "ID", "USER_OP", "NL",
        "WS"
    };
    public static final int RULE_prog = 0, RULE_expr_or_assign = 1, RULE_expr = 2, RULE_exprlist = 3,
            RULE_formlist = 4, RULE_form = 5, RULE_sublist = 6, RULE_sub = 7;
    public static final String[] ruleNames = {
        "prog", "expr_or_assign", "expr", "exprlist", "formlist", "form", "sublist",
        "sub"
    };

    @Override
    public String getGrammarFileName() {
        return "R.g4";
    }

    @Override
    public String[] getTokenNames() {
        return tokenNames;
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }
    public List<SyntaxError> syntaxErrors = new ArrayList<SyntaxError>();

    public String getErrorMessage(RecognitionException e, String[] tokenNames) {
        String message = super.getErrorHeader(e); //  .getErrorMessage(e, tokenNames);
        SyntaxError syntaxError = new SyntaxError();
        syntaxError.exception = e;
        syntaxError.message = message;
        syntaxErrors.add(syntaxError);
        return message;
    }

    public static class SyntaxError {

        public RecognitionException exception;
        public String message;
        public int line;
        public int charPositionInLine;
    }

    public RParser(TokenStream input) {
        super(input);
        _interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    public static class ProgContext extends ParserRuleContext {

        public Expr_or_assignContext expr_or_assign(int i) {
            return getRuleContext(Expr_or_assignContext.class, i);
        }

        public List<TerminalNode> NL() {
            return getTokens(RParser.NL);
        }

        public TerminalNode EOF() {
            return getToken(RParser.EOF, 0);
        }

        public List<Expr_or_assignContext> expr_or_assign() {
            return getRuleContexts(Expr_or_assignContext.class);
        }

        public TerminalNode NL(int i) {
            return getToken(RParser.NL, i);
        }

        public ProgContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_prog;
        }
    }

    public final ProgContext prog() throws RecognitionException {
        System.out.println("Into the Prog method... ");
        ProgContext _localctx = new ProgContext(_ctx, getState());
        enterRule(_localctx, 0, RULE_prog);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(22);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 8) | (1L << 9) | (1L << 15) | (1L << 19) | (1L << 20) | (1L << 22) | (1L << 26) | (1L << 27) | (1L << 28) | (1L << 30) | (1L << 31) | (1L << 32) | (1L << 34) | (1L << 39) | (1L << 41) | (1L << 42) | (1L << 43) | (1L << 44) | (1L << 52) | (1L << 53) | (1L << HEX) | (1L << INT) | (1L << FLOAT) | (1L << COMPLEX) | (1L << STRING) | (1L << ID) | (1L << NL))) != 0)) {
                    {
                        setState(20);
                        switch (_input.LA(1)) {
                            case 8:
                            case 9:
                            case 15:
                            case 19:
                            case 20:
                            case 22:
                            case 26:
                            case 27:
                            case 28:
                            case 30:
                            case 31:
                            case 32:
                            case 34:
                            case 39:
                            case 41:
                            case 42:
                            case 43:
                            case 44:
                            case 52:
                            case 53:
                            case HEX:
                            case INT:
                            case FLOAT:
                            case COMPLEX:
                            case STRING:
                            case ID: {
                                setState(16);
                                expr_or_assign();
                                setState(17);
                                _la = _input.LA(1);
                                if (!(_la == 45 || _la == NL)) {
                                    _errHandler.recoverInline(this);
                                }
                                consume();
                            }
                            break;
                            case NL: {
                                setState(19);
                                match(NL);
                            }
                            break;
                            default:
                                throw new NoViableAltException(this);
                        }
                    }
                    setState(24);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(25);
                match(EOF);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class Expr_or_assignContext extends ParserRuleContext {

        public ExprContext expr() {
            return getRuleContext(ExprContext.class, 0);
        }

        public Expr_or_assignContext expr_or_assign() {
            return getRuleContext(Expr_or_assignContext.class, 0);
        }

        public Expr_or_assignContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_expr_or_assign;
        }
    }

    public final Expr_or_assignContext expr_or_assign() throws RecognitionException {
        Expr_or_assignContext _localctx = new Expr_or_assignContext(_ctx, getState());
        enterRule(_localctx, 2, RULE_expr_or_assign);
        int _la;
        try {
            setState(32);
            switch (getInterpreter().adaptivePredict(_input, 2, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1); {
                    setState(27);
                    expr(0);
                    setState(28);
                    _la = _input.LA(1);
                    if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 7) | (1L << 18) | (1L << 36))) != 0))) {
                        _errHandler.recoverInline(this);
                    }
                    consume();
                    setState(29);
                    expr_or_assign();
                }
                break;

                case 2:
                    enterOuterAlt(_localctx, 2); {
                    setState(31);
                    expr(0);
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ExprContext extends ParserRuleContext {

        public int _p;

        public TerminalNode USER_OP() {
            return getToken(RParser.USER_OP, 0);
        }

        public TerminalNode COMPLEX() {
            return getToken(RParser.COMPLEX, 0);
        }

        public TerminalNode FLOAT() {
            return getToken(RParser.FLOAT, 0);
        }

        public TerminalNode INT() {
            return getToken(RParser.INT, 0);
        }

        public TerminalNode ID() {
            return getToken(RParser.ID, 0);
        }

        public ExprContext expr(int i) {
            return getRuleContext(ExprContext.class, i);
        }

        public TerminalNode HEX() {
            return getToken(RParser.HEX, 0);
        }

        public List<ExprContext> expr() {
            return getRuleContexts(ExprContext.class);
        }

        public SublistContext sublist() {
            return getRuleContext(SublistContext.class, 0);
        }

        public ExprlistContext exprlist() {
            return getRuleContext(ExprlistContext.class, 0);
        }

        public FormlistContext formlist() {
            return getRuleContext(FormlistContext.class, 0);
        }

        public TerminalNode STRING() {
            return getToken(RParser.STRING, 0);
        }

        public ExprContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public ExprContext(ParserRuleContext parent, int invokingState, int _p) {
            super(parent, invokingState);
            this._p = _p;
        }

        @Override
        public int getRuleIndex() {
            return RULE_expr;
        }
    }

    public final ExprContext expr(int _p) throws RecognitionException {
        ParserRuleContext _parentctx = _ctx;
        int _parentState = getState();
        ExprContext _localctx = new ExprContext(_ctx, _parentState, _p);
        ExprContext _prevctx = _localctx;
        int _startState = 4;
        enterRecursionRule(_localctx, RULE_expr);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(102);
                switch (getInterpreter().adaptivePredict(_input, 4, _ctx)) {
                    case 1: {
                        setState(35);
                        _la = _input.LA(1);
                        if (!(_la == 27 || _la == 43)) {
                            _errHandler.recoverInline(this);
                        }
                        consume();
                        setState(36);
                        expr(36);
                    }
                    break;

                    case 2: {
                        setState(37);
                        match(22);
                        setState(38);
                        expr(30);
                    }
                    break;

                    case 3: {
                        setState(39);
                        match(52);
                        setState(40);
                        expr(27);
                    }
                    break;

                    case 4: {
                        setState(41);
                        match(42);
                        setState(42);
                        match(28);
                        setState(44);
                        _la = _input.LA(1);
                        if (_la == 33 || _la == ID) {
                            {
                                setState(43);
                                formlist();
                            }
                        }

                        setState(46);
                        match(14);
                        setState(47);
                        expr(24);
                    }
                    break;

                    case 5: {
                        setState(48);
                        match(19);
                        setState(49);
                        expr(17);
                    }
                    break;

                    case 6: {
                        setState(50);
                        match(31);
                        setState(51);
                        expr(16);
                    }
                    break;

                    case 7: {
                        setState(52);
                        match(32);
                        setState(53);
                        exprlist();
                        setState(54);
                        match(10);
                    }
                    break;

                    case 8: {
                        setState(56);
                        match(30);
                        setState(57);
                        match(28);
                        setState(58);
                        expr(0);
                        setState(59);
                        match(14);
                        setState(60);
                        expr(0);
                    }
                    break;

                    case 9: {
                        setState(62);
                        match(30);
                        setState(63);
                        match(28);
                        setState(64);
                        expr(0);
                        setState(65);
                        match(14);
                        setState(66);
                        expr(0);
                        setState(67);
                        match(35);
                        setState(68);
                        expr(0);
                    }
                    break;

                    case 10: {
                        setState(70);
                        match(44);
                        setState(71);
                        match(28);
                        setState(72);
                        match(ID);
                        setState(73);
                        match(24);
                        setState(74);
                        expr(0);
                        setState(75);
                        match(14);
                        setState(76);
                        expr(0);
                    }
                    break;

                    case 11: {
                        setState(78);
                        match(26);
                        setState(79);
                        match(28);
                        setState(80);
                        expr(0);
                        setState(81);
                        match(14);
                        setState(82);
                        expr(0);
                    }
                    break;

                    case 12: {
                        setState(84);
                        match(8);
                    }
                    break;

                    case 13: {
                        setState(85);
                        match(34);
                    }
                    break;

                    case 14: {
                        setState(86);
                        match(28);
                        setState(87);
                        expr(0);
                        setState(88);
                        match(14);
                    }
                    break;

                    case 15: {
                        setState(90);
                        match(ID);
                    }
                    break;

                    case 16: {
                        setState(91);
                        match(STRING);
                    }
                    break;

                    case 17: {
                        setState(92);
                        match(HEX);
                    }
                    break;

                    case 18: {
                        setState(93);
                        match(INT);
                    }
                    break;

                    case 19: {
                        setState(94);
                        match(FLOAT);
                    }
                    break;

                    case 20: {
                        setState(95);
                        match(COMPLEX);
                    }
                    break;

                    case 21: {
                        setState(96);
                        match(41);
                    }
                    break;

                    case 22: {
                        setState(97);
                        match(20);
                    }
                    break;

                    case 23: {
                        setState(98);
                        match(53);
                    }
                    break;

                    case 24: {
                        setState(99);
                        match(15);
                    }
                    break;

                    case 25: {
                        setState(100);
                        match(9);
                    }
                    break;

                    case 26: {
                        setState(101);
                        match(39);
                    }
                    break;
                }
                _ctx.stop = _input.LT(-1);
                setState(158);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 6, _ctx);
                while (_alt != 2 && _alt != -1) {
                    if (_alt == 1) {
                        if (_parseListeners != null) {
                            triggerExitRuleEvent();
                        }
                        _prevctx = _localctx;
                        {
                            setState(156);
                            switch (getInterpreter().adaptivePredict(_input, 5, _ctx)) {
                                case 1: {
                                    _localctx = new ExprContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expr);
                                    setState(104);
                                    if (!(39 >= _localctx._p)) {
                                        throw new FailedPredicateException(this, "39 >= $_p");
                                    }
                                    setState(105);
                                    _la = _input.LA(1);
                                    if (!(_la == 16 || _la == 38)) {
                                        _errHandler.recoverInline(this);
                                    }
                                    consume();
                                    setState(106);
                                    expr(40);
                                }
                                break;

                                case 2: {
                                    _localctx = new ExprContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expr);
                                    setState(107);
                                    if (!(38 >= _localctx._p)) {
                                        throw new FailedPredicateException(this, "38 >= $_p");
                                    }
                                    setState(108);
                                    _la = _input.LA(1);
                                    if (!(_la == 17 || _la == 37)) {
                                        _errHandler.recoverInline(this);
                                    }
                                    consume();
                                    setState(109);
                                    expr(39);
                                }
                                break;

                                case 3: {
                                    _localctx = new ExprContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expr);
                                    setState(110);
                                    if (!(37 >= _localctx._p)) {
                                        throw new FailedPredicateException(this, "37 >= $_p");
                                    }
                                    setState(111);
                                    match(40);
                                    setState(112);
                                    expr(37);
                                }
                                break;

                                case 4: {
                                    _localctx = new ExprContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expr);
                                    setState(113);
                                    if (!(35 >= _localctx._p)) {
                                        throw new FailedPredicateException(this, "35 >= $_p");
                                    }
                                    setState(114);
                                    match(29);
                                    setState(115);
                                    expr(36);
                                }
                                break;

                                case 5: {
                                    _localctx = new ExprContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expr);
                                    setState(116);
                                    if (!(34 >= _localctx._p)) {
                                        throw new FailedPredicateException(this, "34 >= $_p");
                                    }
                                    setState(117);
                                    match(USER_OP);
                                    setState(118);
                                    expr(35);
                                }
                                break;

                                case 6: {
                                    _localctx = new ExprContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expr);
                                    setState(119);
                                    if (!(33 >= _localctx._p)) {
                                        throw new FailedPredicateException(this, "33 >= $_p");
                                    }
                                    setState(120);
                                    _la = _input.LA(1);
                                    if (!(_la == 3 || _la == 50)) {
                                        _errHandler.recoverInline(this);
                                    }
                                    consume();
                                    setState(121);
                                    expr(34);
                                }
                                break;

                                case 7: {
                                    _localctx = new ExprContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expr);
                                    setState(122);
                                    if (!(32 >= _localctx._p)) {
                                        throw new FailedPredicateException(this, "32 >= $_p");
                                    }
                                    setState(123);
                                    _la = _input.LA(1);
                                    if (!(_la == 27 || _la == 43)) {
                                        _errHandler.recoverInline(this);
                                    }
                                    consume();
                                    setState(124);
                                    expr(33);
                                }
                                break;

                                case 8: {
                                    _localctx = new ExprContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expr);
                                    setState(125);
                                    if (!(31 >= _localctx._p)) {
                                        throw new FailedPredicateException(this, "31 >= $_p");
                                    }
                                    setState(126);
                                    _la = _input.LA(1);
                                    if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 4) | (1L << 5) | (1L << 6) | (1L << 48) | (1L << 51) | (1L << 54))) != 0))) {
                                        _errHandler.recoverInline(this);
                                    }
                                    consume();
                                    setState(127);
                                    expr(32);
                                }
                                break;

                                case 9: {
                                    _localctx = new ExprContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expr);
                                    setState(128);
                                    if (!(29 >= _localctx._p)) {
                                        throw new FailedPredicateException(this, "29 >= $_p");
                                    }
                                    setState(129);
                                    _la = _input.LA(1);
                                    if (!(_la == 1 || _la == 46)) {
                                        _errHandler.recoverInline(this);
                                    }
                                    consume();
                                    setState(130);
                                    expr(30);
                                }
                                break;

                                case 10: {
                                    _localctx = new ExprContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expr);
                                    setState(131);
                                    if (!(28 >= _localctx._p)) {
                                        throw new FailedPredicateException(this, "28 >= $_p");
                                    }
                                    setState(132);
                                    _la = _input.LA(1);
                                    if (!(_la == 21 || _la == 47)) {
                                        _errHandler.recoverInline(this);
                                    }
                                    consume();
                                    setState(133);
                                    expr(29);
                                }
                                break;

                                case 11: {
                                    _localctx = new ExprContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expr);
                                    setState(134);
                                    if (!(26 >= _localctx._p)) {
                                        throw new FailedPredicateException(this, "26 >= $_p");
                                    }
                                    setState(135);
                                    match(52);
                                    setState(136);
                                    expr(27);
                                }
                                break;

                                case 12: {
                                    _localctx = new ExprContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expr);
                                    setState(137);
                                    if (!(25 >= _localctx._p)) {
                                        throw new FailedPredicateException(this, "25 >= $_p");
                                    }
                                    setState(138);
                                    _la = _input.LA(1);
                                    if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 12) | (1L << 13) | (1L << 49))) != 0))) {
                                        _errHandler.recoverInline(this);
                                    }
                                    consume();
                                    setState(139);
                                    expr(26);
                                }
                                break;

                                case 13: {
                                    _localctx = new ExprContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expr);
                                    setState(140);
                                    if (!(41 >= _localctx._p)) {
                                        throw new FailedPredicateException(this, "41 >= $_p");
                                    }
                                    setState(141);
                                    match(11);
                                    setState(142);
                                    sublist();
                                    setState(143);
                                    match(23);
                                    setState(144);
                                    match(23);
                                }
                                break;

                                case 14: {
                                    _localctx = new ExprContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expr);
                                    setState(146);
                                    if (!(40 >= _localctx._p)) {
                                        throw new FailedPredicateException(this, "40 >= $_p");
                                    }
                                    setState(147);
                                    match(2);
                                    setState(148);
                                    sublist();
                                    setState(149);
                                    match(23);
                                }
                                break;

                                case 15: {
                                    _localctx = new ExprContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expr);
                                    setState(151);
                                    if (!(23 >= _localctx._p)) {
                                        throw new FailedPredicateException(this, "23 >= $_p");
                                    }
                                    setState(152);
                                    match(28);
                                    setState(153);
                                    sublist();
                                    setState(154);
                                    match(14);
                                }
                                break;
                            }
                        }
                    }
                    setState(160);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 6, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            unrollRecursionContexts(_parentctx);
        }
        return _localctx;
    }

    public static class ExprlistContext extends ParserRuleContext {

        public Expr_or_assignContext expr_or_assign(int i) {
            return getRuleContext(Expr_or_assignContext.class, i);
        }

        public List<TerminalNode> NL() {
            return getTokens(RParser.NL);
        }

        public List<Expr_or_assignContext> expr_or_assign() {
            return getRuleContexts(Expr_or_assignContext.class);
        }

        public TerminalNode NL(int i) {
            return getToken(RParser.NL, i);
        }

        public ExprlistContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_exprlist;
        }
    }

    public final ExprlistContext exprlist() throws RecognitionException {
        ExprlistContext _localctx = new ExprlistContext(_ctx, getState());
        enterRule(_localctx, 6, RULE_exprlist);
        int _la;
        try {
            setState(172);
            switch (_input.LA(1)) {
                case 8:
                case 9:
                case 15:
                case 19:
                case 20:
                case 22:
                case 26:
                case 27:
                case 28:
                case 30:
                case 31:
                case 32:
                case 34:
                case 39:
                case 41:
                case 42:
                case 43:
                case 44:
                case 52:
                case 53:
                case HEX:
                case INT:
                case FLOAT:
                case COMPLEX:
                case STRING:
                case ID:
                    enterOuterAlt(_localctx, 1); {
                    setState(161);
                    expr_or_assign();
                    setState(168);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == 45 || _la == NL) {
                        {
                            {
                                setState(162);
                                _la = _input.LA(1);
                                if (!(_la == 45 || _la == NL)) {
                                    _errHandler.recoverInline(this);
                                }
                                consume();
                                setState(164);
                                _la = _input.LA(1);
                                if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 8) | (1L << 9) | (1L << 15) | (1L << 19) | (1L << 20) | (1L << 22) | (1L << 26) | (1L << 27) | (1L << 28) | (1L << 30) | (1L << 31) | (1L << 32) | (1L << 34) | (1L << 39) | (1L << 41) | (1L << 42) | (1L << 43) | (1L << 44) | (1L << 52) | (1L << 53) | (1L << HEX) | (1L << INT) | (1L << FLOAT) | (1L << COMPLEX) | (1L << STRING) | (1L << ID))) != 0)) {
                                    {
                                        setState(163);
                                        expr_or_assign();
                                    }
                                }

                            }
                        }
                        setState(170);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                }
                break;
                case 10:
                    enterOuterAlt(_localctx, 2); {
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class FormlistContext extends ParserRuleContext {

        public List<FormContext> form() {
            return getRuleContexts(FormContext.class);
        }

        public FormContext form(int i) {
            return getRuleContext(FormContext.class, i);
        }

        public FormlistContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_formlist;
        }
    }

    public final FormlistContext formlist() throws RecognitionException {
        FormlistContext _localctx = new FormlistContext(_ctx, getState());
        enterRule(_localctx, 8, RULE_formlist);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(174);
                form();
                setState(179);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == 25) {
                    {
                        {
                            setState(175);
                            match(25);
                            setState(176);
                            form();
                        }
                    }
                    setState(181);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class FormContext extends ParserRuleContext {

        public TerminalNode ID() {
            return getToken(RParser.ID, 0);
        }

        public ExprContext expr() {
            return getRuleContext(ExprContext.class, 0);
        }

        public FormContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_form;
        }
    }

    public final FormContext form() throws RecognitionException {
        FormContext _localctx = new FormContext(_ctx, getState());
        enterRule(_localctx, 10, RULE_form);
        try {
            setState(187);
            switch (getInterpreter().adaptivePredict(_input, 11, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1); {
                    setState(182);
                    match(ID);
                }
                break;

                case 2:
                    enterOuterAlt(_localctx, 2); {
                    setState(183);
                    match(ID);
                    setState(184);
                    match(18);
                    setState(185);
                    expr(0);
                }
                break;

                case 3:
                    enterOuterAlt(_localctx, 3); {
                    setState(186);
                    match(33);
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class SublistContext extends ParserRuleContext {

        public List<SubContext> sub() {
            return getRuleContexts(SubContext.class);
        }

        public SubContext sub(int i) {
            return getRuleContext(SubContext.class, i);
        }

        public SublistContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_sublist;
        }
    }

    public final SublistContext sublist() throws RecognitionException {
        SublistContext _localctx = new SublistContext(_ctx, getState());
        enterRule(_localctx, 12, RULE_sublist);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(189);
                sub();
                setState(194);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == 25) {
                    {
                        {
                            setState(190);
                            match(25);
                            setState(191);
                            sub();
                        }
                    }
                    setState(196);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class SubContext extends ParserRuleContext {

        public TerminalNode ID() {
            return getToken(RParser.ID, 0);
        }

        public ExprContext expr() {
            return getRuleContext(ExprContext.class, 0);
        }

        public TerminalNode STRING() {
            return getToken(RParser.STRING, 0);
        }

        public SubContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_sub;
        }
    }

    public final SubContext sub() throws RecognitionException {
        SubContext _localctx = new SubContext(_ctx, getState());
        enterRule(_localctx, 14, RULE_sub);
        try {
            setState(215);
            switch (getInterpreter().adaptivePredict(_input, 13, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1); {
                    setState(197);
                    expr(0);
                }
                break;

                case 2:
                    enterOuterAlt(_localctx, 2); {
                    setState(198);
                    match(ID);
                    setState(199);
                    match(18);
                }
                break;

                case 3:
                    enterOuterAlt(_localctx, 3); {
                    setState(200);
                    match(ID);
                    setState(201);
                    match(18);
                    setState(202);
                    expr(0);
                }
                break;

                case 4:
                    enterOuterAlt(_localctx, 4); {
                    setState(203);
                    match(STRING);
                    setState(204);
                    match(18);
                }
                break;

                case 5:
                    enterOuterAlt(_localctx, 5); {
                    setState(205);
                    match(STRING);
                    setState(206);
                    match(18);
                    setState(207);
                    expr(0);
                }
                break;

                case 6:
                    enterOuterAlt(_localctx, 6); {
                    setState(208);
                    match(41);
                    setState(209);
                    match(18);
                }
                break;

                case 7:
                    enterOuterAlt(_localctx, 7); {
                    setState(210);
                    match(41);
                    setState(211);
                    match(18);
                    setState(212);
                    expr(0);
                }
                break;

                case 8:
                    enterOuterAlt(_localctx, 8); {
                    setState(213);
                    match(33);
                }
                break;

                case 9:
                    enterOuterAlt(_localctx, 9); {
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
        switch (ruleIndex) {
            case 2:
                return expr_sempred((ExprContext) _localctx, predIndex);
        }
        return true;
    }

    private boolean expr_sempred(ExprContext _localctx, int predIndex) {
        switch (predIndex) {
            case 0:
                return 39 >= _localctx._p;

            case 1:
                return 38 >= _localctx._p;

            case 2:
                return 37 >= _localctx._p;

            case 3:
                return 35 >= _localctx._p;

            case 4:
                return 34 >= _localctx._p;

            case 5:
                return 33 >= _localctx._p;

            case 6:
                return 32 >= _localctx._p;

            case 7:
                return 31 >= _localctx._p;

            case 8:
                return 29 >= _localctx._p;

            case 9:
                return 28 >= _localctx._p;

            case 10:
                return 26 >= _localctx._p;

            case 11:
                return 25 >= _localctx._p;

            case 12:
                return 41 >= _localctx._p;

            case 13:
                return 40 >= _localctx._p;

            case 14:
                return 23 >= _localctx._p;
        }
        return true;
    }
    public static final String _serializedATN =
            "\2\3A\u00dc\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4"
            + "\t\t\t\3\2\3\2\3\2\3\2\7\2\27\n\2\f\2\16\2\32\13\2\3\2\3\2\3\3\3\3\3\3"
            + "\3\3\3\3\5\3#\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4/\n\4\3\4"
            + "\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"
            + "\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"
            + "\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"
            + "\4\3\4\3\4\5\4i\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"
            + "\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"
            + "\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"
            + "\4\3\4\3\4\3\4\3\4\7\4\u009f\n\4\f\4\16\4\u00a2\13\4\3\5\3\5\3\5\5\5\u00a7"
            + "\n\5\7\5\u00a9\n\5\f\5\16\5\u00ac\13\5\3\5\5\5\u00af\n\5\3\6\3\6\3\6\7"
            + "\6\u00b4\n\6\f\6\16\6\u00b7\13\6\3\7\3\7\3\7\3\7\3\7\5\7\u00be\n\7\3\b"
            + "\3\b\3\b\7\b\u00c3\n\b\f\b\16\b\u00c6\13\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t"
            + "\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00da\n\t\3\t\2\n\2\4"
            + "\6\b\n\f\16\20\2\16\4//@@\5\t\t\24\24&&\4\35\35--\4\22\22((\4\23\23\'"
            + "\'\4\5\5\64\64\4\35\35--\6\6\b\62\62\65\6588\4\3\3\60\60\4\27\27\61\61"
            + "\4\16\17\63\63\4//@@\u010e\2\30\3\2\2\2\4\"\3\2\2\2\6h\3\2\2\2\b\u00ae"
            + "\3\2\2\2\n\u00b0\3\2\2\2\f\u00bd\3\2\2\2\16\u00bf\3\2\2\2\20\u00d9\3\2"
            + "\2\2\22\23\5\4\3\2\23\24\t\2\2\2\24\27\3\2\2\2\25\27\7@\2\2\26\22\3\2"
            + "\2\2\26\25\3\2\2\2\27\32\3\2\2\2\30\26\3\2\2\2\30\31\3\2\2\2\31\33\3\2"
            + "\2\2\32\30\3\2\2\2\33\34\7\1\2\2\34\3\3\2\2\2\35\36\5\6\4\2\36\37\t\3"
            + "\2\2\37 \5\4\3\2 #\3\2\2\2!#\5\6\4\2\"\35\3\2\2\2\"!\3\2\2\2#\5\3\2\2"
            + "\2$%\b\4\1\2%&\t\4\2\2&i\5\6\4\2\'(\7\30\2\2(i\5\6\4\2)*\7\66\2\2*i\5"
            + "\6\4\2+,\7,\2\2,.\7\36\2\2-/\5\n\6\2.-\3\2\2\2./\3\2\2\2/\60\3\2\2\2\60"
            + "\61\7\20\2\2\61i\5\6\4\2\62\63\7\25\2\2\63i\5\6\4\2\64\65\7!\2\2\65i\5"
            + "\6\4\2\66\67\7\"\2\2\678\5\b\5\289\7\f\2\29i\3\2\2\2:;\7 \2\2;<\7\36\2"
            + "\2<=\5\6\4\2=>\7\20\2\2>?\5\6\4\2?i\3\2\2\2@A\7 \2\2AB\7\36\2\2BC\5\6"
            + "\4\2CD\7\20\2\2DE\5\6\4\2EF\7%\2\2FG\5\6\4\2Gi\3\2\2\2HI\7.\2\2IJ\7\36"
            + "\2\2JK\7>\2\2KL\7\32\2\2LM\5\6\4\2MN\7\20\2\2NO\5\6\4\2Oi\3\2\2\2PQ\7"
            + "\34\2\2QR\7\36\2\2RS\5\6\4\2ST\7\20\2\2TU\5\6\4\2Ui\3\2\2\2Vi\7\n\2\2"
            + "Wi\7$\2\2XY\7\36\2\2YZ\5\6\4\2Z[\7\20\2\2[i\3\2\2\2\\i\7>\2\2]i\7=\2\2"
            + "^i\79\2\2_i\7:\2\2`i\7;\2\2ai\7<\2\2bi\7+\2\2ci\7\26\2\2di\7\67\2\2ei"
            + "\7\21\2\2fi\7\13\2\2gi\7)\2\2h$\3\2\2\2h\'\3\2\2\2h)\3\2\2\2h+\3\2\2\2"
            + "h\62\3\2\2\2h\64\3\2\2\2h\66\3\2\2\2h:\3\2\2\2h@\3\2\2\2hH\3\2\2\2hP\3"
            + "\2\2\2hV\3\2\2\2hW\3\2\2\2hX\3\2\2\2h\\\3\2\2\2h]\3\2\2\2h^\3\2\2\2h_"
            + "\3\2\2\2h`\3\2\2\2ha\3\2\2\2hb\3\2\2\2hc\3\2\2\2hd\3\2\2\2he\3\2\2\2h"
            + "f\3\2\2\2hg\3\2\2\2i\u00a0\3\2\2\2jk\6\4\2\3kl\t\5\2\2l\u009f\5\6\4\2"
            + "mn\6\4\3\3no\t\6\2\2o\u009f\5\6\4\2pq\6\4\4\3qr\7*\2\2r\u009f\5\6\4\2"
            + "st\6\4\5\3tu\7\37\2\2u\u009f\5\6\4\2vw\6\4\6\3wx\7?\2\2x\u009f\5\6\4\2"
            + "yz\6\4\7\3z{\t\7\2\2{\u009f\5\6\4\2|}\6\4\b\3}~\t\b\2\2~\u009f\5\6\4\2"
            + "\177\u0080\6\4\t\3\u0080\u0081\t\t\2\2\u0081\u009f\5\6\4\2\u0082\u0083"
            + "\6\4\n\3\u0083\u0084\t\n\2\2\u0084\u009f\5\6\4\2\u0085\u0086\6\4\13\3"
            + "\u0086\u0087\t\13\2\2\u0087\u009f\5\6\4\2\u0088\u0089\6\4\f\3\u0089\u008a"
            + "\7\66\2\2\u008a\u009f\5\6\4\2\u008b\u008c\6\4\r\3\u008c\u008d\t\f\2\2"
            + "\u008d\u009f\5\6\4\2\u008e\u008f\6\4\16\3\u008f\u0090\7\r\2\2\u0090\u0091"
            + "\5\16\b\2\u0091\u0092\7\31\2\2\u0092\u0093\7\31\2\2\u0093\u009f\3\2\2"
            + "\2\u0094\u0095\6\4\17\3\u0095\u0096\7\4\2\2\u0096\u0097\5\16\b\2\u0097"
            + "\u0098\7\31\2\2\u0098\u009f\3\2\2\2\u0099\u009a\6\4\20\3\u009a\u009b\7"
            + "\36\2\2\u009b\u009c\5\16\b\2\u009c\u009d\7\20\2\2\u009d\u009f\3\2\2\2"
            + "\u009ej\3\2\2\2\u009em\3\2\2\2\u009ep\3\2\2\2\u009es\3\2\2\2\u009ev\3"
            + "\2\2\2\u009ey\3\2\2\2\u009e|\3\2\2\2\u009e\177\3\2\2\2\u009e\u0082\3\2"
            + "\2\2\u009e\u0085\3\2\2\2\u009e\u0088\3\2\2\2\u009e\u008b\3\2\2\2\u009e"
            + "\u008e\3\2\2\2\u009e\u0094\3\2\2\2\u009e\u0099\3\2\2\2\u009f\u00a2\3\2"
            + "\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\7\3\2\2\2\u00a2\u00a0"
            + "\3\2\2\2\u00a3\u00aa\5\4\3\2\u00a4\u00a6\t\r\2\2\u00a5\u00a7\5\4\3\2\u00a6"
            + "\u00a5\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a9\3\2\2\2\u00a8\u00a4\3\2"
            + "\2\2\u00a9\u00ac\3\2\2\2\u00aa\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab"
            + "\u00af\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ad\u00af\3\2\2\2\u00ae\u00a3\3\2"
            + "\2\2\u00ae\u00ad\3\2\2\2\u00af\t\3\2\2\2\u00b0\u00b5\5\f\7\2\u00b1\u00b2"
            + "\7\33\2\2\u00b2\u00b4\5\f\7\2\u00b3\u00b1\3\2\2\2\u00b4\u00b7\3\2\2\2"
            + "\u00b5\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\13\3\2\2\2\u00b7\u00b5"
            + "\3\2\2\2\u00b8\u00be\7>\2\2\u00b9\u00ba\7>\2\2\u00ba\u00bb\7\24\2\2\u00bb"
            + "\u00be\5\6\4\2\u00bc\u00be\7#\2\2\u00bd\u00b8\3\2\2\2\u00bd\u00b9\3\2"
            + "\2\2\u00bd\u00bc\3\2\2\2\u00be\r\3\2\2\2\u00bf\u00c4\5\20\t\2\u00c0\u00c1"
            + "\7\33\2\2\u00c1\u00c3\5\20\t\2\u00c2\u00c0\3\2\2\2\u00c3\u00c6\3\2\2\2"
            + "\u00c4\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\17\3\2\2\2\u00c6\u00c4"
            + "\3\2\2\2\u00c7\u00da\5\6\4\2\u00c8\u00c9\7>\2\2\u00c9\u00da\7\24\2\2\u00ca"
            + "\u00cb\7>\2\2\u00cb\u00cc\7\24\2\2\u00cc\u00da\5\6\4\2\u00cd\u00ce\7="
            + "\2\2\u00ce\u00da\7\24\2\2\u00cf\u00d0\7=\2\2\u00d0\u00d1\7\24\2\2\u00d1"
            + "\u00da\5\6\4\2\u00d2\u00d3\7+\2\2\u00d3\u00da\7\24\2\2\u00d4\u00d5\7+"
            + "\2\2\u00d5\u00d6\7\24\2\2\u00d6\u00da\5\6\4\2\u00d7\u00da\7#\2\2\u00d8"
            + "\u00da\3\2\2\2\u00d9\u00c7\3\2\2\2\u00d9\u00c8\3\2\2\2\u00d9\u00ca\3\2"
            + "\2\2\u00d9\u00cd\3\2\2\2\u00d9\u00cf\3\2\2\2\u00d9\u00d2\3\2\2\2\u00d9"
            + "\u00d4\3\2\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00d8\3\2\2\2\u00da\21\3\2\2"
            + "\2\20\26\30\".h\u009e\u00a0\u00a6\u00aa\u00ae\u00b5\u00bd\u00c4\u00d9";
    public static final ATN _ATN =
            ATNSimulator.deserialize(_serializedATN.toCharArray());

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    }
}