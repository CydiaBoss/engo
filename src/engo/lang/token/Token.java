package engo.lang.token;

/**
 * <b>Token</b><br>
 * <br>
 * This Class helps identify a token<br>
 * @author Andrew Wang
 * @category Token
 * @version 1.0
 * @since 0.1a
 */
public class Token {

	/**
	 * The token's value
	 */
	private String token;
	/**
	 * The token's type
	 */
	private TokenType type;
	
	/**
	 * Creates a token
	 * 
	 * @param token
	 * The token
	 * @param type
	 * The type
	 */
	public Token(String token, TokenType type) {
		this.token = token;	
		this.type = type;
	}

	/**
	 * Returns the token as a String
	 * 
	 * @return
	 * The token as a String
	 */
	public String getToken() {
		return token;
	}
	
	/**
	 * Returns the token as itself
	 * 
	 * @return
	 * The token in its true form
	 */
	public Object getRealToken() {
		if(type.equals(TokenType.NUMBER_LITERAL)) {
			int real = Integer.parseInt(token);
			return real;
		}else if(type.equals(TokenType.BOOLEAN_LITERAL)) {
			boolean real = Boolean.parseBoolean(token);
			return real;
		}else{
			return token;
		}
	}

	public TokenType getType() {
		return type;
	}
	
}
