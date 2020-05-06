package li2.plp.imperative2.memory;

public class PosRequisitosException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2740080865757833441L;

	public PosRequisitosException() {
		super("Os pos-requisitos nao sao satisfeitos.");
	}
}
