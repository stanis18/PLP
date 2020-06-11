package li2.plp.imperative2.command;

import li2.plp.expressions1.util.Tipo;
import li2.plp.expressions1.util.TipoPrimitivo;
import li2.plp.expressions2.expression.Expressao;
import li2.plp.expressions2.expression.Id;
import li2.plp.expressions2.expression.Valor;
import li2.plp.expressions2.expression.ValorBooleano;
import li2.plp.expressions2.memory.AmbienteCompilacao;
import li2.plp.expressions2.memory.AmbienteExecucao;
import li2.plp.expressions2.memory.IdentificadorJaDeclaradoException;
import li2.plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import li2.plp.expressions2.memory.VariavelJaDeclaradaException;
import li2.plp.expressions2.memory.VariavelNaoDeclaradaException;
import li2.plp.imperative1.command.Comando;
import li2.plp.imperative1.memory.AmbienteCompilacaoImperativa;
import li2.plp.imperative1.memory.AmbienteExecucaoImperativa;
import li2.plp.imperative1.memory.EntradaVaziaException;
import li2.plp.imperative1.memory.ErroTipoEntradaException;
import li2.plp.imperative1.memory.ListaValor;
import li2.plp.imperative2.declaration.DefProcedimento;
import li2.plp.imperative2.declaration.ListaDeclaracaoParametro;
import li2.plp.imperative2.expression.ExpBooleana;
import li2.plp.imperative2.memory.AmbienteExecucaoImperativa2;
import li2.plp.imperative2.memory.PosRequisitosException;
import li2.plp.imperative2.memory.PreRequisitosException;
import li2.plp.imperative2.util.TipoProcedimento;

public class ChamadaProcedimento implements Expressao {

	private Id nomeProcedimento;

	private ListaExpressao parametrosReais;

	public ChamadaProcedimento(Id nomeProcedimento,
			ListaExpressao parametrosReais) {
		this.nomeProcedimento = nomeProcedimento;
		this.parametrosReais = parametrosReais;
	}

//	public AmbienteExecucaoImperativa executar(AmbienteExecucaoImperativa amb)
//			throws IdentificadorNaoDeclaradoException,
//			IdentificadorJaDeclaradoException, EntradaVaziaException, ErroTipoEntradaException, PreRequisitosException,PosRequisitosException {
//		AmbienteExecucaoImperativa2 ambiente = (AmbienteExecucaoImperativa2) amb;
//		DefProcedimento procedimento = ambiente
//				.getProcedimento(nomeProcedimento);
//
//		/*
//		 * o incrementa e o restaura neste ponto servem para criar as variveis
//		 * que serao utilizadas pela execucao do procedimento
//		 */
//		ambiente.incrementa();
//		ListaDeclaracaoParametro parametrosFormais = procedimento
//				.getParametrosFormais();
//		AmbienteExecucaoImperativa2 aux = bindParameters(ambiente,
//				parametrosFormais);
//		 
//		if(procedimento.getExpressaoPre() != null) {
//			ValorBooleano avaliacaoPre = (ValorBooleano)procedimento.getExpressaoPre().avaliar(aux);		
//			if(!avaliacaoPre.valor()) throw new PreRequisitosException();
//		}
//		
//		if (procedimento.getListaExpressaoAlt() != null) {
//			aux = (AmbienteExecucaoImperativa2) procedimento.getListaExpressaoAlt().elabora(aux);
//		}
//		
//		aux = (AmbienteExecucaoImperativa2) procedimento.getComando().executar(aux);
//		
//		if(procedimento.getExpressaoPos() != null) {
//			ValorBooleano avaliacaoPos = (ValorBooleano)procedimento.getExpressaoPos().avaliar(aux);		
//			if(!avaliacaoPos.valor()) throw new PosRequisitosException();
//		}
//		
//		aux.restaura();
//		return aux;
//
//	}

