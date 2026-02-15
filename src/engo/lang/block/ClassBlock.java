package engo.lang.block;

import engo.lang.Type;

/**
 * <b>Class</b><br>
 * <br>
 * This Class contains the code that makes a<br>
 * class a class.<br>
 * @author Andrew Wang
 * @category Block
 * @version 1.0
 * @since 0.1a
 */
public class ClassBlock extends Block implements Type{
	
	//Class's name
	private String name;

	/**
	 * Creates the Class Block
	 * @param name
	 * The class' name
	 */
	public ClassBlock(String name) {
		super(null);
		this.name = name;
	}

	/**
	 * Gets the class' name
	 * 
	 * @return
	 * The name
	 */
	public String getName() {
		return name;
	}
	
	//If any, Runs the starter method
	@Override
	public void run() {
		for(Block b : getSubBlock()) {
			if(b instanceof MethodBlock) {
				MethodBlock m = (MethodBlock) b;
				if(m.getName().equals("starter") && m.getReturnType() == null && m.getParams() == null) {
					m.run();
				}
			}
		}
	}

	@Override
	public String getType() {
		return "Class";
	}

	@Override
	public String toString() {
		return "[Class] " + name;
	}

}
