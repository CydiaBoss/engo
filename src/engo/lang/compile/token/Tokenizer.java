package engo.lang.compile.token;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import engo.lang.Type;
import engo.lang.error.code.IllegalCodeError;
import engo.lang.error.general.InvalidFileError;

/**
 * This is the {@link Tokenizer} class.<br/>
 * This reads and correctly identifies what token is what.
 * 
 * @author Andrew Wang
 * @version 1.0
 * @since 1.0
 */
public class Tokenizer {
	
	/**
	 * The <i>code</i>
	 */
	private ArrayList<String> lines = new ArrayList<String>();
	
	/**
	 * The {@link Tokenizer}
	 */
	public Tokenizer(File program) {
		// Initialize
		Scanner sc = null;
		try {
			sc = new Scanner(program);
		} catch (FileNotFoundException e) {
			new InvalidFileError("The given file couldn't be found.");
		}
		// Gets all the lines of code
		while(sc.hasNextLine()) 
			lines.add(sc.nextLine());
	}
	
	/**
	 * Returns the {@link String} {@link ArrayList<>} of {@link Token}s
	 * 
	 * @return
	 * The {@link String} {@link ArrayList<>}
	 */
	public ArrayList<String> getLines() {
		return lines;
	}
	
	/**
	 * Converts a {@link String} to an {@link Array} of {@link Token}s
	 * 
	 * @param code
	 * The {@link String}
	 * 
	 * @return
	 * The {@link Token} {@link Array}
	 */
	public final static Token[] toTokens(String code) {
		// Initialization
		int i = 0;
		String[] line = code.split(" ");
		Token[] tokens = new Token[line.length];
		// Parse
		for(int j = 0; j < line.length; j++) {
			// If a String is detected
			if(line[j].startsWith("\"")) {
				String str = "";
				strGetter:
				do {
					try{
						str += line[j];
						if(line[j].endsWith("\""))
							break strGetter;
						// renters the removed space
						str += " ";
						j++;
					}catch(ArrayIndexOutOfBoundsException e) {
						new IllegalCodeError("Line " + (i + 1) + " has an incomplete string.");
					}
				}while(true);
				tokens[j] = new Token(str, Type.STRING);
			}else{
				System.out.println(line[j]);
				// Gets rid of any periods except for Double Types
				if(line[j].endsWith("."))
					line[j] = line[j].replaceAll("\\.", "");
				System.out.println(line[j]);
				tokens[j] = new Token(line[j], Type.identify(line[j]));
			}
		}
		return tokens;
	}
}
