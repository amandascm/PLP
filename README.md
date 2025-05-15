# PLP
Projeto da disciplina Paradigmas de Linguagens de Programação - CIn/UFPE.

## Equipe
- Amanda Moraes ([ascm](mailto:ascm@cin.ufpe.br), [GitHub](https://github.com/amandascm))
- Tales Alves ([tta](mailto:tta@cin.ufpe.br), [GitHub](https://github.com/tta13))

## Escopo do projeto

Com base nos princípios de flexibilidade e generalização, vamos adicionar tipagem e união de tipos à Linguagem Funcional 1 apresentada na disciplina. Com isso, variáveis, parâmetros ou retorno de funções poderão ser anotados com os tipos específicos já definidos na linguagem, mas também com suporte à união deles. Um novo operador chamado `typeof` também será implementado para expandir a utilidade da união de tipos na linguagem.

## BNF

**Programa** ::= [**Expressao**](Funcional1/src/lf1/plp/functional1/Programa.java)

**Expressao** ::= [**Valor**](Funcional1/src/lf1/plp/expressions2/expression/Valor.java)
    | [**ExpUnaria**](Funcional1/src/lf1/plp/expressions2/expression/ExpUnaria.java)  
    | [**ExpBinaria**](Funcional1/src/lf1/plp/expressions2/expression/ExpBinaria.java)  
    | [**ExpDeclaracao**](Funcional1/src/lf1/plp/functional1/expression/ExpDeclaracao.java)  
    | [**Id**](Funcional1/src/lf1/plp/expressions2/expression/Id.java)  
    | [**Aplicacao**](Funcional1/src/lf1/plp/functional1/expression/Aplicacao.java)  
    | [**IfThenElse**](Funcional1/src/lf1/plp/functional1/expression/IfThenElse.java)

**Valor** ::= [**ValorConcreto**](Funcional1/src/lf1/plp/expressions2/expression/ValorConcreto.java)

**ValorConcreto** ::= [**ValorInteiro**](Funcional1/src/lf1/plp/expressions2/expression/ValorInteiro.java)  
    | [**ValorBooleano**](Funcional1/src/lf1/plp/expressions2/expression/ValorBooleano.java)  
    | [**ValorString**](Funcional1/src/lf1/plp/expressions2/expression/ValorString.java)

**ExpUnaria** ::= ["-"](Funcional1/src/lf1/plp/expressions2/expression/ExpMenos.java) **Expressao**  
    | ["not"](Funcional1/src/lf1/plp/expressions2/expression/ExpNot.java) **Expressao**  
    | ["length"](Funcional1/src/lf1/plp/expressions2/expression/ExpLength.java) **Expressao** \
    | ["typeof"](Funcional1/src/lf1/plp/functional1/expression/ExpTypeOf.java) **Expressao**

**ExpBinaria** ::= [**Expressao "+" Expressao**](Funcional1/src/lf1/plp/expressions2/expression/ExpSoma.java)
    | [**Expressao "-" Expressao**](Funcional1/src/lf1/plp/expressions2/expression/ExpSub.java)  
    | [**Expressao "and" Expressao**](Funcional1/src/lf1/plp/expressions2/expression/ExpAnd.java)  
    | [**Expressao "or" Expressao**](Funcional1/src/lf1/plp/expressions2/expression/ExpOr.java)  
    | [**Expressao "==" Expressao**](Funcional1/src/lf1/plp/expressions2/expression/ExpEquals.java)  
    | [**Expressao "++" Expressao**](Funcional1/src/lf1/plp/expressions2/expression/ExpConcat.java)

**ExpDeclaracao** ::= "let" [**DeclaracaoFuncional**](Funcional1/src/lf1/plp/functional1/declaration/DeclaracaoFuncional.java) "in" **Expressao**

**DeclaracaoFuncional** ::= [**DecVariavel**](Funcional1/src/lf1/plp/functional1/declaration/DecVariavel.java)
    | [**DecFuncao**](Funcional1/src/lf1/plp/functional1/declaration/DecFuncao.java)  
    | [**DecComposta**](Funcional1/src/lf1/plp/functional1/declaration/DecComposta.java)

**DecVariavel** ::= [AnotacaoTipo](Funcional1/src/lf1/plp/functional1/util/AnotacaoTipo.java) Id "=" Expressao

**DecFuncao** ::= "fun" **ListId** "=" **Expressao**

**DecComposta** ::= **DeclaracaoFuncional** "," **DeclaracaoFuncional**

**ListId** ::= [AnotacaoTipo](Funcional1/src/lf1/plp/functional1/util/AnotacaoTipo.java) **Id** | [AnotacaoTipo](Funcional1/src/lf1/plp/functional1/util/AnotacaoTipo.java) **Id ListId**

**Aplicacao** ::= **Id** "(" **ListExp** ")"

**ListExp** ::= **Expressao** | **Expressao, ListExp**

**IfThenElse** ::= "if" **Expressao** "then" **Expressao** "else" **Expressao**

**AnotacaoTipo** ::= [TipoPrimitivo](Funcional1/src/lf1/plp/expressions1/util/TipoPrimitivo.java) | [TipoCustom](Funcional1/src/lf1/plp/functional1/util/TipoCustom.java)

**TipoCustom** ::= [TipoPrimitivo](Funcional1/src/lf1/plp/expressions1/util/TipoPrimitivo.java) "|" [TipoPrimitivo](Funcional1/src/lf1/plp/expressions1/util/TipoPrimitivo.java)

**TipoPrimitivo** ::= ["string" | "int" | "boolean"](Funcional1/src/lf1/plp/expressions1/util/TipoPrimitivo.java)