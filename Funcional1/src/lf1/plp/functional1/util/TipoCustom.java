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

	private TipoPrimitivo tipo1;
	private TipoPrimitivo tipo2;

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
	public TipoCustom(TipoPrimitivo tipo1, TipoPrimitivo tipo2) {
		this.tipo1 = tipo1;
		this.tipo2 = tipo2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lf1.plp.expressions1.util.Tipo#getNome()
	 */
	public String getNome() {
		return String.format("%s | %s", tipo1.getNome(), tipo2.getNome());
	}

	public boolean eBooleano() {
		return tipo1.eBooleano() || tipo2.eBooleano();
	}

	public boolean eInteiro() {
		return tipo1.eInteiro() || tipo2.eInteiro();
	}

	public boolean eString() {
		return tipo1.eString() || tipo2.eString();
	}

	public boolean eValido() {
		return tipo1.eValido() && tipo2.eValido();
	}

	public Tipo getTipo1() {
		return tipo1;
	}

	public Tipo getTipo2() {
		return tipo2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lf1.plp.expressions1.util.Tipo#eIgual(lf1.plp.expressions1.util.Tipo)
	 */
	public boolean eIgual(Tipo tipo) {
		if (tipo instanceof TipoCustom) {
			TipoCustom tipoCasted = (TipoCustom) tipo;
			boolean ret = false;
			if (eValido()) {
				if (tipo.eValido()) {
					if ((this.tipo1.eIgual(tipoCasted.getTipo1()) && this.tipo2.eIgual(tipoCasted.getTipo2()))
							|| (this.tipo1.eIgual(tipoCasted.getTipo2()) && this.tipo2.eIgual(tipoCasted.getTipo1()))) {
						ret = true;
					}
				}
			}
			return ret;
		} else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lf1.plp.expressions1.util.Tipo#intersecao(lf1.plp.expressions1.util.Tipo)
	 */
	public Tipo intersecao(Tipo outroTipo) {
		if (outroTipo.eIgual(getTipo1()) || outroTipo.eIgual(getTipo2()))
			return outroTipo;
		else
			return null;
	}

	@Override
	public String toString() {
		return getNome();
	}

	public TipoCustom clone() {
		return new TipoCustom(tipo1, tipo2);
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
