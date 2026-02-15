package engo.lang;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import engo.lang.block.Block;
import engo.lang.block.ClassBlock;
import engo.lang.block.IfBlock;
import engo.lang.block.MethodBlock;
import engo.lang.parser.AskParser;
import engo.lang.parser.ClassParser;
import engo.lang.parser.IfParser;
import engo.lang.parser.MethodParser;
import engo.lang.parser.MethodStarterParser;
import engo.lang.parser.Parser;
import engo.lang.parser.SayParser;
import engo.lang.parser.VariableParser;
import engo.lang.statement.Statement;
import engo.lang.token.Tokenizer;
import engo.lang.tools.Error;

/**
 * <b>Runtime</b><br>
 * <br>
 * This Class is the main class of Engo.<br>
 * @author Andrew Wang
 * @category Core
 * @version 1.0
 * @since 0.1a
 */
public class Runtime {
	
	/**
	 * The compiler
	 */
	public static Runtime run = null;
	
	/**
	 * The main method
	 * 
	 * @param args
	 * Holds the path to engo file and other settings
	 */
	public static void main(String args[]) {
		
		//Introduce the Language
		System.out.println("*Insert theme song* ENGO");
		System.out.println("By Andrew Wang");
		System.out.println("Starting Engo Compiler...");
		
		//Test if args are Empty (No longer need)
		//if(args == null) {
		//	Error.printE(Error.IncorrectStateError.injectMsg("No settings were found in command. Please add the file directory and settings if need."));
		//}
		
		//Setting
		boolean debug = false, //Debug Mode - Spits out more error
				hidden = false; //Hidden Mode - Hides console
		
		//File directory
		String fileDir = "";
		
		for(String s : args) {
			if(s.equals("-debug")) {
				debug = true;
			}else if(s.equals("-hidden")){
				hidden = true;
			}else if(s.endsWith(".engo")) {
				fileDir = s.replace('-', ' ');
			}else if(s.endsWith("\"")) {
				fileDir = s.substring(1, s.length() - 1);
			}else{
				if(s.startsWith("-")) {
					Error.printE(Error.UnknownSettingError.injectMsg("Setting " + s + " can not be identified. Please fix it."));
				}
				Error.printE(Error.InvaildCodeError.injectMsg("ID or Setting " + s + " could not be identified. Remove or fix it."));
			}
		}//Sets up terminal for use
		
		if(!hidden) {
			System.out.println("[Engo Compiler] Current Version: 1.0.");
			System.out.println("[Engo Compiler] Compiler Started.");
			System.out.println("[Engo Compiler] Compiling...");
		}//Sets up hidden mode
		
		String code = findFile(fileDir);
		
		//Creates the Compiler
		run = new Runtime(code, debug, hidden);
		
		//start
		run.run();
		
	}//Tries to run the file
	
	/**
	 * Finds and Verifies the file. Also converts it to code.
	 * 
	 * @param fileDir 
	 * The file directory
	 * @return 
	 * The code
	 */
	@SuppressWarnings("resource")
	private static String findFile(String fileDir) {

		//Sets up reader
		Scanner sc = null; 
		//Where the code will be stored
		String code = "";
		
		try {
			//Makes the file object
			File f = new File(fileDir);
			//Makes the scanner object
			sc = new Scanner(f);
			//Converts the code to a string
			while(sc.hasNextLine()){
				code = code + sc.nextLine();
			}
			
			return code;
		} catch (FileNotFoundException e) {
			//Tries to find file
			System.out.println("[Engo Compiler] ENGO file not found. Please enter the file directory:");
			sc = new Scanner(System.in);
			String s = sc.nextLine();
			
			if(s.endsWith(".engo")) {
				s = s.replace('-', ' ');
			}else if(s.endsWith("\"")) {
				s = s.substring(1, s.length() - 1);
			}
			
			code = findFile(s);
		}
		return code;
	}
	
	//The array containing all classes
	private ArrayList<ClassBlock> classes = new ArrayList<ClassBlock>();
	//The array containing all methods
	private ArrayList<MethodBlock> methods = new ArrayList<MethodBlock>();
	//The array containing all ifs
	private ArrayList<IfBlock> ifs = new ArrayList<IfBlock>();
	//The code in string form
	private String code;
	//The debug option
	private boolean debug;
	//The hidden option
	private boolean hidden;
	
	//For Dev Options
	public ArrayList<Variable> globalVar = new ArrayList<Variable>();
	
	//Get every parser
	private	Parser<?>[] parsers = new Parser<?>[]{
			new ClassParser(),
			new MethodParser(),
			new VariableParser(),
			new SayParser(),
			new AskParser(),
			new MethodStarterParser(),
			new IfParser()
		};
		
