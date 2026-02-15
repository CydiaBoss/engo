package engo.lang.statement;

import java.util.ArrayList;

import engo.lang.Runtime;
import engo.lang.Value;
import engo.lang.block.Block;
import engo.lang.block.MethodBlock;
import engo.lang.tools.Error;

/**
 * <b>Method Starter</b><br>
 * <br>
 * This Class contains the code that makes a<br>
 * Run a Run.<br>
 * @author Andrew Wang
 * @category Statement
 * @version 1.0
 * @since 0.1a
 */
public class MethodStarterStatement extends Statement {

	private String id;
	
	private ArrayList<Value> paramsData;
	
	public MethodStarterStatement(Block superBlock, String id, ArrayList<Value> values) {
		super(superBlock);
		this.id = id;
		this.paramsData = values;
	}

	@Override
	public void run() {
		boolean success = false;
		for(MethodBlock m : Runtime.run.getMethods()) {
			if(m.getName().equals(id)) {
				Value[] valueData = new Value[paramsData.size()];
				int i = 0;
				for(Value v : paramsData) {
					valueData[i] = v;
					i++;
				}
				m.invoke(valueData);
				success = true;
			}
		}
		if(!success) {
			Error.printE(Error.IncorrectStateError.injectMsg("Method " + id + " couldn't be found. Plz fix it."));
		}
	}

	@Override
	public String getType() {
		return "Method Starter";
	}

	@Override
	public String toString() {
		return "[Method Starter] " + id;
	}

}
