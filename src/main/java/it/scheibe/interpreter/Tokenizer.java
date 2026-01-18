package it.scheibe.interpreter;

import java.util.ArrayList;
import java.util.List;

import it.scheibe.interpreter.token.Addition;
import it.scheibe.interpreter.token.Loop;
import it.scheibe.interpreter.token.Subtraction;
import it.scheibe.interpreter.token.Token;

public class Tokenizer {
	public static final String LINE_SPLITTER = "\\n(?=[^\\n\\tEND])";

	public static final Token[] TOKEN_TYPES = new Token[] {
			new Loop(null, null),
			new Addition(null, 0, null),
			new Subtraction(null, 0, null)
	};

	public static List<Token> tokenize(String input) {
		input = input.replaceAll(" ", "");
		return new Loop(null, new ArrayList<>()).tokenizeLevel(input);
	}
}