// Generated from FOOL.g4 by ANTLR 4.6
package parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FOOLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SEMIC=1, COLON=2, COMMA=3, EQ=4, ASM=5, PLUS=6, MINUS=7, TIMES=8, DIV=9, 
		TRUE=10, FALSE=11, LPAR=12, RPAR=13, CLPAR=14, CRPAR=15, IF=16, THEN=17, 
		ELSE=18, LET=19, IN=20, VAR=21, FUN=22, INT=23, BOOL=24, INTEGER=25, ID=26, 
		WS=27, LINECOMENTS=28, BLOCKCOMENTS=29, ERR=30;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"SEMIC", "COLON", "COMMA", "EQ", "ASM", "PLUS", "MINUS", "TIMES", "DIV", 
		"TRUE", "FALSE", "LPAR", "RPAR", "CLPAR", "CRPAR", "IF", "THEN", "ELSE", 
		"LET", "IN", "VAR", "FUN", "INT", "BOOL", "DIGIT", "INTEGER", "CHAR", 
		"ID", "WS", "LINECOMENTS", "BLOCKCOMENTS", "ERR"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "':'", "','", "'=='", "'='", "'+'", "'-'", "'*'", "'/'", 
		"'true'", "'false'", "'('", "')'", "'{'", "'}'", "'if'", "'then'", "'else'", 
		"'let'", "'in'", "'var'", "'fun'", "'int'", "'bool'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "SEMIC", "COLON", "COMMA", "EQ", "ASM", "PLUS", "MINUS", "TIMES", 
		"DIV", "TRUE", "FALSE", "LPAR", "RPAR", "CLPAR", "CRPAR", "IF", "THEN", 
		"ELSE", "LET", "IN", "VAR", "FUN", "INT", "BOOL", "INTEGER", "ID", "WS", 
		"LINECOMENTS", "BLOCKCOMENTS", "ERR"
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


	   //there is a much better way to do this, check the ANTLR guide
	   //I will leave it like this for now just becasue it is quick
	   //but it doesn't work well
	   public int lexicalErrors=0;


	public FOOLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "FOOL.g4"; }

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
		case 31:
			ERR_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void ERR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			 System.out.println("Invalid char: "+ getText()); lexicalErrors++; 
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2 \u00c6\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3"+
		"\t\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3"+
		"\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3"+
		"\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3"+
		"\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3"+
		"\31\3\32\3\32\3\33\6\33\u0092\n\33\r\33\16\33\u0093\3\34\3\34\3\35\3\35"+
		"\3\35\7\35\u009b\n\35\f\35\16\35\u009e\13\35\3\36\3\36\3\36\3\36\3\37"+
		"\3\37\3\37\3\37\7\37\u00a8\n\37\f\37\16\37\u00ab\13\37\3\37\3\37\3 \3"+
		" \3 \3 \3 \3 \3 \3 \3 \7 \u00b8\n \f \16 \u00bb\13 \3 \3 \3 \3 \3 \3!"+
		"\3!\3!\3!\3!\2\2\"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r"+
		"\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\2\65"+
		"\33\67\29\34;\35=\36?\37A \3\2\b\4\2C\\c|\5\2\13\f\17\17\"\"\4\2\f\f\17"+
		"\17\4\2,,\61\61\3\2,,\3\2\61\61\u00cb\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3"+
		"\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\65\3\2\2\2\29\3"+
		"\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\3C\3\2\2\2\5E\3\2\2"+
		"\2\7G\3\2\2\2\tI\3\2\2\2\13L\3\2\2\2\rN\3\2\2\2\17P\3\2\2\2\21R\3\2\2"+
		"\2\23T\3\2\2\2\25V\3\2\2\2\27[\3\2\2\2\31a\3\2\2\2\33c\3\2\2\2\35e\3\2"+
		"\2\2\37g\3\2\2\2!i\3\2\2\2#l\3\2\2\2%q\3\2\2\2\'v\3\2\2\2)z\3\2\2\2+}"+
		"\3\2\2\2-\u0081\3\2\2\2/\u0085\3\2\2\2\61\u0089\3\2\2\2\63\u008e\3\2\2"+
		"\2\65\u0091\3\2\2\2\67\u0095\3\2\2\29\u0097\3\2\2\2;\u009f\3\2\2\2=\u00a3"+
		"\3\2\2\2?\u00ae\3\2\2\2A\u00c1\3\2\2\2CD\7=\2\2D\4\3\2\2\2EF\7<\2\2F\6"+
		"\3\2\2\2GH\7.\2\2H\b\3\2\2\2IJ\7?\2\2JK\7?\2\2K\n\3\2\2\2LM\7?\2\2M\f"+
		"\3\2\2\2NO\7-\2\2O\16\3\2\2\2PQ\7/\2\2Q\20\3\2\2\2RS\7,\2\2S\22\3\2\2"+
		"\2TU\7\61\2\2U\24\3\2\2\2VW\7v\2\2WX\7t\2\2XY\7w\2\2YZ\7g\2\2Z\26\3\2"+
		"\2\2[\\\7h\2\2\\]\7c\2\2]^\7n\2\2^_\7u\2\2_`\7g\2\2`\30\3\2\2\2ab\7*\2"+
		"\2b\32\3\2\2\2cd\7+\2\2d\34\3\2\2\2ef\7}\2\2f\36\3\2\2\2gh\7\177\2\2h"+
		" \3\2\2\2ij\7k\2\2jk\7h\2\2k\"\3\2\2\2lm\7v\2\2mn\7j\2\2no\7g\2\2op\7"+
		"p\2\2p$\3\2\2\2qr\7g\2\2rs\7n\2\2st\7u\2\2tu\7g\2\2u&\3\2\2\2vw\7n\2\2"+
		"wx\7g\2\2xy\7v\2\2y(\3\2\2\2z{\7k\2\2{|\7p\2\2|*\3\2\2\2}~\7x\2\2~\177"+
		"\7c\2\2\177\u0080\7t\2\2\u0080,\3\2\2\2\u0081\u0082\7h\2\2\u0082\u0083"+
		"\7w\2\2\u0083\u0084\7p\2\2\u0084.\3\2\2\2\u0085\u0086\7k\2\2\u0086\u0087"+
		"\7p\2\2\u0087\u0088\7v\2\2\u0088\60\3\2\2\2\u0089\u008a\7d\2\2\u008a\u008b"+
		"\7q\2\2\u008b\u008c\7q\2\2\u008c\u008d\7n\2\2\u008d\62\3\2\2\2\u008e\u008f"+
		"\4\62;\2\u008f\64\3\2\2\2\u0090\u0092\5\63\32\2\u0091\u0090\3\2\2\2\u0092"+
		"\u0093\3\2\2\2\u0093\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094\66\3\2\2"+
		"\2\u0095\u0096\t\2\2\2\u00968\3\2\2\2\u0097\u009c\5\67\34\2\u0098\u009b"+
		"\5\67\34\2\u0099\u009b\5\63\32\2\u009a\u0098\3\2\2\2\u009a\u0099\3\2\2"+
		"\2\u009b\u009e\3\2\2\2\u009c\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d:"+
		"\3\2\2\2\u009e\u009c\3\2\2\2\u009f\u00a0\t\3\2\2\u00a0\u00a1\3\2\2\2\u00a1"+
		"\u00a2\b\36\2\2\u00a2<\3\2\2\2\u00a3\u00a4\7\61\2\2\u00a4\u00a5\7\61\2"+
		"\2\u00a5\u00a9\3\2\2\2\u00a6\u00a8\n\4\2\2\u00a7\u00a6\3\2\2\2\u00a8\u00ab"+
		"\3\2\2\2\u00a9\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ac\3\2\2\2\u00ab"+
		"\u00a9\3\2\2\2\u00ac\u00ad\b\37\2\2\u00ad>\3\2\2\2\u00ae\u00af\7\61\2"+
		"\2\u00af\u00b0\7,\2\2\u00b0\u00b9\3\2\2\2\u00b1\u00b8\n\5\2\2\u00b2\u00b3"+
		"\7\61\2\2\u00b3\u00b8\n\6\2\2\u00b4\u00b5\7,\2\2\u00b5\u00b8\n\7\2\2\u00b6"+
		"\u00b8\5? \2\u00b7\u00b1\3\2\2\2\u00b7\u00b2\3\2\2\2\u00b7\u00b4\3\2\2"+
		"\2\u00b7\u00b6\3\2\2\2\u00b8\u00bb\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9\u00ba"+
		"\3\2\2\2\u00ba\u00bc\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bc\u00bd\7,\2\2\u00bd"+
		"\u00be\7\61\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c0\b \2\2\u00c0@\3\2\2\2"+
		"\u00c1\u00c2\13\2\2\2\u00c2\u00c3\b!\3\2\u00c3\u00c4\3\2\2\2\u00c4\u00c5"+
		"\b!\4\2\u00c5B\3\2\2\2\t\2\u0093\u009a\u009c\u00a9\u00b7\u00b9\5\b\2\2"+
		"\3!\2\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}