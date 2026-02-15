package engo.lang.parser;

import engo.lang.block.Block;
import engo.lang.token.Tokenizer;

/**
 * <b>Parser</b><br>
 * <br>
 * This Class is the skeleton of all Parser Classes<br>
 * Provides required commands to perform as a<br>
 * Parser Class<br>
 * @author Andrew Wang
 * @category Skeleton
 * @version 1.0
 * @since 0.1a
 */
public abstract class Parser<T extends Block> {

	// Takes code and checks if the code is in the correct parser
	public abstract boolean shouldParse(String line);
	
	// Parses the code
	public abstract T parse(Block superBlock, Tokenizer tokenizer);
}
