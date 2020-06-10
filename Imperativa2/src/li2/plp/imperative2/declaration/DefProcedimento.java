package li2.plp.imperative2.declaration;

import li2.plp.expressions1.util.Tipo;
import li2.plp.expressions2.expression.Expressao;
import li2.plp.imperative1.command.Comando;
import li2.plp.imperative2.command.ListaExpAlteravel;
import li2.plp.imperative2.util.TipoProcedimento;

/**
 * Uma defini�ao de procedimento � uma declara�ao de comando e parametrosFormais
 * uma declara��o de procedimento.
 */
public class DefProcedimento {

	/**
	 * Declara��o dos parametrosFormais
	 */
	private ListaDeclaracaoParametro parametrosFormais;

	/**
	 * Declara�ao de Comando
	 */
	private Comando comando;
	
	private Expressao expressaoPre;
	
	private ListaExpAlteravel listaExpressaoAlt;
	
	private Expressao expressaoPos;
	
	private Expressao expressaoRetorno;

	/**
	 * Construtor
	 * 
	 * @param parametrosFormais
	 *            Declara��o de ListaDeclaracaoParametro
	 * @param comando
	 *            Declara�ao de Comando.
	 */
	public DefProcedimento(ListaDeclaracaoParametro parametrosFormais,
							Comando comando,
							Expressao expressaoPre,
							ListaExpAlteravel listaExpressaoAlt,
							Expressao expressaoPos,
							Expressao expressaoRetorno) {
		this.parametrosFormais = parametrosFormais;
		this.comando = comando;
		this.expressaoPre = expressaoPre;
		this.listaExpressaoAlt = listaExpressaoAlt;
		this.expressaoPos = expressaoPos;
		this.expressaoRetorno = expressaoRetorno;
	}

	/**
	 * Obt�m o comando do Procedimento.
	 * 
	 * @return o comando
	 */
	public Comando getComando() {
		return comando;
	}

	/**
	 * Obt�m os parametrosFormais do Procedimento.
	 * 
	 * @return a ListaDeclaracaoParametro
	 */
	public ListaDeclaracaoParametro getParametrosFormais() {
		return parametrosFormais;
	}

	public Tipo getTipo() {
		return new TipoProcedimento(parametrosFormais.getTipos());
	}

	public Expressao getExpressaoPre() {
		return expressaoPre;
	}
	
	public ListaExpAlteravel getListaExpressaoAlt() {
		return listaExpressaoAlt;
	}
	
	public Expressao getExpressaoPos() {
		return expressaoPos;
	}

	public Expressao getExpressaoRetorno() {
		return expressaoRetorno;
	}
}
