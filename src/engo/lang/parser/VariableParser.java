package engo.lang.parser;

import engo.lang.block.Block;
import engo.lang.statement.VariableStatement;
import engo.lang.token.Token;
import engo.lang.token.TokenType;
import engo.lang.token.Tokenizer;

/**
 * <b>Variable Parser</b><br>
 * <br>
 * This Class helps identify and<br>
 * parser a Engo Variable Statement<br>
 * @author Andrew Wang
 * @category Parser
 * @version 1.0
 * @since 0.1a
 */
public class VariableParser extends Parser<Block>{

	@Override
	public boolean shouldParse(String line) {
		return line.matches("THIS( )+[a-zA-Z][a-zA-Z0-9]*( )+(CALLED( )+)?[a-zA-Z0-9]+( )+(EQUAL|=)( )+(\")?.*(\")?");
	}
	
	@Override
	public Block parse(Block superBlock, Tokenizer tokenizer) {
		tokenizer.nextToken(); //Skip "THIS"
		String type = tokenizer.nextToken().getToken(); //Get Var. Type
		String tokenCall = tokenizer.nextToken().getToken();
		if(tokenCall.equals("CALLED")) { // If token is CALLED
			tokenCall = tokenizer.nextToken().getToken(); // Skip to value
		}
		String name = tokenCall; // record the name of Var.
		tokenizer.nextToken(); //Skip "EQUAL" or the "="
		Token value = tokenizer.nextToken();
		Object valueData = null;
		if(value.getType() == TokenType.NUMBER_LITERAL) { // Is it a number
			valueData = Integer.valueOf(value.getToken());
		}else if(value.getType() == TokenType.STRING_LITERAL) { // Is it a string (ex: "blah, blah")
			valueData = String.valueOf(value.getToken());
		}else{ // It's an identifier
			valueData = superBlock.getVariable(value.getToken()).getValue();
		}
		
		return new VariableStatement(superBlock, name, type, valueData);
	}

}
