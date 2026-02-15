package engo.lang.statement;

import java.util.InputMismatchException;
import java.util.Scanner;

import engo.lang.BasicType;
import engo.lang.Type;
import engo.lang.Variable;
import engo.lang.block.Block;
import engo.lang.tools.Error;

/**
 * <b>Ask</b><br>
 * <br>
 * This Class contains the code that makes an<br>
 * Ask an Ask.<br>
 * @author Andrew Wang
 * @category Statement
 * @version 1.0
 * @since 0.1a
 */
public class AskStatement extends Statement {

	private Scanner sc = new Scanner(System.in);
	
	private Type type;
	private String quest;
	private String name;
	
	public AskStatement(Block superBlock, Type type, String quest, String name) {
		super(superBlock);
		this.type = type;
		this.quest = quest; 
		this.name = name;
	}

	@Override
	public void run() {
		
		//if Question was inputed
		if(quest != "") {
			System.out.println(quest);
		}
		
		try {
			if(type == BasicType.STRING) {
				String ui = sc.nextLine();
				getSuperBlock().addVariable(new Variable(getSuperBlock(), name, type, ui));
			}else if(type == BasicType.NUMBER) {
				int ui = sc.nextInt();
				getSuperBlock().addVariable(new Variable(getSuperBlock(), name, type, ui));
			}else if(type == BasicType.BOOLEAN) {
				boolean ui = sc.nextBoolean();
				getSuperBlock().addVariable(new Variable(getSuperBlock(), name, type, ui));
			}else{
				Error.printE(Error.IncorrectStateError.injectMsg("ASK command only supports basic types. Sorry :("));
			}
		}catch(InputMismatchException e){
			Error.printE(Error.MismatchError.injectMsg("The value you entered do not match the type you want. Plz change it."));
		}
	}

	@Override
	public String getType() {
		return "Ask";
	}

	@Override
	public String toString() {
		return "[Ask] " + name;
	}

}
