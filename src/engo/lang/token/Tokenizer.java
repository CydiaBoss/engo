package engo.lang.token;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import engo.lang.tools.Error;

/**
 * <b>Tokenizer</b><br>
 * <br>
 * This Class helps split the code into<br>
 * Pieces of tokens which will be parser.<br>
 * @author Andrew Wang
 * @category Token
 * @version 1.0
 * @since 0.1a
 */
public class Tokenizer {

	private ArrayList<TokenData> tokenData;
	private String cmd;
	private Token lastToken;
	private boolean uselessToken;
	
	public Tokenizer(String cmd) {
		this.cmd = cmd;
		this.tokenData = new ArrayList<TokenData>();
		
		//This will setup how my program will identify a OPERATOR
		tokenData.add(new TokenData(Pattern.compile("^(==|\\*|\\\\|\\+|\\-|\\%|\\<|\\>)"), TokenType.OPERATOR));
		//This will setup how my program will identify a BOOLEAN
		tokenData.add(new TokenData(Pattern.compile("^(true|false)"), TokenType.BOOLEAN_LITERAL));
		//This will setup how my program will identify a NUMBER
		tokenData.add(new TokenData(Pattern.compile("^((-)?[0-9]+)"), TokenType.NUMBER_LITERAL));
		//This will setup how my program will identify an IDENTIFIER
		tokenData.add(new TokenData(Pattern.compile("^([a-zA-Z][a-zA-Z0-9]*)"), TokenType.IDENTIFIER));
		//This will setup how my program will identify a STRING
		tokenData.add(new TokenData(Pattern.compile("^(\".*\")"), TokenType.STRING_LITERAL));
		
		for (String str : new String[]{ "=", "\\(", "\\)", "\\.", "\\," }) {
			tokenData.add(new TokenData(Pattern.compile("^(" + str + ")"), TokenType.TOKEN));
		}
	}
	
	/**
	 * Gets the next token
	 * 
	 * @return
	 * The next token (if any)
	 */
	public Token nextToken() {
		cmd = cmd.trim();
		
		//Check if token is useless
		if(uselessToken) {
			uselessToken = false;
			return lastToken;	
		}
		//Check if token is empty
		if(cmd.isEmpty()) {
			lastToken = new Token("", TokenType.EMPTY);
			return lastToken;
		}
		//Checks for the next token type
		for(TokenData data : tokenData) {
			Matcher match = data.getPattern().matcher(cmd);
			//If match found
			if(match.find()) {
				//Get s the token ready for parsing
				String token = match.group().trim();
				cmd = match.replaceFirst("");
				//Remove double quote from Strings
				if(data.getType() == TokenType.STRING_LITERAL) {
					lastToken = new Token(token.substring(1, token.length() - 1), TokenType.STRING_LITERAL);
					return lastToken;
				}else{
					lastToken = new Token(token, data.getType());
					return lastToken;
				}
			}
		}
		Error.printE(Error.InvaildCodeError.injectMsg("Code " + cmd + " is unparserable! Plz fix it."));
		return null;
	}
	
	/**
	 * Test if the tokenizer has another token to parse
	 * 
	 * @return
	 * If a token exists
	 */
	public boolean hasNextToken() {
		return !cmd.isEmpty();
	}
	
	/**
	 * Test if token is useless
	 */
	public void uselessToken() {
		if(lastToken != null) {
			this.uselessToken = true;
		}
	}
	
	/**
	 * Returns all tokens in {@code ArrayList} form
	 * 
	 * @return
	 * An {@code ArrayList} with all tokens
	 */
	public ArrayList<Token> getTokens() {
		Tokenizer t = new Tokenizer(cmd);
		ArrayList<Token> tAL = new ArrayList<Token>();
		while(true) {
			if(hasNextToken()) break;
			tAL.add(t.nextToken());
		}
		return tAL;
	}
}