	//The class with the start method
	ClassBlock mainClass = null;	
	
	/**
	 * Creating the Compiler
	 * 
	 * @param code
	 * The code
	 */
	public Runtime(String code, boolean debug, boolean hidden) {
		this.code = code;
		this.debug = debug;
		this.hidden = hidden;
	}

	/**
	 * The run method. Starts the whole thing.
	 */
	public void run() {
		
		//The current block of code we are looking at
		Block curBlock = null;
		
		//The current line we are reading
		int lineNum = 0;
		
		//The current method
		MethodBlock mb = null;
		
		//Dividing the code into separate lines
		for(String line : code.split("\\.")) {
			line = line.trim();
			//If code reading is successful
			boolean success = false;
			lineNum++;
			//Splitting the line into tokens
			Tokenizer tokenizer = new Tokenizer(line);
			for(Parser<?> parser : parsers) {
				
				//checks if line is readable\valid
				if(parser.shouldParse(line)) {
					if(debug) {
						System.out.println("[Engo Compiler] Line " + lineNum + " > " + line + " > " + "being parsed by " + parser.getClass().getSimpleName());
					}
					
					//Creates a block with new code
					Block newBlock = parser.parse(curBlock, tokenizer);
					//Adds each class into the class array
					if(newBlock instanceof ClassBlock) {
						classes.add((ClassBlock) newBlock);
					//Adds each method into the block tree
					}else if(newBlock instanceof MethodBlock) {
						curBlock.getBlockTree().get(0).addBlock(newBlock); //made Subblock of Class
						methods.add((MethodBlock) newBlock);
						mb = (MethodBlock) newBlock;
					//If statement, made subblock of nearest method
					}else if(newBlock instanceof IfBlock) {
						ifs.add((IfBlock) newBlock); 
					}else if(newBlock instanceof Statement) {
						if(mb == null) {
							Error.printE(Error.IncorrectStateError.injectMsg("A Statements has been found outside of method. Please fix it."));
						}
						mb.addBlock(newBlock);
					//Add newBlock to currentBlock
					}else{
						curBlock.addBlock(newBlock);
					}
					curBlock = newBlock;
					success = true;
					break;
				}
			}
			
			//If all code was successful
			if(!success && !line.equals("")) {
				Error.printE(Error.InvaildCodeError.injectMsg("Invaild Code at Line " + lineNum + " \n"
						+ "> \"" + line + "\""));
			}
		}
		
		//Finds the main class
		for(ClassBlock c : classes) {
			for(Block b : c.getSubBlock()) {
				if(b instanceof MethodBlock) {
					MethodBlock m = (MethodBlock) b;
					if(m.getName().equals("starter") && m.getReturnType() == null && m.getParams() == null) {
						mainClass = c;
						break;
					}
				}
			}
		}
		
		//if no main class was found
		if(mainClass == null) {
			Error.printE(Error.MissingFileError.injectMsg("Main class could not be found... Plz add one."));
		}
		
		//Message for the coder
		if(!hidden) {
			System.out.println("[Engo Compiler] Compilation Complete.");
			System.out.println("[Engo Complier] Running...\n");
			if(!debug) System.out.println("~~~~~~~~~~~~~~~[Engo Console]~~~~~~~~~~~~~~~\n");
		}
		
		//If the program is in Debug Mode
		if(debug) System.out.println("\n~~~~~~~~~~~~~~~[Debug Console]~~~~~~~~~~~~~~~\n");
		//if everything is OK
		mainClass.run();
		
		//Finished
		if(!hidden) System.out.println("\n[Engo Complier] Finished\n");
		Error.promptEnter();
		
	}
	
	/**
	 * Get all identified Classes
	 * 
	 * @return
	 * ArrayList of Classes
	 */
	public ArrayList<ClassBlock> getClasses(){
		return classes;
	}
	
	/**
	 * Get all identified Methods
	 * 
	 * @return
	 * ArrayList of Methods
	 */
	public ArrayList<MethodBlock> getMethods(){
		return methods;
	}
	
	/**
	 * Get all identified If Statements
	 * 
	 * @return
	 * ArrayList of If Statements
	 */
	public ArrayList<IfBlock> getIfs(){
		return ifs;
	}
	
	/**
	 * Gets the wanted boolean
	 * 
	 * @param setting
	 * The setting you want
	 * @return
	 * The setting
	 */
	public boolean getSetting(String setting) {
		if(setting.equals("debug")) return debug;
		if(setting.equals("hidden")) return hidden;
		Error.printE(Error.UnknownSettingError.injectMsg("The setting " + setting + " you wanted could not be found."));
		return false;
	}
}
