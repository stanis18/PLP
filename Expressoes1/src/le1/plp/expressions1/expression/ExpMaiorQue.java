package le1.plp.expressions1.expression;

import le1.plp.expressions1.util.Tipo;
import le1.plp.expressions1.util.TipoPrimitivo;
import le1.plp.expressions2.memory.AmbienteCompilacao;
import le1.plp.expressions2.memory.AmbienteExecucao;

public class ExpMaiorQue extends ExpBinaria {
	
	public ExpMaiorQue(Expressao esq, Expressao dir) {
		super(esq, dir, ">");
	}

	@Override
	public Valor avaliar(AmbienteExecucao amb) {
		return new ValorBooleano(
				((ValorInteiro) getEsq().avaliar(amb)).valor() >
				((ValorInteiro) getDir().avaliar(amb)).valor() );
	}

	@Override
	public Tipo getTipo(AmbienteCompilacao amb) {
		return TipoPrimitivo.BOOLEANO;
	}

	@Override
	protected boolean checaTipoElementoTerminal(AmbienteCompilacao amb) {
		return (getEsq().getTipo(amb).eInteiro() && getDir().getTipo(amb).eInteiro());
	}
}
