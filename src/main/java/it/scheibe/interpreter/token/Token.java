package it.scheibe.interpreter.token;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.scheibe.interpreter.Tokenizer;

public interface Token {
	Pattern getPattern();

	Token tokenize(Matcher result);

	Map<String, Integer> interpret(Map<String, Integer> input);

	default List<Token> tokenizeLevel(String input) {
		List<Token> res = new ArrayList<>();
		String[] lines = input.split(Tokenizer.LINE_SPLITTER);
		for (String line : lines) {
			for (Token type : Tokenizer.TOKEN_TYPES) {
				Matcher matcher = type.getPattern().matcher(line);
				if (matcher.find()) {
					res.add(type.tokenize(matcher));
					break;
				}
			}
		}
		return res;
	}
}
