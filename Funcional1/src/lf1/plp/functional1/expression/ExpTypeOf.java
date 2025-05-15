package lf1.plp.functional1.expression;

import lf1.plp.expressions1.util.Tipo;
import lf1.plp.expressions1.util.TipoPrimitivo;
import lf1.plp.expressions2.expression.ExpUnaria;
import lf1.plp.expressions2.expression.Expressao;
import lf1.plp.expressions2.expression.Id;
import lf1.plp.expressions2.expression.Valor;
import lf1.plp.expressions2.expression.ValorBooleano;
import lf1.plp.expressions2.expression.ValorInteiro;
import lf1.plp.expressions2.expression.ValorString;
import lf1.plp.expressions2.memory.AmbienteCompilacao;
import lf1.plp.expressions2.memory.AmbienteExecucao;
import lf1.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf1.plp.expressions2.memory.VariavelNaoDeclaradaException;

public class ExpTypeOf extends ExpUnaria{

	public ExpTypeOf( Expressao exp) {
		super(exp, "typeof");
	}

	/**
	 * Retorna o valor da Expressao de negacao logica.
	 * 
	 * @param amb o ambiente de execu��o.
	 * @return o valor da express�o avaliada.
	 * @exception VariavelNaoDeclaradaException se a vari�vel n�o est� 
	 *            declarada no ambiente. 
	 */
	public Valor avaliar(AmbienteExecucao amb) throws VariavelJaDeclaradaException, 
        VariavelNaoDeclaradaException {
		Valor valor = getExp().avaliar(amb);
		String tipoStr = "unknown";
		
		// Check the type of the evaluated expression
		if (valor instanceof ValorInteiro) {
			tipoStr = "int";
		} else if (valor instanceof ValorBooleano) {
			tipoStr = "boolean";
		} else if (valor instanceof ValorString) {
			tipoStr = "string";
		}
		
		return new ValorString(tipoStr);
	}

	/**
	 * Realiza a verificacao de tipos desta expressao.
	 * 
	 * @param amb o ambiente de compila��o.
	 * @return <code>true</code> se os tipos da expressao sao validos;
	 *          <code>false</code> caso contrario.
	 * @exception VariavelNaoDeclaradaException se existir um identificador
	 *          nao declarado no ambiente.
	 * @exception VariavelNaoDeclaradaException se existir um identificador
	 *          declarado mais de uma vez no mesmo bloco do ambiente.
	 */
	protected boolean checaTipoElementoTerminal(AmbienteCompilacao amb) throws VariavelJaDeclaradaException, 
        VariavelNaoDeclaradaException {
		// typeof should work with any type
		return true;
	}

	/**
	 * Retorna os tipos possiveis desta expressao.
	 *
	 * @param amb o ambiente de compila��o.
	 * @return os tipos possiveis desta expressao.
	 */
	public Tipo getTipo(AmbienteCompilacao amb) {
		return TipoPrimitivo.STRING;
	}
	
	@Override
	public ExpUnaria clone() {
		return new ExpTypeOf(exp.clone());
	}
}
