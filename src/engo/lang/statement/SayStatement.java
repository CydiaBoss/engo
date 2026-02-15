package engo.lang.statement;

import engo.lang.block.Block;
import engo.lang.token.TokenType;

/**
 * <b>Say</b><br>
 * <br>
 * This Class contains the code that makes a<br>
 * say statement a printing command.<br>
 * @author Andrew Wang
 * @category Statement
 * @version 1.0
 * @since 0.1a
 */
public class SayStatement extends Statement {
	
	private Object value;
	private TokenType type;

	public SayStatement(Block superBlock, Object value, TokenType type) {
		super(superBlock);
		this.value = value;
		this.type = type;
	}

	@Override
	public void run() {
		if(!type.equals(TokenType.IDENTIFIER)) {
			System.out.println(value);
		}else{
			String id = (String) value;
			System.out.println(getSuperBlock().getVariable(id).getValue());
		}
	}

	@Override
	public String getType() {
		return "Say";
	}

	@Override
	public String toString() {
		return "[Say]";
	}
}
