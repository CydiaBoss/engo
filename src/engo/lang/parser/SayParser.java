package engo.lang.parser;

import engo.lang.block.Block;
import engo.lang.statement.SayStatement;
import engo.lang.token.Token;
import engo.lang.token.Tokenizer;

/**
 * <b>Say Parser</b><br>
 * <br>
 * This Class helps identify and<br>
 * parser a Engo Say Statement<br>
 * @author Andrew Wang
 * @category Parser
 * @version 1.0
 * @since 0.1a
 */
public class SayParser extends Parser<SayStatement> {
	
	@Override
	public boolean shouldParse(String line) {
		return line.matches("SAY( )+((\".*\")|([a-zA-Z][a-zA-Z0-9]*))");
	}

	@Override
	public SayStatement parse(Block superBlock, Tokenizer tokenizer) {
		tokenizer.nextToken(); //Skipped "say"
		Token token = tokenizer.nextToken();
		Object value = token.getToken();
		return new SayStatement(superBlock, value, token.getType());
	}

}
