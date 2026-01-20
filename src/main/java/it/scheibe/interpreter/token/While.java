package it.scheibe.interpreter.token;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.scheibe.interpreter.Parameters;
import it.scheibe.interpreter.InterpretationException.ParsingException;

public record While(String variable, int constant, List<Token> content) implements Token {
	// TODO only != is supported as of now
	private static final Pattern PATTERN = Pattern
			.compile("^WHILE(?<variable>.+?)!=(?<constant>\\d+?)DO\\n(?<content>.*?)\\nEND$", Pattern.DOTALL);

	@Override
	public Pattern getPattern() {
		return PATTERN;
	}

	@Override
	public While tokenize(Matcher result) throws ParsingException {
		String variable = result.group("variable");
		int constant = Integer.parseInt(result.group("constant"));
		// Remove first-level indentation from content
		String content = result.group("content").replaceAll("(?m)^\t", "");
		return new While(variable, constant, tokenizeLevel(content));
	}

	@Override
	public Map<String, Integer> interpret(Map<String, Integer> input) {
		// Here the variable is always updated
		while (input.getOrDefault(variable, Parameters.DEFAULT_VALUE) != constant) {
			for (Token statement : content) {
				input = statement.interpret(input);
			}
		}
		return input;
	}
}
