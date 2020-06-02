package li2.plp.imperative2.declaration;

import li2.plp.expressions2.memory.IdentificadorJaDeclaradoException;
import li2.plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import li2.plp.imperative1.declaration.Declaracao;
import li2.plp.imperative1.memory.AmbienteCompilacaoImperativa;
import li2.plp.imperative1.memory.AmbienteExecucaoImperativa;
import li2.plp.imperative2.expression.ExpBooleana;
import li2.plp.imperative2.memory.AmbienteExecucaoImperativa2;
import li2.plp.imperative2.memory.InvarianteException;

public class DeclaracaoInvariante extends Declaracao {

	private ExpBooleana exp;

	public DeclaracaoInvariante(ExpBooleana exp) {
		super();
		this.exp = exp;
	}

	@Override
	public AmbienteExecucaoImperativa elabora(
			AmbienteExecucaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException {
		AmbienteExecucaoImperativa2 aux = (AmbienteExecucaoImperativa2) ambiente;
		
		aux.adicionaListaExpInvaritante(getExpressao());
		if(!aux.checaListaExpInvaritante()) throw new InvarianteException();
			
		return aux;
	}

	public ExpBooleana getExpressao() {
		return this.exp;
	}

	@Override
	public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException {
		return getExpressao().checaTipo(ambiente);
	}

}
