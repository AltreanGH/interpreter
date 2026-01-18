package it.scheibe.interpreter;

import java.util.ArrayList;
import java.util.List;

import it.scheibe.interpreter.token.Loop;
import it.scheibe.interpreter.token.Token;

public class Tokenizer {
	public static final String NEW_TOKEN_REGEX = "\\n(?=[^\\n\\tEND])";

	public static final String COMMENT_REGEX = "(?s)//.*?(?=\\n)|/\\*.*?\\*/";

	public static List<Token> tokenize(String input) {
		// Preprocess the program
		input = input.replaceAll("(?m)^\\s*\\n| ", "");
		if (Parameters.ENABLE_COMMENTS)
			input = input.replaceAll(COMMENT_REGEX, "");
		return new Loop(null, new ArrayList<>()).tokenizeLevel(input);
	}
}