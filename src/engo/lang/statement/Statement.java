package engo.lang.statement;

import engo.lang.block.Block;

/**
 * <b>Statement</b><br>
 * <br>
 * This Class is the skeleton of a statement.<br>
 * @author Andrew Wang
 * @category Skeleton
 * @version 1.0
 * @since 0.1a
 */
public abstract class Statement extends Block {

	public Statement(Block superBlock) {
		super(superBlock);
	}

	@Override
	public abstract void run();

	@Override
	public abstract String getType();

}
