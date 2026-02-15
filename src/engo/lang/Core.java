package engo.lang;

import java.io.File;

import engo.lang.error.runtime.InvalidParameterError;
import engo.lang.compile.Compiler;

/**
 * This is the <b>Engo</b> {@link Core} File.<br/>
 * This will direct the user to the correct location,<br/>
 * Whether the {@link Compiler} or the {@link Runner}
 * 
 * @author Andrew Wang
 * @version 1.0
 * @since 1.0
 */
public class Core {

	/**
	 * {@code Main Method}
	 * 
	 * @param args
	 * The <i>parameters</i> that will determine what to read and how to interpret it.
	 */
	public static void main(String[] args) {
		// Settings
		boolean compile = false, run = false;
		String path = "";
		File program = null;
		// Argument Parsing
		for(int i = 0; i < args.length; i++) {
			if(args[i].equals("-c"))
				compile = true;
			else if(args[i].equals("-r"))
				run = true;
			else if(args[i].startsWith("\"")) {
				pathGetter:
				do {
					try{
						path += args[i];
						if(args[i].endsWith("\""))
							break pathGetter;
						i++;
					}catch(ArrayIndexOutOfBoundsException e) {
						new InvalidParameterError("The file's pathname is incomplete", true);
					}
				}while(true);
				program = new File(path.substring(1, path.length() - 1));
			}else{
				new InvalidParameterError("The prompt " + args[i] + " is invalid.", false);
			}
		}
		// Validation
		if((!run && !compile) || program == null)
			new InvalidParameterError("No function or file path found.", true);
		// Direction
		if(compile);
			Compiler.start(program);
		if(run);
			// TODO Runner
	}
}