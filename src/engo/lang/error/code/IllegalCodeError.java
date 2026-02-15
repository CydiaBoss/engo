package engo.lang.error.code;

import engo.lang.error.Error;

/**
 * {@link IllegalCodeError} is thrown when<br/>
 * The code has bad syntax.
 * 
 * @author Andrew Wang
 * @version 1.0
 * @since 1.0
 */
public class IllegalCodeError extends Error {

	/**
	 * The <i>message</i>
	 */
	private String msg;
	
	/**
	 * Throws an {@link IllegalCodeError} with a <i>message</i>.<br/>
	 * Also this will auto-stop all processes.
	 * 
	 * @param msg
	 * The <i>message</i>
	 */
	public IllegalCodeError(String msg) {
		super(msg, true);
		this.msg = msg;
	}
	
	/**
	 * Returns the <i>message</i>
	 * 
	 * @return
	 * The <i>message</i>
	 */
	@Override
	protected String getMsg() {
		return msg;
	}

}