	/**
	 * insere no contexto o resultado da associacao entre cada parametro formal
	 * e seu correspondente parametro atual
	 */
	private AmbienteExecucaoImperativa2 bindParameters(
			AmbienteExecucaoImperativa2 ambiente,
			ListaDeclaracaoParametro parametrosFormais)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException {
		ListaValor listaValor = parametrosReais.avaliar(ambiente);
		while (listaValor.length() > 0) {
			ambiente.map(parametrosFormais.getHead().getId(), listaValor
					.getHead());
			parametrosFormais = (ListaDeclaracaoParametro) parametrosFormais
					.getTail();
			listaValor = (ListaValor) listaValor.getTail();
		}
		return ambiente;
	}

	/**
	 * Realiza a verificacao de tipos desta chamada de procedimento, onde os
	 * tipos dos parametros formais devem ser iguais aos tipos dos parametros
	 * reais na ordem em que se apresentam.
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            tipos.
	 * @return <code>true</code> se a chamada de procedimeno est� bem tipada;
	 *         <code>false</code> caso contrario.
	 */
//	public boolean checaTipo(AmbienteCompilacaoImperativa amb)
//			throws IdentificadorJaDeclaradoException,
//			IdentificadorNaoDeclaradoException {
//
//		Tipo tipoProcedimento = amb.get(this.nomeProcedimento);
//
//		TipoProcedimento tipoParametrosReais = new TipoProcedimento(
//				parametrosReais.getTipos(amb));
//		return tipoProcedimento.eIgual(tipoParametrosReais);
//
//	}

	@Override
	public Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		
		AmbienteExecucaoImperativa2 ambiente = (AmbienteExecucaoImperativa2) amb;
		DefProcedimento procedimento = ambiente
				.getProcedimento(nomeProcedimento);

		/*
		 * o incrementa e o restaura neste ponto servem para criar as variveis
		 * que serao utilizadas pela execucao do procedimento
		 */
		ambiente.incrementa();
		ListaDeclaracaoParametro parametrosFormais = procedimento
				.getParametrosFormais();
		AmbienteExecucaoImperativa2 aux = bindParameters(ambiente,
				parametrosFormais);
		 
		if(procedimento.getExpressaoPre() != null) {
			ValorBooleano avaliacaoPre = (ValorBooleano)procedimento.getExpressaoPre().avaliar(aux);		
			if(!avaliacaoPre.valor()) throw new PreRequisitosException();
		}
		
		if (procedimento.getListaExpressaoAlt() != null) {
			aux = (AmbienteExecucaoImperativa2) procedimento.getListaExpressaoAlt().elabora(aux);
		}
		
		try {
			//corrigir depois.. 
			aux = (AmbienteExecucaoImperativa2) procedimento.getComando().executar(aux);
		} catch (IdentificadorJaDeclaradoException | IdentificadorNaoDeclaradoException | PreRequisitosException
				| PosRequisitosException | EntradaVaziaException | ErroTipoEntradaException e) {
			e.printStackTrace();
		}
		
		if(procedimento.getExpressaoPos() != null) {
			ValorBooleano avaliacaoPos = (ValorBooleano)procedimento.getExpressaoPos().avaliar(aux);		
			if(!avaliacaoPos.valor()) throw new PosRequisitosException();
		}
		
		Valor valor = (Valor)procedimento.getExpressaoRetorno().avaliar(aux);
		
		return valor;
	}

	@Override
	public boolean checaTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		
		return true;
	}

	@Override
	public Tipo getTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		AmbienteCompilacaoImperativa ambiente = (AmbienteCompilacaoImperativa) amb;
		return ambiente.getTipoRetorno(nomeProcedimento);
	}

	@Override
	public Expressao reduzir(AmbienteExecucao amb) {
		AmbienteExecucaoImperativa2 ambiente = (AmbienteExecucaoImperativa2) amb;
		DefProcedimento procedimento = ambiente
				.getProcedimento(nomeProcedimento);
		procedimento.getExpressaoRetorno().reduzir(ambiente);
		return procedimento.getExpressaoRetorno();
	}

	@Override
	public Expressao clone() {
		return null;
	}

}
