package li2.plp.imperative2.command;

import li2.plp.expressions2.expression.Expressao;
import li2.plp.expressions2.expression.Id;
import li2.plp.expressions2.memory.IdentificadorJaDeclaradoException;
import li2.plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import li2.plp.imperative1.memory.AmbienteExecucaoImperativa;

public class ListaExpAlteravel extends ListaExpressao {
	
	public ListaExpAlteravel() {

	}

	public ListaExpAlteravel(Expressao expressao) {
		super(expressao, new ListaExpAlteravel());
	}

	public ListaExpAlteravel(Expressao expressao, ListaExpAlteravel listaExpressao) {
		super(expressao, listaExpressao);
	}
	
	public AmbienteExecucaoImperativa elabora(
			AmbienteExecucaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException {
		
		if (getHead() != null) {
			Id id = (Id) getHead();
			Id newId = new Id(transformId(id.getIdName()));
			ambiente.map(newId, id.avaliar(ambiente));

			if (getTail() != null) {
				((ListaExpAlteravel) getTail()).elabora(ambiente);
			}
		}

		return ambiente;
	}
	
	private String transformId(String id) {
		return "old_" + id;
	}

}
