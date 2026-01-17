package it.scheibe.interpreter.token;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.scheibe.interpreter.Parameters;

public record Subtraction(String variable, int constant, String output) implements Token {
	private static final Pattern PATTERN = Pattern
			.compile("(?<output>.+?):=(?<variable>.+?)-(?<constant>\\d+)");

	@Override
	public Pattern getPattern() {
		return PATTERN;
	}

	@Override
	public Subtraction tokenize(Matcher result) {
		return new Subtraction(
				result.group("variable"),
				Integer.parseInt(result.group("constant")),
				result.group("output"));
	}

	@Override
	public Map<String, Integer> interpret(Map<String, Integer> input) {
		input.put(output, constant - input.getOrDefault(variable, Parameters.DEFAULT_VALUE));
		return input;
	}
}
