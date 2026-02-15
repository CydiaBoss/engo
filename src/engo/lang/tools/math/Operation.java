package engo.lang.tools.math;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import engo.lang.Runtime;
import engo.lang.Variable;
import engo.lang.token.Token;
import engo.lang.token.TokenType;

/**
 * <b>Operation</b><br>
 * <br>
 * This Class converts an array of tokens into a<br>
 * boolean expression.<br>
 * @author Andrew Wang
 * @category Math
 * @version 1.0
 * @since 0.1a
 */
public class Operation {

	/**
	 * TODO Makes sure that an Expression in an array is valid
	 * 
	 * @param token
	 * The expression
	 * @return
	 * If it's valid
	 */
	public static boolean isValid(ArrayList<Token> token) {
		boolean expOp = false;
		boolean varID = false;
		boolean getAns = false;
		for(Token t : token) {
			if(t.getType() == TokenType.IDENTIFIER && !varID) {
				for(Variable var : Runtime.run.globalVar) {
					if(var.getName().equals(t.getToken())) {
						varID = true;
						expOp = true;
					}
				}
				if(!varID) return false;
			}else if(t.getType() == TokenType.NUMBER_LITERAL && !expOp && !varID) {
				expOp = true;
			}else if(t.getType() == TokenType.NUMBER_LITERAL && expOp && !varID) {
				return false;
			}else if(t.getType() == TokenType.OPERATOR && expOp) {
				expOp = false;
				if(t.getToken().equals("=")) {
					getAns = true;
				}
			}else if(t.getType() == TokenType.OPERATOR && !expOp) {
				return false;
			}else if(t.getType() == TokenType.TOKEN && !varID) {
				if(!t.getToken().equals("(") || !t.getToken().equals(")")) return false;
			}else if(t.getType() == TokenType.BOOLEAN_LITERAL && token.size() == 1 && !varID) {
				return true;
			}else if(!(t.getType() == TokenType.TOKEN || t.getType() == TokenType.OPERATOR || t.getType() == TokenType.EMPTY) && varID){
				return true;
			}
		}
		if(getAns && expOp) return true;
		return false;
	}
	
	/**
	 * | TODO | Takes a string equation and calculates it
	 * 
	 * @param token
	 * The equation
	 * @return
	 * The answer
	 * @throws ScriptException 
	 */
	public static int eval(ArrayList<Token> token) throws ScriptException {
		int mode = -1;
		Object toEval = null;
		if(token.get(0).getType() == TokenType.IDENTIFIER) {
			mode = 0; 
		}else if(token.get(0).getType() == TokenType.IDENTIFIER) {
			mode = 1;
		}
		
		if(mode == 0) {
			for(Variable var : Runtime.run.globalVar) {
				if(var.getName().equals(token.get(0).getToken())) {
					toEval = var.getValue();
				}
			}
		}else if(mode == 1) {
			ScriptEngineManager mgr = new ScriptEngineManager();
			ScriptEngine engine = mgr.getEngineByName("JavaScript");
			String foo = "40+2";
	    	System.out.println(engine.eval(foo));
		}
		return 0;
	}

}
