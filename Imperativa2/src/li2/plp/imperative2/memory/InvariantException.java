package li2.plp.imperative2.memory;

public class InvariantException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2740080865757833441L;

	public InvariantException() {
		super("Os requisitos da invariantes nao sao satisfeitos.");
	}
}
