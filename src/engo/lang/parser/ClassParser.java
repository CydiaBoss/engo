package engo.lang.parser;

import engo.lang.block.Block;
import engo.lang.block.ClassBlock;
import engo.lang.token.Tokenizer;

/**
 * <b>Class Parser</b><br>
 * <br>
 * This Class helps identify and<br>
 * parser a Engo Class<br>
 * @author Andrew Wang
 * @category Parser
 * @version 1.0
 * @since 0.1a
 */
public class ClassParser extends Parser<ClassBlock> {

	@Override
	public boolean shouldParse(String line) {
		return line.matches("CODE( )+[a-zA-Z][a-zA-Z0-9]*");
	}

	@Override
	public ClassBlock parse(Block superBlock, Tokenizer tokenizer) {
		tokenizer.nextToken(); //Skip "CODE" token.
		String className = tokenizer.nextToken().getToken(); //Get class name
		return new ClassBlock(className);
	}

}
