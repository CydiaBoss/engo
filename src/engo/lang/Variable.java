package engo.lang;

import engo.lang.block.Block;

/**
 * <b>Variable</b><br>
 * <br>
 * This Class defines a variable. Not the variable block.<br>
 * @author Andrew Wang
 * @category Value
 * @version 1.0
 * @since 0.1a
 */
public class Variable extends Value{
	
	/**
	 * Variable's current superblock
	 */
	private Block block;
	/**
	 * It's name
	 */
	private String name;
	
	/**
	 * Creates a variable object
	 * 
	 * @param block
	 * The superblock
	 * @param name
	 * The name
	 * @param type
	 * The type
	 * @param value
	 * What the variable holds
	 */
	public Variable(Block block, String name, Type type, Object value) {
		// Call original class
		super(type, value);
		this.block = block;
		this.name = name;
		//System.out.println("Created Variable: Name=" + name + " Type=" + type + " Value=" + value);
	}
	
	/**
	 * Gets the superblock'
	 * 
	 * @return
	 * the superblock
	 */
	public Block getBlock() {
		return block;
	}

	/**
	 * Get the variable's name
	 * 
	 * @return
	 * It's name
	 */
	public String getName() {
		return name;
	}
	
	public String toString() {
		return "[Variable] " + name;
	}
}
