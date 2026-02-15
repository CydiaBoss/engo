package engo.lang;

import engo.lang.compile.token.Token;
import engo.lang.error.code.IllegalCodeError;

/**
 * This is the {@link Type} class.<br/>
 * This defines all the <i>token</i> types. 
 * 
 * @author Andrew Wang
 * @version 1.0
 * @since 1.0
 */
public enum Type {

	/**
	 * This is an <i>integer<i>
	 */
	INTEGER("(-)?[0-9]+"),
	/**
	 * This is a <i>real number<i>
	 */
	DOUBLE("(-)?[0-9]+\\.[0-9]+"),
	/**
	 * This is a <i>boolean<i>
	 */
	BOOLEAN("true|false"),
	/**
	 * This is a <i>char<i>
	 */
	CHAR("'(.){1}'"),
	/**
	 * This is a <i>string<i>
	 */
	STRING("\"[^\"]*\""),
	/**
	 * This is a name for a <i>variable</i>, <i>method</i>, <i>etc</i>
	 */
	IDENTIFIER("[A-Za-z]+[A-Za-z0-9]*"),
	/**
	 * This is a symbol
	 */
	SIGN("\\W");
	
	/**
	 * The <i>pattern</i>
	 */
	private String pattern;
	
	/**
	 * The {@link Type}
	 * 
	 * @param pattern
	 * The pattern to identify the token
	 */
	Type(String pattern) {
		this.pattern = pattern;
	}
	
	/**
	 * Returns the pattern
	 * 
	 * @return
	 * The pattern
	 */
	public String getPattern() {
		return pattern;
	}
	
	/**
	 * Determines the {@link Token} {@link Type}
	 * 
	 * @param token
	 * The {@link Token}
	 * 
	 * @return
	 * The the {@link Token}'s {@link Type}
	 */
	public static Type identify(String token) {
		for(Type t : values()) {
			if(token.matches(t.getPattern()))
				return t;
		}
		new IllegalCodeError(token + " is illegal.");
		return null;
	}
}
