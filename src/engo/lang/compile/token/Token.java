package engo.lang.compile.token;

import engo.lang.Type;

/**
 * This is the {@link Token} class.<br/>
 * It defines a <i>token</i>.
 * 
 * @author Andrew Wang
 * @version 1.0
 * @since 1.0
 */
public class Token {

	/**
	 * The Actual <i>Token</i>
	 */
	private final String token;
	/**
	 * The {@link Token} {@link Type}
	 */
	private final Type type;
	
	/**
	 * The {@link Token}
	 * 
	 * @param token
	 * The actual <i>token</i>
	 * @param type
	 * The {@link Token} {@link Type}
	 */
	public Token(String token, Type type) {
		this.token = token;
		this.type = type;
		// TODO Create an algorithm that identifies what the identifier is.
	}

	/**
	 * Returns the {@link Token} as a {@link String}
	 * 
	 * @return
	 * The {@link Token} as a {@link String}
	 */
	public final String getToken() {
		return token;
	}

	/**
	 * Returns the {@link Token} {@link Type}
	 * 
	 * @return
	 * The {@link Type}
	 */
	public final Type getType() {
		return type;
	}
}
