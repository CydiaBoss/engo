package engo.lang;

/**
 * <b>Value</b><br>
 * <br>
 * This Class defines a value/data.<br>
 * @author Andrew Wang
 * @category Value
 * @version 1.0
 * @since 0.1a
 */
public class Value {

	//The value's type
	private Type type;
	//The value's value
	private Object value;
	
	/**
	 * Creates the new Value object
	 * 
	 * @param type
	 * The value's type
	 * @param value
	 * The value's value
	 */
	public Value(Type type, Object value) {
		this.type = type;
		this.value = value;
	}
	
	/**
	 * Gets the type
	 * 
	 * @return
	 * Value's type
	 */
	public Type getType() {
		return type;
	}
	
	/**
	 * Gets the value
	 * 
	 * @return
	 * Value's value
	 */
	public Object getValue() {
		return value;
	}
	
	/**
	 * Sets the value's value
	 * 
	 * @param value
	 * New value
	 */
	public void setValue(Object value) {
		this.value = value;
	}
}
