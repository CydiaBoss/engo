package engo.lang.block;

import java.util.ArrayList;
import java.util.Collections;

import engo.lang.Runtime;
import engo.lang.Variable;
import engo.lang.tools.Error;

/**
 * <b>Block</b><br>
 * <br>
 * This Class is the skeleton of the other block classes<br>
 * Provides all basic functions to the block classes<br>
 * @author Andrew Wang
 * @category Skeleton
 * @version 1.0
 * @since 0.1a
 */
public abstract class Block {
	
	/**
	 * <i>Superblock</i><br><br>
	 * The Superblock of the current block
	 */
	private Block superBlock;
	/**
	 * <i>Subblocks</i><br><br>
	 * The Subblocks. The blocks and statements under the current block
	 */
	private ArrayList<Block> subBlock = new ArrayList<Block>();
	/**
	 * <i>Variables</i><br><br>
	 * Contains all the variables in the block
	 */
	private ArrayList<Variable> variable = new ArrayList<Variable>();
	
	/**
	 * <i>Block(Block superblock)</i><br><br>
	 * Creates a Block
	 * 
	 * @param superBlock
	 * The block ahead of this one
	 */
	public Block(Block superblock) {
		this.superBlock = superblock;
		
	}
	
	/**
	 * <i>getSuperBlock()</i><br><br>
	 * Gets the Superblock
	 * 
	 * @return
	 * Returns the superblock
	 */
	public Block getSuperBlock() {
		return superBlock;
	}
	
	/**
	 * <i>getBlockTree()</i><br><br>
	 * Returns all blocks in a family tree type of style.<br>
	 * Highest to Lowest.
	 * 
	 * @return 
	 * The family tree of the block (ArrayList)
	 */
	public ArrayList<Block> getBlockTree() {
		ArrayList<Block> blockTree = new ArrayList<Block>();
		Block curBlock = this;
		
		do{
			blockTree.add(curBlock);
			curBlock = curBlock.getSuperBlock();
		} while(curBlock != null);
		
		Collections.reverse(blockTree);
		
		return blockTree;
	}
	
	/**
	 * <i>addBlock(Block block)</i><br><br>
	 * Add a subblock to this block
	 * 
	 * @param block
	 * The subblock
	 */
	public void addBlock(Block block) {
		subBlock.add(block);
	}
	
	/**
	 * <i>getVariable(String name)</i><br><br>
	 * Retrieves a variable
	 * 
	 * @param name
	 * Uses the Variable name to identify the Variable
	 * @return
	 * The Variable you want
	 */
	public Variable getVariable(String name) {
		//Checks local variables
		for(Variable var : variable) {
			if(var.getName().equals(name)) {
				return var;
			}
		}
		
		//Check global variable
		for(Variable var : Runtime.run.globalVar) {
			if(var.getName().equals(name)) {
				return var;
			}
		}
		
		Error.printE(Error.InvaildCodeError.injectMsg("Variable " + name + " could not be found."));
		return null;
	}
	
	/**
	 * <i>addVariable(Variable var)</i><br><br>
	 * Add a Variable to the superblock
	 * 
	 * @param var
	 * The Variable you want to add
	 */
	public boolean addVariable(Variable var) {
		Runtime.run.globalVar.add(var);
		return variable.add(var);
	}
	
	/**
	 * <i>getSubBlock()</i><br><br>
	 * Retrieves all of the current block's subblocks
	 * 
	 * @return
	 * All subblock in an array
	 */
	public Block[] getSubBlock() {
		return subBlock.toArray(new Block[subBlock.size()]);
	}
	
	/**
	 * <i>run()</i><br><br>
	 * The method that was run the block
	 */
	public abstract void run();
	
	/**
	 * <i>getType()</i><br><br>
	 * Gets the original type
	 * @return
	 * The block's type
	 */
	public abstract String getType();
	
	/**
	 * <i>toString()</i><br><br>
	 * The Block to String
	 */
	@Override
	public abstract String toString();
}
