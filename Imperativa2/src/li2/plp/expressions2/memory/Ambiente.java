package li2.plp.expressions2.memory;

import li2.plp.expressions1.util.Tipo;
import li2.plp.expressions2.expression.Id;


public interface Ambiente<T> {

	public void incrementa();

	public void restaura();

	public void map(Id idArg, T tipoId) throws VariavelJaDeclaradaException;

	public T get(Id idArg) throws VariavelNaoDeclaradaException;
	
	public void mapTipoRetorno(Id idArg, T tipoId);
	
	public Tipo getTipoRetorno(Id idArg);

}
