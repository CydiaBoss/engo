package engo.lang.code;

import java.io.File;
import java.io.IOException;

import engo.lang.code.blocks.Code;
import engo.lang.code.statements.Statement;
import engo.lang.compile.parser.Parser;
import engo.lang.compile.token.Tokenizer;
import engo.lang.error.code.IllegalCodeError;

/**
 * This is the {@link Test} class.<br/>
 * <ul>Experimental Purposes <b>ONLY</b></ul>.
 * 
 * @author Andrew Wang
 * @deprecated
 * @version 1.0
 * @since 1.0
 */
@Deprecated
public final class Test {

	/**
	 * <ul>Experimental Purposes <b>ONLY</b></ul>.
	 * 
	 * @param args
	 * <i>null</i>
	 */
	public static void main(String[] args) {
		// Example Compiler
		File engo = new File("Engo.engo");
		try {
			engo.createNewFile();
		} catch (IOException e) {}
		Tokenizer tok = new Tokenizer(engo);
		for(String l : tok.getLines()) {
			Statement code = null;
			parser:
			for(Parser<?> p : Parser.PARS) {
				if(p.canParse(l)) {
					code = p.parse(Tokenizer.toTokens(l));
					break parser;
				}
			}
			if(code == null)
				new IllegalCodeError("Line \"" + l + "\" is an invalid function.");
			if(code instanceof Code)
				((Code) code).run();
		}
	}
}
