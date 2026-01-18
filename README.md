# interpreter
A simple Java and Regex based interpreter for LOOP and WHILE.
## Notes
- slightly different syntax from https://en.wikipedia.org/wiki/LOOP_(programming_language)
- for references see https://github.com/knowsys/FormaleSysteme
## Configuring the Interpreter
You can configure the capabilities of the interpreter by editing the constants in `Parameter.java`.
### Changing Language Support
By default both WHILE and LOOP are supported at the same time.
```Java
public static final Token[] TOKEN_TYPES = new Token[] {
		new Loop(null, null),
		new While(null, -1, null), // e.g. removing this will turn off WHILE support
		new Addition(null, -1, null),
		new Subtraction(null, -1, null)
};
```
## Writing a Program
### Multiplication
```LOOP
a := _ + 5
b := _ + 7
ausgabe := _ + 0
LOOP a DO
	LOOP b DO
		ausgabe := ausgabe + 1
	END
END
```
### Factorial
```LOOP
ausgabe := _ + 1
i := _ + 0
LOOP a DO
	i := i + 1
	tmp := _ + 0
	LOOP ausgabe DO
		LOOP i DO
			tmp := tmp + 1
		END
	END
	ausgabe := tmp + 0
END
```
### Summation
```WHILE
z := y+10
WHILE z!= 0 DO
	LOOP z DO
		x := x+ 1
	END
	z := z - 1
END
```
## Running a Program
### Run it Using
```java
java -jar {release}.jar {path-to-code} {initial-variable-assignments}
```
### Example
```java
java -jar interpreter-1.0-SNAPSHOT.jar /home/user/Documents/test-program a=9 b=12
```
