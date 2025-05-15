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

public class TipoCustom implements Tipo, Valor {

	private TipoPrimitivo tipo1;
	private TipoPrimitivo tipo2;

	public TipoCustom(TipoPrimitivo tipo1, TipoPrimitivo tipo2) {
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
