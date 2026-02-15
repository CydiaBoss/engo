package engo.lang.code.blocks;

import java.util.ArrayList;

import engo.lang.code.statements.Statement;
import engo.lang.code.variable.Variable;
import engo.lang.compile.token.Token;

/**
 * This is the parent class of all {@link Block}s
 * 
 * @author Andrew Wang
 * @version 1.0
 * @since 1.0
 */
public abstract class Block extends Statement{
	
	/**
	 * The Super Global {@link Variable}s
	 */
	private static final ArrayList<Variable> VAR = new ArrayList<Variable>();
	
	/**
	 * The Super{@link Block}
	 */
	private final Block superblock;
	
	/**
	 * The Sub{@link Block}
	 */
	private final ArrayList<Block> subblock = new ArrayList<Block>();
	
	/**
	 * The {@link Block}
	 * 
	 * @param superblock
	 * The {@link Block} above the current {@link Block}
	 */
	protected Block(Block superblock) {
		this.superblock = superblock;
	}
	
	/**
	 * Gets the Super{@link Block}
	 * 
	 * @return
	 * the Super{@link Block}
	 */
	public final Block getSuperblock() {
		return superblock;
	}
	
	/**
	 * Gets the Sub{@link Block}
	 * 
	 * @return
	 * the Sub{@link Block}
	 */
	public final ArrayList<Block> getSubBlocks() {
		return subblock;
	}
	
	/**
	 * Adds a local {@link Variable} to the {@link Block}
	 * 
	 * @param var
	 * The {@link Variable}
	 */
	protected abstract void addVar(Variable var);
	
	/**
	 * Gets a local {@link Variable} from the {@link Block}
	 * 
	 * @param id
	 * The {@link Variable}'s <i>identifier</i>
	 * 
	 * @return
	 * The {@link Variable}
	 */
	protected abstract Variable getVar(Token id);

	/**
	 * Retrieves the desired global {@link Variable}
	 * 
	 * @param id
	 * The {@link Variable}'s <i>identifier</i>
	 * 
	 * @return
	 * The {@link Variable}
	 */
	public static final Variable getGlobVar(Token id) {
		for(Variable v : VAR) {
			if(v.getId().getToken().equals(id.getToken()))
				return v;
		}
		return null;
	}

	/**
	 * Adds a global {@link Variable}
	 * 
	 * @param var
	 * The {@link Variable}
	 */
	public static final void addGlobVar(Variable var) {
		VAR.add(var);
	}
}
