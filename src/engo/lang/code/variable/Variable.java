package engo.lang.code.variable;

import engo.lang.Type;
import engo.lang.compile.token.Token;
import engo.lang.error.code.IllegalCodeError;

/**
 * This is the {@link Variable} class.<br/>
 * It defines a {@link Variable}.
 * 
 * @author Andrew Wang
 * @version 1.0
 * @since 1.0
 */
public class Variable {

	/**
	 * The <i>identifier</i>
	 */
	private Token id;
	/**
	 * The {@link Type}
	 */
	private Type type;
	/**
	 * The <i>value</i>
	 */
	private Object value;
	
	/**
	 * The {@link Variable}
	 * 
	 * @param id
	 * The {@link Variable}'s <i>identifier</i>
	 * @param type
	 * The {@link Variable}'s {@link Type}
	 * @param value
	 * The {@link Variable}'s <i>value</i>
	 */
	public Variable(Token id, Type type, Object value) {
		if(!id.getType().equals(Type.IDENTIFIER))
			new IllegalCodeError("A variable with an invalid identifier was found.");
		this.id = id;
		this.type = type;
		if(!Type.identify("" + value).equals(type))
			new IllegalCodeError("A variable has a different value than the one assigned.");
		this.value = value;
	}

	/**
	 * Returns the {@link Variable}'s <i>value</i>
	 * 
	 * @return
	 * The {@link Variable}'s <i>value</i>
	 */
	public final Object getValue() {
		return value;
	}

	/**
	 * Sets the {@link Variable}'s new <i>value</i>
	 * 
	 * @param value
	 * The new <i>value</i>
	 */
	public final void setValue(Object value) {
		this.value = value;
	}

	/**
	 * Returns the {@link Variable}'s <i>identifier</i>
	 * 
	 * @return
	 * The {@link Variable}'s <i>identifier</i>
	 */
	public final Token getId() {
		return id;
	}

	/**
	 * Returns the {@link Variable}'s {@link Type}
	 * 
	 * @return
	 * The {@link Variable}'s {@link Type}
	 */
	public final Type getType() {
		return type;
	}
}
