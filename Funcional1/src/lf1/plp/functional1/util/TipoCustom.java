/*
 * Universidade Federal de Pernambuco - UFPE
 * Centro de Inform�tica - CIn
 * 
 * Paradigmas de Linguagem de Programa��o - PLP
 * 
 * Tipo: TipoFuncao
 */
package lf1.plp.functional1.util;

import lf1.plp.expressions1.util.Tipo;
import lf1.plp.expressions1.util.TipoPrimitivo;
import lf1.plp.expressions2.expression.Expressao;
import lf1.plp.expressions2.expression.Valor;
import lf1.plp.expressions2.memory.AmbienteCompilacao;
import lf1.plp.expressions2.memory.AmbienteExecucao;
import lf1.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf1.plp.expressions2.memory.VariavelNaoDeclaradaException;

/**
 * Esta classe representa o tipo de uma fun��o.
 * 
 */
public class TipoCustom implements Tipo, Valor {

	private TipoPrimitivo tipoPrim;
	private Tipo tipo2;

	/**
	 * O tipo do dom�nio da fun��o.
	 */
	// private List<Tipo> dominio;

	/**
	 * O tipo da imagem (o tipo de retorno) da fun��o.
	 */
	// private Tipo imagem;

	/**
	 * Construtor da classe que representa um tipo fun��o (T1 x ... x Tn -> T).
	 * 
	 * @param dominio
	 *            A lista dos tipos do dom�nio da fun��o (T1 x ... x Tn).
	 * @param imagem
	 *            O tipo da imagem da fun��o (T).
	 */
	public TipoCustom(TipoPrimitivo tipoPrim, Tipo tipo2) {
		this.tipoPrim = tipoPrim;
		this.tipo2 = tipo2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lf1.plp.expressions1.util.Tipo#getNome()
	 */
	public String getNome() {
		return String.format("%s | %s", tipoPrim.getNome(), tipo2.getNome());
	}

	public boolean eBooleano() {
		return tipoPrim.eBooleano() || tipo2.eBooleano();
	}

	public boolean eInteiro() {
		return tipoPrim.eInteiro() || tipo2.eInteiro();
	}

	public boolean eString() {
		return tipoPrim.eString() || tipo2.eString();
	}

	public boolean eValido() {
		return tipoPrim.eValido() && tipo2.eValido();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lf1.plp.expressions1.util.Tipo#eIgual(lf1.plp.expressions1.util.Tipo)
	 */
	public boolean eIgual(Tipo tipo) {
		if (tipo instanceof TipoCustom) {
			boolean ret = false;
			if (eValido()) {
				if (tipo.eValido()) {
					ret = this.getNome().equals(tipo.getNome());
				} else {
					ret = tipo.eIgual(this);
				}
			}
			return ret;
		} else {
			return tipo.eIgual(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lf1.plp.expressions1.util.Tipo#intersecao(lf1.plp.expressions1.util.Tipo)
	 */
	public Tipo intersecao(Tipo outroTipo) {
		if (outroTipo.eIgual(this))
			return this;
		else
			return null;
	}

	@Override
	public String toString() {
		return getNome();
	}

	public TipoCustom clone() {
		return new TipoCustom(tipoPrim, tipo2);
	}

	@Override
	public Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return this;
	}

	@Override
	public boolean checaTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return this.eValido();
	}

	@Override
	public Tipo getTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return this;
	}

	@Override
	public Expressao reduzir(AmbienteExecucao ambiente) {
		return this;
	}
}
