package engo.lang.token;

import engo.lang.BasicType;

/**
 * <b>Token Type</b><br>
 * <br>
 * This Class helps identify a token's type<br>
 * @author Andrew Wang
 * @category Token
 * @version 1.0
 * @since 0.1a
 */
public enum TokenType {
	
	/**
	 * A Token.
	 * Represents ( ) . , etc
	 */
	TOKEN(BasicType.VOID),
	
	/**
	 * An name that starts with a letter. Anything else is a number or letter
	 * Represents 'hello' 'main' 'apple' etc 
	 */
	IDENTIFIER(BasicType.VOID),
	
	/**
	 * An Operator.
	 * Represents + - * / % < > = 
	 */
	OPERATOR(BasicType.VOID),
	
	/**
	 * A Number
	 * Represents 3 7.54 -1 etc
	 */
	NUMBER_LITERAL(BasicType.NUMBER),
	
	/**
	 * Anything enclosed in double quotes
	 * Represents "Hello World!" "I am a String"
	 */
	STRING_LITERAL(BasicType.STRING), 
	
	/**
	 * True or False
	 */
	BOOLEAN_LITERAL(BasicType.BOOLEAN),
	
	/**
	 * Absolutely nothing. Not a space.
	 */
	EMPTY(BasicType.VOID);

	private BasicType type;
	
	TokenType(BasicType type) {
		this.type = type;
	}
	
	public BasicType getType() {
		return type;
	}
}
