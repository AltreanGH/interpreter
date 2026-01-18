package interpreter;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import it.scheibe.interpreter.Program;
import it.scheibe.interpreter.Tokenizer;

public class InterpreterTest {
	@Test
	public void testMultiplication() {
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
	public void testFactorial() {
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
}
