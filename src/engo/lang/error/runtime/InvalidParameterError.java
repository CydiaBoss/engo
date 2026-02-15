package engo.lang.error.runtime;

import engo.lang.error.Error;

/**
 * {@link InvalidParameterError} is thrown when<br/>
 * The runtime parameters are invalid.
 * 
 * @author Andrew Wang
 * @version 1.0
 * @since 1.0
 */
public class InvalidParameterError extends Error {

	/**
	 * The <i>message</i>
	 */
	private final String msg;
	
	/**
	 * Throws an {@link InvalidParameterError} with a <i>message</i>.<br/>
	 * Also this has the ability to stop all processes
	 * 
	 * @param msg
	 * The <i>message</i>
	 * @param crash
	 * Whether to stop all processes or not
	 */
	public InvalidParameterError(String msg, boolean crash) {
		super(msg, crash);
		this.msg = msg;
	}

	/**
	 * Returns the <i>message</i>
	 * 
	 * @return
	 * The <i>message</i>
	 */
	@Override
	public String getMsg() {
		return msg;
	}
}
