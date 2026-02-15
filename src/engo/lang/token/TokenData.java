package engo.lang.token;

import java.util.regex.Pattern;

/**
 * <b>Token Data</b><br>
 * <br>
 * This Class helps keep token data.<br>
 * @author Andrew Wang
 * @category Token
 * @version 1.0
 * @since 0.1a
 */
public class TokenData {

	private Pattern pattern;
	private TokenType type;
	
	public TokenData(Pattern pattern, TokenType type) {
		this.pattern = pattern;
		this.type = type;
	}
	
	public Pattern getPattern() {
		return pattern;
	}
	
	public TokenType getType() {
		return type;
	}
}
