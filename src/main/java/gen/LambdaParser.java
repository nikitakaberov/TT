package gen;

import common.Structure;
import lambdas.LambdaStructure;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LambdaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LET=1, IN=2, EQ=3, LBR=4, RBR=5, LAMBDA=6, DOT=7, VAR=8, WS=9;
	public static final int
		RULE_let_expression = 0, RULE_expression = 1, RULE_abstraction = 2, RULE_application = 3, 
		RULE_atomic = 4;
	public static final String[] ruleNames = {
		"let_expression", "expression", "abstraction", "application", "atomic"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'let'", "'in'", "'='", "'('", "')'", "'\\'", "'.'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "LET", "IN", "EQ", "LBR", "RBR", "LAMBDA", "DOT", "VAR", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Lambda.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LambdaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class Let_expressionContext extends ParserRuleContext {
		public Structure ret;
		public Token VAR;
		public Let_expressionContext def;
		public Let_expressionContext expr;
		public ExpressionContext expression;
		public TerminalNode LET() { return getToken(LambdaParser.LET, 0); }
		public TerminalNode VAR() { return getToken(LambdaParser.VAR, 0); }
		public TerminalNode EQ() { return getToken(LambdaParser.EQ, 0); }
		public TerminalNode IN() { return getToken(LambdaParser.IN, 0); }
		public List<Let_expressionContext> let_expression() {
			return getRuleContexts(Let_expressionContext.class);
		}
		public Let_expressionContext let_expression(int i) {
			return getRuleContext(Let_expressionContext.class,i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Let_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_let_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LambdaListener ) ((LambdaListener)listener).enterLet_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LambdaListener ) ((LambdaListener)listener).exitLet_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LambdaVisitor ) return ((LambdaVisitor<? extends T>)visitor).visitLet_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Let_expressionContext let_expression() throws RecognitionException {
		Let_expressionContext _localctx = new Let_expressionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_let_expression);
		try {
			setState(21);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LET:
				enterOuterAlt(_localctx, 1);
				{
				setState(10);
				match(LET);
				setState(11);
				((Let_expressionContext)_localctx).VAR = match(VAR);
				setState(12);
				match(EQ);
				setState(13);
				((Let_expressionContext)_localctx).def = let_expression();
				setState(14);
				match(IN);
				setState(15);
				((Let_expressionContext)_localctx).expr = let_expression();
				 ((Let_expressionContext)_localctx).ret =  LambdaStructure.let((((Let_expressionContext)_localctx).VAR!=null?((Let_expressionContext)_localctx).VAR.getText():null), ((Let_expressionContext)_localctx).def.ret, ((Let_expressionContext)_localctx).expr.ret);
				}
				break;
			case LBR:
			case LAMBDA:
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(18);
				((Let_expressionContext)_localctx).expression = expression();
				((Let_expressionContext)_localctx).ret = ((Let_expressionContext)_localctx).expression.ret;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public Structure ret;
		public ApplicationContext application;
		public AbstractionContext abstraction;
		public ApplicationContext application() {
			return getRuleContext(ApplicationContext.class,0);
		}
		public AbstractionContext abstraction() {
			return getRuleContext(AbstractionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LambdaListener ) ((LambdaListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LambdaListener ) ((LambdaListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LambdaVisitor ) return ((LambdaVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_expression);
		try {
			setState(33);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(23);
				((ExpressionContext)_localctx).application = application(0);
				setState(24);
				((ExpressionContext)_localctx).abstraction = abstraction();
				 ((ExpressionContext)_localctx).ret =  LambdaStructure.application(((ExpressionContext)_localctx).application.ret, ((ExpressionContext)_localctx).abstraction.ret); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(27);
				((ExpressionContext)_localctx).application = application(0);
				 ((ExpressionContext)_localctx).ret =  ((ExpressionContext)_localctx).application.ret; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(30);
				((ExpressionContext)_localctx).abstraction = abstraction();
				 ((ExpressionContext)_localctx).ret =  ((ExpressionContext)_localctx).abstraction.ret; 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AbstractionContext extends ParserRuleContext {
		public Structure ret;
		public Token VAR;
		public ExpressionContext expression;
		public TerminalNode LAMBDA() { return getToken(LambdaParser.LAMBDA, 0); }
		public TerminalNode VAR() { return getToken(LambdaParser.VAR, 0); }
		public TerminalNode DOT() { return getToken(LambdaParser.DOT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AbstractionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_abstraction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LambdaListener ) ((LambdaListener)listener).enterAbstraction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LambdaListener ) ((LambdaListener)listener).exitAbstraction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LambdaVisitor ) return ((LambdaVisitor<? extends T>)visitor).visitAbstraction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AbstractionContext abstraction() throws RecognitionException {
		AbstractionContext _localctx = new AbstractionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_abstraction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			match(LAMBDA);
			setState(36);
			((AbstractionContext)_localctx).VAR = match(VAR);
			setState(37);
			match(DOT);
			setState(38);
			((AbstractionContext)_localctx).expression = expression();
			((AbstractionContext)_localctx).ret =  LambdaStructure.abstraction((((AbstractionContext)_localctx).VAR!=null?((AbstractionContext)_localctx).VAR.getText():null), ((AbstractionContext)_localctx).expression.ret);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ApplicationContext extends ParserRuleContext {
		public Structure ret;
		public ApplicationContext app;
		public AtomicContext atomic;
		public AtomicContext atomic() {
			return getRuleContext(AtomicContext.class,0);
		}
		public ApplicationContext application() {
			return getRuleContext(ApplicationContext.class,0);
		}
		public ApplicationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_application; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LambdaListener ) ((LambdaListener)listener).enterApplication(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LambdaListener ) ((LambdaListener)listener).exitApplication(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LambdaVisitor ) return ((LambdaVisitor<? extends T>)visitor).visitApplication(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ApplicationContext application() throws RecognitionException {
		return application(0);
	}

	private ApplicationContext application(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ApplicationContext _localctx = new ApplicationContext(_ctx, _parentState);
		ApplicationContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_application, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(42);
			((ApplicationContext)_localctx).atomic = atomic();
			((ApplicationContext)_localctx).ret = ((ApplicationContext)_localctx).atomic.ret;
			}
			_ctx.stop = _input.LT(-1);
			setState(51);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ApplicationContext(_parentctx, _parentState);
					_localctx.app = _prevctx;
					_localctx.app = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_application);
					setState(45);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(46);
					((ApplicationContext)_localctx).atomic = atomic();
					((ApplicationContext)_localctx).ret = LambdaStructure.application(((ApplicationContext)_localctx).app.ret,((ApplicationContext)_localctx).atomic.ret);
					}
					} 
				}
				setState(53);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AtomicContext extends ParserRuleContext {
		public Structure ret;
		public Let_expressionContext let_expression;
		public Token VAR;
		public TerminalNode LBR() { return getToken(LambdaParser.LBR, 0); }
		public Let_expressionContext let_expression() {
			return getRuleContext(Let_expressionContext.class,0);
		}
		public TerminalNode RBR() { return getToken(LambdaParser.RBR, 0); }
		public TerminalNode VAR() { return getToken(LambdaParser.VAR, 0); }
		public AtomicContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomic; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LambdaListener ) ((LambdaListener)listener).enterAtomic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LambdaListener ) ((LambdaListener)listener).exitAtomic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LambdaVisitor ) return ((LambdaVisitor<? extends T>)visitor).visitAtomic(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomicContext atomic() throws RecognitionException {
		AtomicContext _localctx = new AtomicContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_atomic);
		try {
			setState(61);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBR:
				enterOuterAlt(_localctx, 1);
				{
				setState(54);
				match(LBR);
				setState(55);
				((AtomicContext)_localctx).let_expression = let_expression();
				setState(56);
				match(RBR);
				((AtomicContext)_localctx).ret =  ((AtomicContext)_localctx).let_expression.ret;
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(59);
				((AtomicContext)_localctx).VAR = match(VAR);
				((AtomicContext)_localctx).ret =  LambdaStructure.variable((((AtomicContext)_localctx).VAR!=null?((AtomicContext)_localctx).VAR.getText():null));
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
			return application_sempred((ApplicationContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean application_sempred(ApplicationContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\13B\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\5\2\30\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3$\n\3\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\64\n\5\f\5\16\5\67"+
		"\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6@\n\6\3\6\2\3\b\7\2\4\6\b\n\2\2\2"+
		"A\2\27\3\2\2\2\4#\3\2\2\2\6%\3\2\2\2\b+\3\2\2\2\n?\3\2\2\2\f\r\7\3\2\2"+
		"\r\16\7\n\2\2\16\17\7\5\2\2\17\20\5\2\2\2\20\21\7\4\2\2\21\22\5\2\2\2"+
		"\22\23\b\2\1\2\23\30\3\2\2\2\24\25\5\4\3\2\25\26\b\2\1\2\26\30\3\2\2\2"+
		"\27\f\3\2\2\2\27\24\3\2\2\2\30\3\3\2\2\2\31\32\5\b\5\2\32\33\5\6\4\2\33"+
		"\34\b\3\1\2\34$\3\2\2\2\35\36\5\b\5\2\36\37\b\3\1\2\37$\3\2\2\2 !\5\6"+
		"\4\2!\"\b\3\1\2\"$\3\2\2\2#\31\3\2\2\2#\35\3\2\2\2# \3\2\2\2$\5\3\2\2"+
		"\2%&\7\b\2\2&\'\7\n\2\2\'(\7\t\2\2()\5\4\3\2)*\b\4\1\2*\7\3\2\2\2+,\b"+
		"\5\1\2,-\5\n\6\2-.\b\5\1\2.\65\3\2\2\2/\60\f\4\2\2\60\61\5\n\6\2\61\62"+
		"\b\5\1\2\62\64\3\2\2\2\63/\3\2\2\2\64\67\3\2\2\2\65\63\3\2\2\2\65\66\3"+
		"\2\2\2\66\t\3\2\2\2\67\65\3\2\2\289\7\6\2\29:\5\2\2\2:;\7\7\2\2;<\b\6"+
		"\1\2<@\3\2\2\2=>\7\n\2\2>@\b\6\1\2?8\3\2\2\2?=\3\2\2\2@\13\3\2\2\2\6\27"+
		"#\65?";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}