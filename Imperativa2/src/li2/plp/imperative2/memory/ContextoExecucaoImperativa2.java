package li2.plp.imperative2.memory;

import li2.plp.expressions2.expression.Id;
import li2.plp.expressions2.expression.ValorBooleano;
import li2.plp.expressions2.memory.Contexto;
import li2.plp.expressions2.memory.VariavelJaDeclaradaException;
import li2.plp.expressions2.memory.VariavelNaoDeclaradaException;
import li2.plp.imperative1.memory.ContextoExecucaoImperativa;
import li2.plp.imperative1.memory.ListaValor;
import li2.plp.imperative2.command.ListaExpressao;
import li2.plp.imperative2.declaration.DefProcedimento;
import li2.plp.imperative2.expression.ExpBooleana;

public class ContextoExecucaoImperativa2 extends ContextoExecucaoImperativa
		implements AmbienteExecucaoImperativa2 {

	/**
	 * O contexto de procedimentos faz as vezes de um contexto de execu��o que
	 * armazena apenas procedimentos.
	 */
	private Contexto<DefProcedimento> contextoProcedimentos;
	private ListaExpressao listaExpInvariante;

	/**
	 * Construtor da classe.
	 */
	public ContextoExecucaoImperativa2(ListaValor entrada) {
		super(entrada);
		contextoProcedimentos = new Contexto<DefProcedimento>();
	}

	@Override
	public void incrementa() {
		super.incrementa();
		this.contextoProcedimentos.incrementa();
	}

	@Override
	public void restaura() {
		super.restaura();
		this.contextoProcedimentos.restaura();
	}

	/**
	 * Mapeia o id no procedimento dado.
	 * 
	 * @exception ProcedimentoJaDeclaradoException
	 *                se j� existir um mapeamento do identificador nesta tabela.
	 */
	public void mapProcedimento(Id idArg, DefProcedimento procedimentoId)
			throws ProcedimentoJaDeclaradoException {
		try {
			this.contextoProcedimentos.map(idArg, procedimentoId);
		} catch (VariavelJaDeclaradaException e) {
			throw new ProcedimentoJaDeclaradoException(idArg);
		}

	}

	/**
	 * Retorna o procedimento mapeado ao id dado.
	 * 
	 * @exception ProcedimentoNaoDeclaradoException
	 *                se n�o existir nenhum procedimento mapeado ao id dado
	 *                nesta tabela.
	 */
	public DefProcedimento getProcedimento(Id idArg)
			throws ProcedimentoNaoDeclaradoException {
		try {
			return this.contextoProcedimentos.get(idArg);
		} catch (VariavelNaoDeclaradaException e) {
			throw new ProcedimentoNaoDeclaradoException(idArg);
		}

	}

	@Override
	public void adicionaListaExpInvaritante(ExpBooleana exp) {
		if (this.listaExpInvariante == null) {
			this.listaExpInvariante = new ListaExpressao(exp);
		} else {
			this.listaExpInvariante = new ListaExpressao(exp, this.listaExpInvariante);
		}
	}

	@Override
	public ListaExpressao getListaExpInvaritante() {
		return this.listaExpInvariante;
	}

	@Override
	public boolean checaListaExpInvaritante() {
		if (this.listaExpInvariante == null) return true;
		return checaListaValorInvaritante(this.listaExpInvariante.avaliar(this));
	}
	
	private boolean checaListaValorInvaritante(ListaValor listaValor) {
		boolean resposta = true;
		if (listaValor.getHead() != null) {
			if (listaValor.getTail() != null) {
				resposta = ((ValorBooleano) listaValor.getHead().avaliar(this)).valor()
						&& checaListaValorInvaritante((ListaValor) listaValor.getTail());
			} else {
				resposta = ((ValorBooleano) listaValor.getHead().avaliar(this)).valor();
			}
		}
		return resposta;
	}
}
