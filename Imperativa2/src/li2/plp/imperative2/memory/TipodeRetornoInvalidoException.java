package li2.plp.imperative2.memory;

public class TipodeRetornoInvalidoException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2740080865757833441L;

	public TipodeRetornoInvalidoException() {
		super("O tipo de retorno do método é inválido.");
	}

}
