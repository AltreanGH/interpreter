package it.scheibe.interpreter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.scheibe.interpreter.token.Token;

public class Interpreter {
	public static void main(String[] args) throws Exception {
		if (args.length == 0)
			throw new Exception("Please provide a path to a file!");

		// Read the input file
		String code = "";
		try {
			code = Files.readString(Path.of(args[0]));
		} catch (IOException e) {
			throw new Exception("The file at '" + args[0] + "' could not be found!");
		}

		// Parse initial variable assignments
		Map<String, Integer> vars = new HashMap<>();
		for (int i = 1; i < args.length; i++) {
			try {
				String[] arg = args[i].split("=");
				vars.put(arg[0], Integer.parseInt(arg[1]));
			} catch (Exception e) {
				throw new Exception("An error occured while parsing the argument '" + args[i] + "'!");
			}
		}

		// Interpret the given LOOP file
		List<Token> tokenized = Tokenizer.tokenize(code);
		System.out.println(new Program(tokenized).interpret(vars));
	}
}