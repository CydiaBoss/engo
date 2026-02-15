package engo.lang.compile;

import java.io.File;
import java.io.IOException;

import engo.lang.code.blocks.Block;
import engo.lang.code.blocks.Code;
import engo.lang.code.statements.Statement;
import engo.lang.compile.parser.Parser;
import engo.lang.compile.token.Tokenizer;
import engo.lang.error.code.IllegalCodeError;
import engo.lang.error.general.InvalidFileError;

/**
 * This is the {@link Compiler} class.<br/>
 * It will convert an <b>.engo</b> file into readable<br/>
 * Computer code.
 * 
 * @author Andrew Wang
 * @version 1.0
 * @since 1.0
 */
public class Compiler {
	
	/**
	 * This will start the <i>compilation</i> process
	 * 
	 * @param program
	 * The <i>code</i>
	 */
	public static void start(File program) {
		// Validation
		if(!program.exists() || program.isDirectory())
			new InvalidFileError(program.getAbsolutePath() + " is invalid.");
		// Reading
		File engo = new File("Engo.engo");
		try {
			engo.createNewFile();
		} catch (IOException e) {}
		Tokenizer tok = new Tokenizer(engo);
		// Parsing
		Code curCode = null;
		Code main = null;
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
			if(code instanceof Code) {
				Code.getAllCode().add((Code) code);
				curCode = (Code) code;
			}
		}
	}
}
