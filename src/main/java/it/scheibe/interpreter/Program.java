package it.scheibe.interpreter;

import java.util.List;
import java.util.Map;

import it.scheibe.interpreter.token.Token;

public class Program {
	private List<Token> content;

	public Program(List<Token> content) {
		this.content = content;
	}

	public Map<String, Integer> interpret(Map<String, Integer> args) {
		for (Token statement : content) {
			args = statement.interpret(args);
		}
		return args;
	}
}
