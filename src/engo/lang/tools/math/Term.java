package engo.lang.tools.math;

/**
 * <b>Terms</b><br>
 * <br>
 * This Class represents a mathematical<br>
 * term.<br>
 * @author Andrew Wang
 * @category Math
 * @version 1.0
 * @since 0.1a
 */
public class Term {

	/**
	 * The value
	 */
	final int value;
	/**
	 * Its location in a expression
	 */
	final int place;
	/**
	 * The operator
	 */
	final char operator;
	/**
	 * The first Term
	 */
	final Term fTerm;
	/**
	 * The order of operation
	 */
	int priority;
	/**
	 * The last Term
	 */
	final Term lTerm;
	/**
	 * The last Term
	 */
	final boolean bracket;
	
	/**
	 * Creates a term.
	 * 
	 * @param value
	 * The value of said term
	 * @param place
	 * The location in the expression
	 */
	@SuppressWarnings("null")
	public Term(int value, int place) {
		this.value = value;
		this.place = place;
		this.operator = (Character) null;
		this.fTerm = null;
		this.priority = (Integer) null;
		this.lTerm = null;	
		this.bracket = (Boolean) null;
	}

	/**
	 * Creates a operation.
	 * 
	 * @param operation
	 * The operation
	 * @param place
	 * The location in the expression
	 */
	@SuppressWarnings("null")
	public Term(char operation, int place) {
		this.value = (Integer) null;
		this.place = place;
		this.operator = operation;
		this.fTerm = null;
		this.priority = (Integer) null;
		this.lTerm = null;
		this.bracket = (Boolean) null;
	}
	
	/**
	 * Creates a simple equation.
	 * 
	 * @param fTerm
	 * The first {@code Term}
	 * @param priority
	 * The order of calculation
	 * @param lTerm
	 * The last {@code Term}
	 */
	@SuppressWarnings("null")
	public Term(Term fTerm, int priority, Term op, Term lTerm, boolean bracket) {
		this.value = (Integer) null;
		this.place = (Integer) null;
		this.operator = op.getOperation();
		this.fTerm = fTerm;
		this.priority = priority;
		this.lTerm = lTerm;
		this.bracket = bracket;
	}
	
	/**
	 * Returns the value
	 * 
	 * @return
	 * The value
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Returns the operation
	 * 
	 * @return
	 * The operation
	 */
	public char getOperation() {
		return operator;
	}
	
	/**
	 * Return the location in the equation
	 * 
	 * @return
	 * The location
	 */
	public int getPlace() {
		return place;
	}
	
	/**
	 * Return the first Term
	 * 
	 * @return
	 * The location
	 */
	public Term getFirstTerm() {
		return fTerm;
	}
	
	/**
	 * Return the priority
	 * 
	 * @return
	 * The location
	 */
	public int getPriority() {
		return priority;
	}
	
	/**
	 * Return the last Term
	 * 
	 * @return
	 * The location
	 */
	public Term getLastTerm() {
		return lTerm;
	}
	
	/**
	 * Check if it's in a bracket
	 * 
	 * @return
	 * The location
	 */
	public boolean isBracket() {
		return bracket;
	}
	
	/**
	 * Set the priority
	 * 
	 * @return
	 * The location
	 */
	public void setPriority(int p) {
		priority = p;
	}
}
