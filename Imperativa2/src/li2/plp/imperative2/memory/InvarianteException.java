package li2.plp.imperative2.memory;

public class InvarianteException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2740080865757833441L;

	public InvarianteException() {
		super("Os requisitos das invariantes nao sao satisfeitos.");
	}
}
