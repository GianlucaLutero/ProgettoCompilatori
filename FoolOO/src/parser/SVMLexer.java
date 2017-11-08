// Generated from SVM.g4 by ANTLR 4.6
package parser;

import java.util.HashMap;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SVMLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PUSH=1, POP=2, ADD=3, SUB=4, MULT=5, DIV=6, STOREW=7, LOADW=8, BRANCH=9, 
		BRANCHEQ=10, BRANCHLESSEQ=11, JS=12, LOADRA=13, STORERA=14, LOADRV=15, 
		STORERV=16, LOADFP=17, STOREFP=18, COPYFP=19, LOADHP=20, STOREHP=21, PRINT=22, 
		HALT=23, COL=24, LABEL=25, NUMBER=26, WHITESP=27, ERR=28;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"PUSH", "POP", "ADD", "SUB", "MULT", "DIV", "STOREW", "LOADW", "BRANCH", 
		"BRANCHEQ", "BRANCHLESSEQ", "JS", "LOADRA", "STORERA", "LOADRV", "STORERV", 
		"LOADFP", "STOREFP", "COPYFP", "LOADHP", "STOREHP", "PRINT", "HALT", "COL", 
		"LABEL", "NUMBER", "WHITESP", "ERR"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'push'", "'pop'", "'add'", "'sub'", "'mult'", "'div'", "'sw'", 
		"'lw'", "'b'", "'beq'", "'bleq'", "'js'", "'lra'", "'sra'", "'lrv'", "'srv'", 
		"'lfp'", "'sfp'", "'cfp'", "'lhp'", "'shp'", "'print'", "'halt'", "':'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "PUSH", "POP", "ADD", "SUB", "MULT", "DIV", "STOREW", "LOADW", "BRANCH", 
		"BRANCHEQ", "BRANCHLESSEQ", "JS", "LOADRA", "STORERA", "LOADRV", "STORERV", 
		"LOADFP", "STOREFP", "COPYFP", "LOADHP", "STOREHP", "PRINT", "HALT", "COL", 
		"LABEL", "NUMBER", "WHITESP", "ERR"
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


	public int lexicalErrors=0;


	public SVMLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SVM.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 27:
			ERR_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void ERR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			 System.err.println("Invalid char: "+ getText()); lexicalErrors++;  
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\36\u00ba\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2\3\2\3\2\3\2\3\2\3\3"+
		"\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3"+
		"\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f"+
		"\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20"+
		"\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23"+
		"\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\32\3\32"+
		"\7\32\u009d\n\32\f\32\16\32\u00a0\13\32\3\33\3\33\5\33\u00a4\n\33\3\33"+
		"\3\33\7\33\u00a8\n\33\f\33\16\33\u00ab\13\33\5\33\u00ad\n\33\3\34\6\34"+
		"\u00b0\n\34\r\34\16\34\u00b1\3\34\3\34\3\35\3\35\3\35\3\35\3\35\2\2\36"+
		"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20"+
		"\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36\3"+
		"\2\5\4\2C\\c|\5\2\62;C\\c|\5\2\13\f\17\17\"\"\u00be\2\3\3\2\2\2\2\5\3"+
		"\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2"+
		"\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3"+
		"\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'"+
		"\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63"+
		"\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\3;\3\2\2\2\5@\3\2\2\2\7"+
		"D\3\2\2\2\tH\3\2\2\2\13L\3\2\2\2\rQ\3\2\2\2\17U\3\2\2\2\21X\3\2\2\2\23"+
		"[\3\2\2\2\25]\3\2\2\2\27a\3\2\2\2\31f\3\2\2\2\33i\3\2\2\2\35m\3\2\2\2"+
		"\37q\3\2\2\2!u\3\2\2\2#y\3\2\2\2%}\3\2\2\2\'\u0081\3\2\2\2)\u0085\3\2"+
		"\2\2+\u0089\3\2\2\2-\u008d\3\2\2\2/\u0093\3\2\2\2\61\u0098\3\2\2\2\63"+
		"\u009a\3\2\2\2\65\u00ac\3\2\2\2\67\u00af\3\2\2\29\u00b5\3\2\2\2;<\7r\2"+
		"\2<=\7w\2\2=>\7u\2\2>?\7j\2\2?\4\3\2\2\2@A\7r\2\2AB\7q\2\2BC\7r\2\2C\6"+
		"\3\2\2\2DE\7c\2\2EF\7f\2\2FG\7f\2\2G\b\3\2\2\2HI\7u\2\2IJ\7w\2\2JK\7d"+
		"\2\2K\n\3\2\2\2LM\7o\2\2MN\7w\2\2NO\7n\2\2OP\7v\2\2P\f\3\2\2\2QR\7f\2"+
		"\2RS\7k\2\2ST\7x\2\2T\16\3\2\2\2UV\7u\2\2VW\7y\2\2W\20\3\2\2\2XY\7n\2"+
		"\2YZ\7y\2\2Z\22\3\2\2\2[\\\7d\2\2\\\24\3\2\2\2]^\7d\2\2^_\7g\2\2_`\7s"+
		"\2\2`\26\3\2\2\2ab\7d\2\2bc\7n\2\2cd\7g\2\2de\7s\2\2e\30\3\2\2\2fg\7l"+
		"\2\2gh\7u\2\2h\32\3\2\2\2ij\7n\2\2jk\7t\2\2kl\7c\2\2l\34\3\2\2\2mn\7u"+
		"\2\2no\7t\2\2op\7c\2\2p\36\3\2\2\2qr\7n\2\2rs\7t\2\2st\7x\2\2t \3\2\2"+
		"\2uv\7u\2\2vw\7t\2\2wx\7x\2\2x\"\3\2\2\2yz\7n\2\2z{\7h\2\2{|\7r\2\2|$"+
		"\3\2\2\2}~\7u\2\2~\177\7h\2\2\177\u0080\7r\2\2\u0080&\3\2\2\2\u0081\u0082"+
		"\7e\2\2\u0082\u0083\7h\2\2\u0083\u0084\7r\2\2\u0084(\3\2\2\2\u0085\u0086"+
		"\7n\2\2\u0086\u0087\7j\2\2\u0087\u0088\7r\2\2\u0088*\3\2\2\2\u0089\u008a"+
		"\7u\2\2\u008a\u008b\7j\2\2\u008b\u008c\7r\2\2\u008c,\3\2\2\2\u008d\u008e"+
		"\7r\2\2\u008e\u008f\7t\2\2\u008f\u0090\7k\2\2\u0090\u0091\7p\2\2\u0091"+
		"\u0092\7v\2\2\u0092.\3\2\2\2\u0093\u0094\7j\2\2\u0094\u0095\7c\2\2\u0095"+
		"\u0096\7n\2\2\u0096\u0097\7v\2\2\u0097\60\3\2\2\2\u0098\u0099\7<\2\2\u0099"+
		"\62\3\2\2\2\u009a\u009e\t\2\2\2\u009b\u009d\t\3\2\2\u009c\u009b\3\2\2"+
		"\2\u009d\u00a0\3\2\2\2\u009e\u009c\3\2\2\2\u009e\u009f\3\2\2\2\u009f\64"+
		"\3\2\2\2\u00a0\u009e\3\2\2\2\u00a1\u00ad\7\62\2\2\u00a2\u00a4\7/\2\2\u00a3"+
		"\u00a2\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a9\4\63"+
		";\2\u00a6\u00a8\4\62;\2\u00a7\u00a6\3\2\2\2\u00a8\u00ab\3\2\2\2\u00a9"+
		"\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ad\3\2\2\2\u00ab\u00a9\3\2"+
		"\2\2\u00ac\u00a1\3\2\2\2\u00ac\u00a3\3\2\2\2\u00ad\66\3\2\2\2\u00ae\u00b0"+
		"\t\4\2\2\u00af\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1"+
		"\u00b2\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b4\b\34\2\2\u00b48\3\2\2\2"+
		"\u00b5\u00b6\13\2\2\2\u00b6\u00b7\b\35\3\2\u00b7\u00b8\3\2\2\2\u00b8\u00b9"+
		"\b\35\2\2\u00b9:\3\2\2\2\b\2\u009e\u00a3\u00a9\u00ac\u00b1\4\2\3\2\3\35"+
		"\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}