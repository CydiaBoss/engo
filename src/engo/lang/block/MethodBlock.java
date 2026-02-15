package engo.lang.block;

import engo.lang.Parameter;
import engo.lang.Type;

import engo.lang.BasicType;
import engo.lang.Value;
import engo.lang.Variable;
import engo.lang.tools.Error;

/**
 * <b>Method</b><br>
 * <br>
 * This Class contains the code that makes a<br>
 * method a method<br>
 * @author Andrew Wang
 * @category Block
 * @version 1.0
 * @since 0.1a
 */
public class MethodBlock extends Block{

	//Method's name
	private String name;
	//Method's return type
	private String returnType;
	//Method's parameters
	private Parameter[] params;
	//Method's return value
	private Value returnValue;
	
	/**
	 * Creates a Method block
	 * 
	 * @param superBlock
	 * The block above
	 * @param name
	 * The method's name
	 * @param returnType
	 * The method's return type
	 * @param params
	 * The method's parameters
	 */
	public MethodBlock(Block superBlock, String name, String returnType, Parameter[] params) {
		super(superBlock);
		this.name = name;
		this.returnType = returnType;
		this.params = params;
	}
	
	public String getName() {
		return name;
	}
	
	public String getReturnType() {
		return returnType;
	}
	
	public Parameter[] getParams() {
		return params;
	}
	
	public Value getReturnValue() {
		return returnValue;
	}

	@Override
	public void run() {
		invoke();
	}

	//used invoke to give the method the ability to add params.
	public Value invoke(Value... values){
		Type t = Type.match(returnType);
		Value locReturnVal = null;
		if(params != null) {
			if(values.length != params.length) {
				Error.printE(Error.InvaildCodeError.injectMsg("Too much or not enough arguments. "
						+ "\nPlease add/erase required arguments."));
			}
			if(params.length != 0) {
				for(int i = 0; i != params.length; i++) {
					Parameter p = params[i];
					Value v = values[i];
					if(p.getType() != v.getType()) {
						Error.printE(Error.IncorrectStateError.injectMsg("Incorrect arguments types."
								+ "\nArgument " + p.getName() + " might be " + p.getType() + "."
								+ "\nRecieved " + v.getType() + "."
								+ "\nPlease change it."));
					}
					// Registering the value
					addVariable(new Variable(this, p.getName(), p.getType(), v.getValue()));
				}
			}
		}
		
		
		
		for(Block b : getSubBlock()) {
			
			b.run();
			
			if(returnValue != null) {
				break;
			}
			
		}
		//System.out.println(Arrays.toString(getAllVariable()));
		if(returnType != null && (returnValue == null && t != BasicType.VOID)) {
			Error.printE(Error.IncorrectStateError.injectMsg("Return value was found empty."
					+ "\nPlease return something."));
		}
		locReturnVal = returnValue;
		returnValue = null;
		return locReturnVal;
	}

	@Override
	public String getType() {
		return "Method";
	}

	@Override
	public String toString() {
		return "[Method] " + name;
	}
}
