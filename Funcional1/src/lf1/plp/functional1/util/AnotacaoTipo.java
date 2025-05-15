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

/**
 * Esta classe representa o tipo de uma fun��o.
 * 
 */
public class AnotacaoTipo {

	private Tipo tipo;
	
	public AnotacaoTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lf1.plp.expressions1.util.Tipo#getNome()
	 */
	public String getNome() {
		return String.format("%s", tipo.getNome());
	}

	public Tipo getTipo() {
		return tipo;
	}

	public String toString() {
		return getNome();
	}

	public AnotacaoTipo clone() {
		return new AnotacaoTipo(tipo);
	}
}
