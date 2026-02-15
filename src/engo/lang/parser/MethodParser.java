package engo.lang.parser;

import java.util.ArrayList;

import engo.lang.Parameter;
import engo.lang.Type;
import engo.lang.block.Block;
import engo.lang.block.MethodBlock;
import engo.lang.token.Token;
import engo.lang.token.Tokenizer;

/**
 * <b>Method Parser</b><br>
 * <br>
 * This Class helps identify and<br>
 * parser a Method Class<br>
 * @author Andrew Wang
 * @category Parser
 * @version 1.0
 * @since 0.1a
 */
public class MethodParser extends Parser<MethodBlock>{

	@Override
	public boolean shouldParse(String line) {
		return line.matches("TASK( )+[a-zA-Z][a-zA-Z0-9]+((( )+NEED( )+\\((([a-zA-Z][a-zA-Z0-9]+( )+[a-zA-Z0-9]*(,( )+)?)+)?\\))?(( )+GIVE( )+[a-zA-Z][a-zA-Z0-9]+)?)?");
	}

	@Override
	public MethodBlock parse(Block superBlock, Tokenizer tokenizer) {
		tokenizer.nextToken(); //Skips the keyword "TASK"
		String name = tokenizer.nextToken().getToken(); //Record method's name
		// Checking Next Token
		Token token = tokenizer.nextToken(); 
		if(token.getToken().equals("NEED")) {
			//System.out.println("NEED identified");
			tokenizer.nextToken(); //Skips the (
			Token first = tokenizer.nextToken();
			ArrayList<Parameter> params = new ArrayList<>();
			if(!first.getToken().equals(")")) {
				String[] paramsData = new String[]{first.getToken(), null}; //0 = type 1 = id
				
				//Record the parameters
				while(tokenizer.hasNextToken()) {
					token = tokenizer.nextToken();
					
					//if the end of parameter, end
					if(token.getToken().equals(")")) {
						break;
					}
					
					if(paramsData[0] == null) {
						paramsData[0] = token.getToken();
					}else{
						paramsData[1] = token.getToken();
						params.add(new Parameter(Type.match(paramsData[0]), paramsData[1]));
						paramsData = new String[2]; //Reset
					}
				}
			}
			token = tokenizer.nextToken();
			if(!token.getToken().equals("GIVE")) {
				return new MethodBlock(superBlock, name, null, params.toArray(new Parameter[params.size()]));
			}
			//System.out.println("GIVE identified");
			String returnType = tokenizer.nextToken().getToken();
			return new MethodBlock(superBlock, name, returnType, params.toArray(new Parameter[params.size()]));
		// If it only finds GIVE
		}else if(token.getToken().equals("GIVE")) {
			//System.out.println("GIVE identified");
			String returnType = tokenizer.nextToken().getToken();
			return new MethodBlock(superBlock, name, returnType, null);
		}else{
			//System.out.println("Nothing identified");
			return new MethodBlock(superBlock, name, null, null);
		}
	}
}
