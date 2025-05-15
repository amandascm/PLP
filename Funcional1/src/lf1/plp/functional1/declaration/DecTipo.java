package lf1.plp.functional1.declaration;

import lf1.plp.expressions2.expression.Id;
import lf1.plp.expressions2.memory.AmbienteCompilacao;
import lf1.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf1.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf1.plp.functional1.memory.AmbienteExecucaoFuncional;
import lf1.plp.functional1.util.TipoCustom;

public class DecTipo implements DeclaracaoFuncional {
	private Id id;
	private TipoCustom tipoCustom;

	public DecTipo(Id idArg, TipoCustom tipoCustom) {
		id = idArg;
		this.tipoCustom = tipoCustom;
	}

	/**
	 * Retorna uma representacao String desta expressao. Util para depuracao.
	 * 
	 * @return uma representacao String desta expressao.
	 */
	@Override
	public String toString() {
		return String.format("tipo %s = %s", id, tipoCustom);
	}

	public Id getId() {
		return id;
	}

	/**
	 * Retorna os tipos possiveis desta declara��o.
	 * 
	 * @param amb
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            tipos.
	 * @return os tipos possiveis desta declara��o.
	 * @exception VariavelNaoDeclaradaException
	 *                se houver uma vari&aacute;vel n&atilde;o declarada no
	 *                ambiente.
	 * @exception VariavelJaDeclaradaException
	 *                se houver uma mesma vari&aacute;vel declarada duas vezes
	 *                no mesmo bloco do ambiente.
	 * @precondition this.checaTipo(amb);
	 */
	public TipoCustom getTipo()
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return tipoCustom;
	}

	/**
	 * Realiza a verificacao de tipos desta declara��o.
	 * 
	 * @param amb
	 *            o ambiente de compila��o.
	 * @return <code>true</code> se os tipos da expressao sao validos;
	 *         <code>false</code> caso contrario.
	 * @exception VariavelNaoDeclaradaException
	 *                se existir um identificador nao declarado no ambiente.
	 * @exception VariavelNaoDeclaradaException
	 *                se existir um identificador declarado mais de uma vez no
	 *                mesmo bloco do ambiente.
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return tipoCustom.eValido();
	}

	public DecTipo clone() {
		return new DecTipo(this.id.clone(), this.tipoCustom.clone());
	}

	public void elabora(AmbienteCompilacao amb, AmbienteCompilacao aux) throws VariavelJaDeclaradaException {
		aux.map(getId(), getTipo());
	}

	public void incluir(AmbienteCompilacao amb, AmbienteCompilacao aux) throws VariavelJaDeclaradaException {
		amb.map(getId(), aux.get(getId()));
	}

	public void elabora(AmbienteExecucaoFuncional amb, AmbienteExecucaoFuncional aux) throws VariavelJaDeclaradaException {
		aux.map(getId(), getTipo());
	}

	public void incluir(AmbienteExecucaoFuncional amb, AmbienteExecucaoFuncional aux) throws VariavelJaDeclaradaException {
		amb.map(getId(), aux.get(getId()));
	}
	
}
