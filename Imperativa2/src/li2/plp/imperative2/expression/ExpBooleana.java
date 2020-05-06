package li2.plp.imperative2.expression;

import li2.plp.expressions1.util.Tipo;
import li2.plp.expressions1.util.TipoPrimitivo;
import li2.plp.expressions2.expression.Expressao;
import li2.plp.expressions2.expression.Valor;
import li2.plp.expressions2.memory.AmbienteCompilacao;
import li2.plp.expressions2.memory.AmbienteExecucao;
import li2.plp.expressions2.memory.VariavelJaDeclaradaException;
import li2.plp.expressions2.memory.VariavelNaoDeclaradaException;

public class ExpBooleana implements Expressao {
	
	/**
	 * Expressao contida pela expressao booleana
	 */
	protected Expressao exp;
	
	/**
	 * Construtor da classe.
	 * 
	 * @param exp
	 *            expressao contida pela expressao booleana.
	 */
	public ExpBooleana(Expressao exp) {
		this.exp = exp;
	}

	/**
	 * Retorna a expressao contida pela expressao booleana
	 * 
	 * @return a expressao contida pela expressao booleana
	 */
	public Expressao getExp() {
		return exp;
	}

	@Override
	public Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return getExp().avaliar(amb);
	}

	@Override
	public boolean checaTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return getTipo(amb).eBooleano();
	}

	@Override
	public Tipo getTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return TipoPrimitivo.BOOLEANO;
	}

	@Override
	public Expressao reduzir(AmbienteExecucao ambiente) {
		this.exp = this.exp.reduzir(ambiente);
		
		return this;
	}

	@Override
	public ExpBooleana clone() {
		return new ExpBooleana(getExp().clone());
	}

}
