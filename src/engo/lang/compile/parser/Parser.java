package engo.lang.compile.parser;

import engo.lang.code.statements.Statement;
import engo.lang.compile.token.Token;

/**
 * This is the parent class of all {@link Parser}s
 * 
 * @param E
 * The linked {@link Statement}
 * 
 * @author Andrew Wang
 * @version 1.0
 * @since 1.0
 */
public abstract class Parser<E extends Statement> {

	/**
	 * All {@link Parser}
	 */
	public static final Parser<?>[] PARS = {
			new CodeParser()
	};
	
	/**
	 * Verifies if the code is <i>parsable</i> by said {@link Parser}
	 * 
	 * @param code
	 * The code
	 * 
	 * @return
	 * If it is <i>parsable</i>
	 */
	public abstract boolean canParse(String code);
	
	/**
	 * Parses the code 
	 * 
	 * @param code
	 * The code as {@link Token}s
	 * 
	 * @return
	 * A fully parsed {@link Statement}
	 */
	public abstract E parse(Token[] code);
}
