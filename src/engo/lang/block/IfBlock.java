package engo.lang.block;

import java.util.ArrayList;

public class IfBlock extends Block {
	
	/**
	 * The run condition
	 */
	private boolean con;
	/**
	 * Holds all the elseif's and else's
	 */
	private ArrayList<IfBlock> ifs;
	
	/**
	 * 
	 * @param superblock
	 * @param condition
	 * @param declare
	 */
	public IfBlock(Block superblock, boolean condition, char blockType) {
		super(superblock);
		this.con = condition;
	}

	@Override
	public void run() {
		if(con) {
			for(Block b : getSubBlock()) {
				b.run();
			}
		}
	}

	@Override
	public String getType() {
		return "If";
	}

	@Override
	public String toString() {
		return "[If]";
	}

}
