package it.scheibe.interpreter.token;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.scheibe.interpreter.Parameters;

public record Loop(String variable, List<Token> content) implements Token {
	private static final Pattern PATTERN = Pattern
			.compile("^LOOP(?<variable>.+?)DO\\n(?<content>.*?)\\nEND$", Pattern.DOTALL);

	@Override
	public Pattern getPattern() {
		return PATTERN;
	}

	@Override
	public Loop tokenize(Matcher result) {
		String variable = result.group("variable");
		// Remove first-level indentation from content
		String content = result.group("content").replaceAll("(?m)^\t", "");
		return new Loop(variable, tokenizeLevel(content));
	}

	@Override
	public Map<String, Integer> interpret(Map<String, Integer> input) {
		// Really run n times even if n is updated in the loop
		int n = input.getOrDefault(variable, Parameters.DEFAULT_VALUE);
		for (int i = Parameters.DEFAULT_VALUE; i < n; i++) {
			for (Token statement : content) {
				input = statement.interpret(input);
			}
			if (Parameters.UPDATE_IN_LOOP)
				n = input.getOrDefault(variable, Parameters.DEFAULT_VALUE);
		}
		return input;
	}
}
