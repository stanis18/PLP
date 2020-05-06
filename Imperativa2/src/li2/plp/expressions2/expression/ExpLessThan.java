package li2.plp.expressions2.expression;

import li2.plp.expressions1.util.Tipo;
import li2.plp.expressions1.util.TipoPrimitivo;
import li2.plp.expressions2.memory.AmbienteCompilacao;
import li2.plp.expressions2.memory.AmbienteExecucao;
import li2.plp.expressions2.memory.VariavelJaDeclaradaException;
import li2.plp.expressions2.memory.VariavelNaoDeclaradaException;

public class ExpLessThan extends ExpBinaria{

	public ExpLessThan(Expressao esq, Expressao dir) {
		super(esq, dir, "<");
	}

	@Override
	public Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return new ValorBooleano(((ValorInteiro) getEsq().avaliar(amb)).valor() < ((ValorInteiro) getDir().avaliar(amb)).valor());
	}

	@Override
	public Tipo getTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return TipoPrimitivo.BOOLEANO;
	}

	@Override
	protected boolean checaTipoElementoTerminal(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return (getEsq().getTipo(amb).eInteiro() && getDir().getTipo(amb).eInteiro());
	}

	@Override
	public ExpBinaria clone() {
		return new ExpLessThan(esq.clone(), dir.clone());
	}

}
