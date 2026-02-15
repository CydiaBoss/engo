package engo.lang.code.blocks;

import java.util.ArrayList;

import engo.lang.code.variable.Variable;
import engo.lang.compile.token.Token;
import engo.lang.error.code.IllegalCodeError;

/**
 * This is the {@link Code} class.<br/>
 * It defines a code block in <b>Engo</b>
 * 
 * @author Andrew Wang
 * @version 1.0
 * @since 1.0
 */
public class Code extends Block {

	/**
	 * Loaded {@link Code}
	 */
	private final static ArrayList<Code> CODE = new ArrayList<Code>();
	
	/**
	 * The {@link Code}'s <i>global</i> {@link Variable}
	 */
	private final ArrayList<Variable> VAR = new ArrayList<Variable>();
	
	/**
	 * The {@link Code}'s <i>identifier</i>
	 */
	private Token id;
	
	/**
	 * The {@link Code}
	 * 
	 * @param id
	 * The {@link Code}'s <i>identifier</i>
	 */
	public Code(Token id) {
		super(null);
		this.id = id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addVar(Variable var) {
		// Check for Duplicates
		for(Variable v : VAR)
			if(v.getId().getToken().equals(var.getId().getToken()))
				new IllegalCodeError("Variables with same identifiers were found.");
		VAR.add(var);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Variable getVar(Token id) {
		for(Variable v : VAR)
			if(v.getId().getToken().equals(id.getToken()))
				return v;
		new IllegalCodeError("Variable " + id.getToken() + " doesn't exist.");
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		System.out.println("Code " + id.getToken() + " is added.");
	}

	/**
	 * Returns the {@link Code}'s <i>identifier</i>
	 * 
	 * @return
	 * The {@link Code}'s <i>identifier</i>
	 */
	public Token getID() {
		return id;
	}
	
	/**
	 * Get the loaded {@link Code}s
	 */
	public final static ArrayList<Code> getAllCode() {
		return CODE;
	}
}
