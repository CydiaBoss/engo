package engo.lang.tools;

import engo.lang.token.Tokenizer;
import engo.lang.tools.math.Operation;

/**
 * <b>Experiment</b><br>
 * <br>
 * This Class is used for testing purposes.<br>
 * @author Andrew Wang
 * @category Core
 * @version 1.0
 * @since 0.1a
 */
@Deprecated
public class Experiment {

	/**
	 * Methods created/used in this class are testing purposes only
	 */
	
	public static void main(String[] args) {
		Tokenizer t = new Tokenizer("5 + 2 = 73 +");
		if(Operation.isValid(t.getTokens())) {
			System.out.println("VALID!");
		}else{
			System.out.println("FAIL!");
		}
	}

}
