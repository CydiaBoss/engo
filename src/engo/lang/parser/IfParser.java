package engo.lang.parser;

import java.util.ArrayList;

import engo.lang.block.Block;
import engo.lang.block.IfBlock;
import engo.lang.token.Token;
import engo.lang.token.Tokenizer;
import engo.lang.tools.Error;
import engo.lang.tools.math.Operation;
/**
 * <b>If Parser</b><br>
 * <br>
 * This Class contains the code that makes an<br>
 * if an if.<br>
 * @author Andrew Wang
 * @category Parser
 * @version 1.0
 * @since 0.1a
 */
public class IfParser extends Parser<IfBlock>{

	@Override
	public boolean shouldParse(String line) {
		return line.matches("(ELSEIF |ELSE |IF )/([a-zA-Z0-9]+/)");
	}

	@Override
	public IfBlock parse(Block superBlock, Tokenizer tokenizer) {
		String declare = tokenizer.nextToken().getToken();
		char type = 'I';
		if(declare.equals("ELSE")) {
			type = 'E';
		}else if(declare.equals("ELSEIF")) {
			type = 'e';
		}
		tokenizer.nextToken();
		ArrayList<Token> con = new ArrayList<Token>();
		do{
			Token conP = tokenizer.nextToken();
			if(conP.getToken().equals(")")) {
				break;
			}
			con.add(conP);
		}while(true);
		if(!Operation.isValid(con)) Error.InvaildCodeError.injectMsg("An if block has an invalid boolean expression.");
		boolean conB = true;
		return new IfBlock(superBlock, conB, type);
	}

}
