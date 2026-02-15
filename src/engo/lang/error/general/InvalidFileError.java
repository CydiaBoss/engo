package engo.lang.error.general;

import engo.lang.error.Error;

/**
 * {@link InvalidFileError} is thrown when<br/>
 * The file won't work or doesn't exist.
 * 
 * @author Andrew Wang
 * @version 1.0
 * @since 1.0
 */
public class InvalidFileError extends Error {

	/**
	 * The <i>message</i>
	 */
	private String msg;
	
	/**
	 * Throws an {@link InvalidFileError} with a <i>message</i>.<br/>
	 * Also this will auto-stop all processes.
	 * 
	 * @param msg
	 * The <i>message</i>
	 */
	public InvalidFileError(String msg) {
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
