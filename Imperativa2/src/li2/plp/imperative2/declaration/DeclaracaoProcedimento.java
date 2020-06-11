package li2.plp.imperative2.declaration;

import li2.plp.expressions1.util.Tipo;
import li2.plp.expressions2.expression.Expressao;
import li2.plp.expressions2.expression.Id;
import li2.plp.expressions2.memory.IdentificadorJaDeclaradoException;
import li2.plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import li2.plp.imperative1.declaration.Declaracao;
import li2.plp.imperative1.memory.AmbienteCompilacaoImperativa;
import li2.plp.imperative1.memory.AmbienteExecucaoImperativa;
import li2.plp.imperative1.memory.EntradaVaziaException;
import li2.plp.imperative2.memory.AmbienteExecucaoImperativa2;
import li2.plp.imperative2.memory.TipodeRetornoInvalidoException;

public class DeclaracaoProcedimento extends Declaracao {

	private Id id;
	private DefProcedimento defProcedimento;
	private Tipo tipoRetorno;

	public DeclaracaoProcedimento(Id id, DefProcedimento defProcedimento, Tipo tipoRetorno) {
		super();
		this.id = id;
		this.defProcedimento = defProcedimento;
		this.tipoRetorno = tipoRetorno;
	}

	@Override
	public AmbienteExecucaoImperativa elabora(
			AmbienteExecucaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException {
		((AmbienteExecucaoImperativa2) ambiente).mapProcedimento(getId(),
				getDefProcedimento());
		return ambiente;
	}

	private Id getId() {
		return this.id;
	}

	@Override
	public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException, TipodeRetornoInvalidoException {
		boolean resposta;

		ambiente.map(id, getDefProcedimento().getTipo());
		ambiente.mapTipoRetorno(id, getDefProcedimento().getTipoExpressaoRetorno(ambiente));
		

		ListaDeclaracaoParametro parametrosFormais = getDefProcedimento()
				.getParametrosFormais();
		if (parametrosFormais.checaTipo(ambiente)) {
			ambiente.incrementa();
			ambiente = parametrosFormais.elabora(ambiente);
			
			resposta = getDefProcedimento().getComando().checaTipo(ambiente);
			if (resposta && getDefProcedimento().getExpressaoPre() != null) resposta = resposta && getDefProcedimento().getExpressaoPre().checaTipo(ambiente);
			if (resposta && getDefProcedimento().getListaExpressaoAlt() != null) resposta = resposta && getDefProcedimento().getListaExpressaoAlt().checaTipo(ambiente);
			if (resposta && getDefProcedimento().getExpressaoPos() != null) resposta = resposta && getDefProcedimento().getExpressaoPos().checaTipo(ambiente);
			
			if (resposta && getDefProcedimento().getExpressaoRetorno() != null) resposta = resposta && getDefProcedimento().getExpressaoRetorno().checaTipo(ambiente);
			if (resposta && getDefProcedimento().getExpressaoRetorno() != null) resposta = resposta && getDefProcedimento().getExpressaoRetorno().getTipo(ambiente).eIgual(tipoRetorno);

			
			ambiente.restaura();
		} else {
			resposta = false;
		}
		return resposta;
	}

	private DefProcedimento getDefProcedimento() {
		return this.defProcedimento;
	}

	public Tipo getTipoRetorno() {
		return tipoRetorno;
	}
	
}
