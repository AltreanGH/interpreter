# interpreter
A simple Java and Regex based interpreter for LOOP
The initial implementation of the LOOP interpreter.
## Usage Examples
- slightly different syntax from https://en.wikipedia.org/wiki/LOOP_(programming_language)
- for references see https://github.com/knowsys/FormaleSysteme
### Writing a Program
#### Multiplication
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
#### Factorial
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
### Running the Program
#### Run it Using
```java
java -jar {release}.jar {path-to-code} {initial-variable-assignments}
```
#### Example
```java
java -jar interpreter-1.0-SNAPSHOT.jar /home/user/Documents/test-program a=9 b=12
```
