package engo.lang.tools;

import java.util.Scanner;

/**
 * <b>Error</b><br>
 * <br>
 * This Class helps organize errors.<br>
 * @author Andrew Wang
 * @category Core
 * @version 1.0
 * @since 0.1a
 */
public enum Error{

	/**
	 * Issued when Engo code is found invalid
	 */
	InvaildCodeError("An Invaild Code Error has occurred"),
	/**
	 * Issued when the state of the code/file is incorrect
	 */
	IncorrectStateError("An Incorrect State Error has occurred"),
	/**
	 * Issued when something doesn't match
	 */
	MismatchError("A Mismatch Error has occurred"),
	/**
	 * Issued when a file is missing
	 */
	MissingFileError("A Missing File Error has occurred"),
	/**
	 * Issued when settings are wrong
	 */
	UnknownSettingError("An Unknown Setting Error has occured"),
	/**
	 * Issued when something inside compiler is wrong
	 */
	InternalError("An Internal Error has occurred");
	
	/**
	 * The message
	 */
	private String msg;
	
	/**
	 * The Error Object
	 *  
	 * @param msg
	 * The error message
	 */
	Error(String msg) {
		this.msg = msg;
	}

	/**
	 * Gets the default error message
	 * 
	 * @return
	 * The default message
	 */
	public String getDefaultMsg() {
		return msg;
	}
	
	/**
	 * Creates a modified error message.
	 * 
	 * @param str
	 * The additional message
	 * 
	 * @return
	 * The custom message
	 */
	public String injectMsg(String str) {
		return "[Engo] " + msg + " > " +str;
	}
	
	/**
	 * Prints the error message. Then stops Engo.
	 * 
	 * @param Emsg
	 * The error message
	 */
	public static void printE(String Emsg) {
		System.err.println(Emsg);
		promptEnter();
		System.exit(0);
	}
	
	/**
	 * Prints the error message. [Optional] Crashable
	 * 
	 * @param Emsg
	 * The error message
	 * @param crash
	 * Do you want to crash it?
	 */
	public static void printE(String Emsg, boolean crash) {
		System.err.println(Emsg);
		if(crash) {
			promptEnter();
			System.exit(0);
		}
	}
	/**
	 * Prompts the user to press enter.
	 */
	public static void promptEnter() {
		@SuppressWarnings("resource")
		Scanner enter = new Scanner(System.in);
		System.err.println("Press <ENTER> to close...");
		enter.nextLine();
	}
}
