package lf1.plp.functional1.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lf1.plp.expressions1.util.Tipo;
import lf1.plp.expressions2.expression.Expressao;
import lf1.plp.expressions2.expression.Id;
import lf1.plp.expressions2.memory.AmbienteCompilacao;
import lf1.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf1.plp.expressions2.memory.VariavelNaoDeclaradaException;

public class DefFuncao {

	protected List<Map.Entry<AnotacaoTipo, Id>> argsId;

	protected Expressao exp;

	protected AnotacaoTipo tipoRetorno;

	public DefFuncao(List<Map.Entry<AnotacaoTipo, Id>> argsId, Expressao exp, AnotacaoTipo tipoRetorno) {
		this.argsId = argsId;
		this.exp = exp;
		this.tipoRetorno = tipoRetorno;
	}

	public List<Map.Entry<AnotacaoTipo, Id>> getListaId() {
		return argsId;
	}

	public Expressao getExp() {
		return exp;
	}

	public AnotacaoTipo getTipoRetorno() {
		return tipoRetorno;
	}

	/**
	 * Retorna a aridade desta funcao.
	 * 
	 * @return a aridade desta funcao.
	 */
	public int getAridade() {
		return argsId.size();
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
		ambiente.incrementa();

		// Usa uma inst�ncia de TipoQualquer para cada par�metro formal.
		// Essa inst�ncia ser� inferida durante o getTipo de exp.
		for (Map.Entry<AnotacaoTipo, Id> entry : argsId) {
			Id id = entry.getValue();
			Tipo tipo = entry.getKey().getTipo();
			if (tipo != null) {
				ambiente.map(id, tipo);
			} else {
				ambiente.map(id, new TipoPolimorfico());
			}
		}
		// Chama o checa tipo da express�o para veririficar se o corpo da
		// fun��o est� correto. Isto ir� inferir o tipo dos par�metros.
		boolean result = exp.checaTipo(ambiente) && tipoRetorno.getTipo().intersecao(exp.getTipo(ambiente)) != null;
		if (!result) {
			System.out.println("Erro de tipo na funcao: " + exp.toString());
		}

		ambiente.restaura();

		return result;
	}

	/**
	 * Retorna os tipos possiveis desta fun��o.
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
	 * @precondition exp.checaTipo();
	 */
	public Tipo getTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		ambiente.incrementa();


		for (Map.Entry<AnotacaoTipo, Id> entry : argsId) {
			Id id = entry.getValue();
			Tipo tipo = entry.getKey().getTipo();
			if (tipo != null) {
				ambiente.map(id, tipo);
			} else {
				ambiente.map(id, new TipoPolimorfico());
			}
		}

		// Usa o checaTipo apenas para inferir o tipo dos par�metros.
		// Pois o getTipo da express�o pode simplismente retornar o
		// tipo, por exemplo, no caso de uma express�o bin�ria ou un�ria
		// os tipos sempre s�o bem definidos (Booleano, Inteiro ou String).
		// exp.checaTipo(ambiente);

		// Comp�e o tipo desta fun��o do resultado para o primeiro par�metro.
		// Tipo result = exp.getTipo(ambiente);

		// Obt�m o tipo inferido de cada par�metro.
		List<Tipo> params = new ArrayList<Tipo>(getAridade());
		Tipo argTipo;
		for (int i = 0; i < getAridade(); i++) {
			Id id = argsId.get(i).getValue();
			argTipo = ambiente.get(id);
			params.add(argTipo);
		}
		Tipo result = new TipoFuncao(params, tipoRetorno.getTipo());

		ambiente.restaura();

		return result;
	}
	
	public DefFuncao clone() {
		List<Map.Entry<AnotacaoTipo, Id>> novaLista = new ArrayList<Map.Entry<AnotacaoTipo, Id>>(this.argsId.size());
		
		for (Map.Entry<AnotacaoTipo, Id> entry : argsId) {
			novaLista.add(entry);
		}
		
		return new DefFuncao(novaLista, this.exp.clone(), this.tipoRetorno.clone());
	}
}
