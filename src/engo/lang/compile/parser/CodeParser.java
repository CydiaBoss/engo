package engo.lang.compile.parser;

import engo.lang.Type;
import engo.lang.code.blocks.Code;
import engo.lang.compile.token.Token;

/**
 * This is the {@link Code} {@link Parser}
 * 
 * @author Andrew Wang
 * @version 1.0
 * @since 1.0
 */
public class CodeParser extends Parser<Code> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canParse(String code) {
		return code.matches("CODE " + Type.IDENTIFIER.getPattern() + "\\.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Code parse(Token[] code) {
		return new Code(code[1]);
	}

}
