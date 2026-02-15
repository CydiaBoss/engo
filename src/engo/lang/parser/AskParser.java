package engo.lang.parser;

import engo.lang.Type;
import engo.lang.block.Block;
import engo.lang.statement.AskStatement;
import engo.lang.token.Tokenizer;

public class AskParser extends Parser<AskStatement> {

	@Override
	public boolean shouldParse(String line) {
		return line.matches("ASK( )+(\")?.*(\")?( )+GET( )+[a-zA-Z][a-zA-Z0-9]+( )+(CALLED( )+)?[a-zA-Z][a-zA-Z0-9]+");
	}

	@Override
	public AskStatement parse(Block superBlock, Tokenizer tokenizer) {
		tokenizer.nextToken(); //Skip ASK
		String quest = tokenizer.nextToken().getToken(); //Record Question
		tokenizer.nextToken(); //Skipped GET
		String type = tokenizer.nextToken().getToken(); //Record Var. Type
		String name = tokenizer.nextToken().getToken(); //Record name
		//if next token is CALLED
		if(name.equals("CALLED")) {
			name = tokenizer.nextToken().getToken(); //Record real name
		}
		return new AskStatement(superBlock, Type.match(type), quest, name);
	}

}
