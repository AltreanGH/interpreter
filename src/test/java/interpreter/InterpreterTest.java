package interpreter;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import it.scheibe.interpreter.Program;
import it.scheibe.interpreter.Tokenizer;
import it.scheibe.interpreter.InterpretationException.ParsingException;

public class InterpreterTest {
	@Test
	public void testLoopMultiplication() throws ParsingException {
		String code = """
				a := _ + 5
				b := _ + 7
				ausgabe := _ + 0
				LOOP a DO
				\tLOOP b DO
				\t\tausgabe := ausgabe + 1
				\tEND
				END
				""";

		Map<String, Integer> expected = Map.of(
				"a", 5,
				"b", 7,
				"ausgabe", 35);
		Map<String, Integer> actual = new Program(Tokenizer.tokenize(code)).interpret(new HashMap<>());
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testLoopFactorial() throws ParsingException {
		String code = """
				a := y + 9
				ausgabe := _ + 1
				i := _ + 0
				LOOP a DO
				\ti := i + 1
				\ttmp := _ + 0
				\tLOOP ausgabe DO
				\t\tLOOP i DO
				\t\t\ttmp := tmp + 1
				\t\tEND
				\tEND
				\tausgabe := tmp + 0
				END
				""";

		Map<String, Integer> expected = Map.of(
				"a", 9,
				"i", 9,
				"ausgabe", 362880,
				"tmp", 362880);
		Map<String, Integer> actual = new Program(Tokenizer.tokenize(code)).interpret(new HashMap<>());
		Assert.assertEquals(expected, actual);
	}

	public void testWhileAssignment() throws ParsingException {
		String code = """
				z := y+5
				WHILE z!= 0 DO
				\tx := x +1
				\tz := z - 1
				END
				""";

		Map<String, Integer> expected = Map.of(
				"z", 0,
				"x", 5);
		Map<String, Integer> actual = new Program(Tokenizer.tokenize(code)).interpret(new HashMap<>());
		Assert.assertEquals(expected, actual);
	}

	public void testWhileAndLoop() throws ParsingException {
		String code = """
				z := y+10
				WHILE z!= 0 DO
				\tLOOP z DO
				\t\tx := x+ 1
				\tEND
				\tz := z - 1
				END
				""";

		Map<String, Integer> expected = Map.of(
				"z", 0,
				"x", 55);
		Map<String, Integer> actual = new Program(Tokenizer.tokenize(code)).interpret(new HashMap<>());
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testEmptyLines() throws ParsingException {
		String code = """
				\na := _ + 5\n
				b := _ + 7\n\n\n
				ausgabe := _ + 0
				LOOP a DO
				\tLOOP b DO\n
				\t\tausgabe := ausgabe + 1\n
				\tEND
				END\n\n
				""";

		Map<String, Integer> expected = Map.of(
				"a", 5,
				"b", 7,
				"ausgabe", 35);
		var t = Tokenizer.tokenize(code);
		Map<String, Integer> actual = new Program(t).interpret(new HashMap<>());
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testComments() throws ParsingException {
		String code = """
				a := y + 5
				ausgabe := _ + 1
				LOOP a DO // test
				\ti := i + 1
				\t/*
				\t * this is a comment tmp := _ - 4
				\t */
				\ttmp := _ + 0
				\tLOOP ausgabe DO
				\t\tLOOP i DO
				\t\t\ttmp := tmp + 1
				\t\t/* blub \n*/END
				\t\t/* LOOP i DO
				\t\t *\ttmp := tmp + 1
				\t\t END */
				\tEND
				\tausgabe := tmp + 0
				END
				""";
		Map<String, Integer> expected = Map.of(
				"a", 5,
				"i", 5,
				"ausgabe", 120,
				"tmp", 120);
		Map<String, Integer> actual = new Program(Tokenizer.tokenize(code)).interpret(new HashMap<>());
		Assert.assertEquals(expected, actual);
	}
}
