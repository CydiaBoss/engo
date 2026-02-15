package engo.lang.statement;

import engo.lang.BasicType;
import engo.lang.Type;
import engo.lang.Variable;
import engo.lang.block.Block;
import engo.lang.tools.Error;

/**
 * <b>Variable</b><br>
 * <br>
 * This Class contains the code that makes a<br>
 * variable statement a data container.<br>
 * @author Andrew Wang
 * @category Statement
 * @version 1.0
 * @since 0.1a
 */
public class VariableStatement extends Statement{

	private String type;
	private String name;
	private Object value;
	
	public VariableStatement(Block superBlock, String name, String type, Object value) {
		super(superBlock);
		this.name = name;
		this.type = type;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public void run() {
		Type t = Type.match(type);
		if(t == BasicType.VOID) {
			Error.printE(Error.IncorrectStateError.injectMsg("A variable can NOT be a void type. Plz change."));
		}
		//TODO Error
		getSuperBlock().addVariable(new Variable(getSuperBlock(), name, t, value));
	}

	@Override
	public String getType() {
		return "Variable";
	}

	@Override
	public String toString() {
		return "[Variable] " + name;
	}

}
