package it.scheibe.interpreter;

public class Parameters {
	/**
	 * Let's say we have a LOOP with the variable <code>n</code>. If this param is
	 * <code>true</code>, updating this variable inside the LOOP updates the times
	 * the LOOP is executed. Otherwise the loop is executed exactly <code>n</code>
	 * times no matter if the variable is updated.
	 */
	public static final boolean UPDATE_IN_LOOP = false;

	/**
	 * Wether comments should be allowed in source code. Comments are written in
	 * Java syntax.
	 */
	public static final boolean ENABLE_COMMENTS = true;

	/**
	 * The value variables take on if they are not initialized yet.
	 */
	public static final int DEFAULT_VALUE = 0;
}
