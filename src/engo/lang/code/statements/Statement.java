package engo.lang.code.statements;

/**
 * This is the parent class of all <i>code</i>
 * 
 * @author Andrew Wang
 * @version 1.0
 * @since 1.0
 */
public abstract class Statement {

	/**
	 * Determines how the <i>code</i> should be executed
	 */
	protected abstract void run();
}
