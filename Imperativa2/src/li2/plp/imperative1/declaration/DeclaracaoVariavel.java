package li2.plp.imperative1.declaration;

import li2.plp.expressions2.expression.Expressao;
import li2.plp.expressions2.expression.Id;
import li2.plp.expressions2.expression.ValorBooleano;
import li2.plp.expressions2.memory.IdentificadorJaDeclaradoException;
import li2.plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import li2.plp.imperative1.memory.AmbienteCompilacaoImperativa;
import li2.plp.imperative1.memory.AmbienteExecucaoImperativa;
import li2.plp.imperative2.expression.ExpBooleana;
import li2.plp.imperative2.memory.InvariantException;

public class DeclaracaoVariavel extends Declaracao {

	private Id id;
	private Expressao expressao;
	private Expressao expBooleana;

	public DeclaracaoVariavel(Id id, Expressao expressao, Expressao expBooleana) {
		super();
		this.id = id;
		this.expressao = expressao;
		this.expBooleana = expBooleana;
	}

	/**
	 * Cria um mapeamento do identificador para o valor da express�o desta
	 * declara��o no AmbienteExecucao
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            valores.
	 * 
	 * @return o ambiente modificado pela inicializa��o da vari�vel.
	 */
	@Override
	public AmbienteExecucaoImperativa elabora(
			AmbienteExecucaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException {
		ambiente.map(getId(), getExpressao().avaliar(ambiente));
		
		if(getExpressaoBooleana() != null) {
			ambiente.mapBoolean(getId(), getExpressaoBooleana());
			ValorBooleano avaliacaoInv = (ValorBooleano) ambiente.getExpInvariant(getId()).avaliar(ambiente);
			if(!avaliacaoInv.valor()) throw new InvariantException();
		}
		
		
//		if(getExpressaoBooleana() != null) {
//			ValorBooleano avaliacaoInv = (ValorBooleano) getExpressaoBooleana().avaliar(ambiente);
//			if(!avaliacaoInv.valor()) throw new InvariantException();
//		}
		
		return ambiente;
	}

	public Expressao getExpressao() {
		return this.expressao;
	}

	public Id getId() {
		return this.id;
	}
	
	public Expressao getExpressaoBooleana() {
		return this.expBooleana;
	}

	/**
	 * Verifica se a declara��o est� bem tipada, ou seja, se a express�o de
	 * inicializa��o est� bem tipada, e cria o mapeamento da variavel para o seu
	 * tipo correspondente
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            seus tipos.
	 * 
	 * @return <code>true</code> se os tipos da declara��o s�o v�lidos;
	 *         <code>false</code> caso contrario.
	 * 
	 */
	@Override
	public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException {
		boolean result = getExpressao().checaTipo(ambiente);
		if (result) {
			ambiente.map(getId(), getExpressao().getTipo(ambiente));
		}
		return result;
	}
}
