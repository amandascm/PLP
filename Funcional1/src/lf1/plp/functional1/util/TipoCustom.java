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

public class TipoCustom implements Tipo {

	private Tipo tipo1;
	private Tipo tipo2;

	public TipoCustom(Tipo tipo1, Tipo tipo2) {
		this.tipo1 = tipo1;
		this.tipo2 = tipo2;
	}

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

	public Tipo intersecao(Tipo outroTipo) {
		if (outroTipo != null && (outroTipo.eIgual(this) || outroTipo.eIgual(getTipo1()) || outroTipo.eIgual(getTipo2()))) {
			return outroTipo;
		}
		else {
			return null;
		}
	}

	@Override
	public String toString() {
		return getNome();
	}

	public TipoCustom clone() {
		return new TipoCustom(tipo1, tipo2);
	}
}
