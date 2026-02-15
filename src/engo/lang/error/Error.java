package engo.lang.error;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * This is the {@link Error} File,<br/>
 * The parent file to all {@link Error}s 
 * 
 * @author Andrew Wang
 * @version 1.0
 * @since 1.0
 */
public abstract class Error{

	/**
	 * The {@link PrintStream} for all the {@link Error}
	 */
	protected final PrintStream P = System.err;
	
	/**
	 * Throws an {@link Error}
	 */
	protected Error() {
		this("", false);
	}
	
	/**
	 * Throws an {@link Error} with a <i>message</i>.
	 * 
	 * @param msg
	 * The <i>message</i>
	 */
	protected Error(String msg) {
		this(msg, false);
	}

	/**
	 * Throws an {@link Error} with a <i>message</i>.<br/>
	 * Has the ability to stop the process.
	 * 
	 * @param msg
	 * The <i>message</i>
	 * @param crash
	 * Whether to stop or not.
	 */
	protected Error(String msg, boolean crash) {
		P.println("A(n) " + this.getClass().getName().split("\\.")[this.getClass().getName().split("\\.").length - 1] + " has occurred. " + msg);
		if(crash) {
			P.println("Unable to Continue. Stopping...");
			stop();
		}
	}
	
	/**
	 * Get the {@link Error} <i>message</i>
	 * 
	 * @return
	 * The <i>message</i>
	 */
	protected abstract String getMsg();
	
	/**
	 * Stops all processes
	 */
	@SuppressWarnings("resource")
	protected void stop() {
		P.println("Press <Enter> to Continue.");
		new Scanner(System.in).nextLine();
		System.exit(0);
	}
}
