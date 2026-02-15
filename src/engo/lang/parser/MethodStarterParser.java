package engo.lang.parser;

import java.util.ArrayList;

import engo.lang.Value;
import engo.lang.block.Block;
import engo.lang.statement.MethodStarterStatement;
import engo.lang.token.Token;
import engo.lang.token.Tokenizer;

/**
 * <b>Method Starter Parser</b><br>
 * <br>
 * This Class helps identify and<br>
 * parser a Engo Run Statement<br>
 * @author Andrew Wang
 * @category Parser
 * @version 1.0
 * @since 0.1a
 */
public class MethodStarterParser extends Parser<MethodStarterStatement>{
    
	@Override
	public boolean shouldParse(String line) {
		return line.matches("RUN( )+[a-zA-Z][a-zA-Z0-9]*\\(([a-zA-Z][a-zA-Z0-9]*(,( )+)?)?\\)");
	}

	@Override
	public MethodStarterStatement parse(Block superBlock, Tokenizer tokenizer) {
		tokenizer.nextToken(); //Skip RUN
		String id = tokenizer.nextToken().getToken(); //Record id
		tokenizer.nextToken(); //Skip (
		ArrayList<Value> values = new ArrayList<Value>();
		Token token = tokenizer.nextToken();
		if(!token.getToken().equals(")")) {
			
			while(tokenizer.hasNextToken()) {
				token = tokenizer.nextToken();
				if(token.getToken().equals(")")) {
					break;
				}
				values.add(new Value(token.getType().getType(), token.getToken()));
			}
		}
		return new MethodStarterStatement(superBlock, id, values);
	}

}
