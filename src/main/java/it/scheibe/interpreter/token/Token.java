package it.scheibe.interpreter.token;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.scheibe.interpreter.InterpretationException.ParsingException;
import it.scheibe.interpreter.Parameters;
import it.scheibe.interpreter.Tokenizer;

public interface Token {
	Pattern getPattern();

	Token tokenize(Matcher result) throws ParsingException;

	Map<String, Integer> interpret(Map<String, Integer> input);

	default List<Token> tokenizeLevel(String input) throws ParsingException {
		List<Token> res = new ArrayList<>(); // TODO error when nothing matches
		String[] lines = input.split(Tokenizer.NEW_TOKEN_REGEX);
		for (String line : lines) {
			boolean match = false;
			for (Token type : Parameters.TOKEN_TYPES) {
				Matcher matcher = type.getPattern().matcher(line);
				if (matcher.find()) {
					match = true;
					res.add(type.tokenize(matcher));
					break;
				}
			}
			if (!match)
				throw new ParsingException("Could not parse line '" + line + "'!");
		}
		return res;
	}
}
