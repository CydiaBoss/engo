package engo.lang;

/**
 * <b>Parameter</b><br>
 * <br>
 * This Class defines a parameter.<br>
 * @author Andrew Wang
 * @category Parameter
 * @version 1.0
 * @since 0.1a
 */
public class Parameter {

	private String name;
	private Type type;
	
	public Parameter(Type type, String name) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}
}
