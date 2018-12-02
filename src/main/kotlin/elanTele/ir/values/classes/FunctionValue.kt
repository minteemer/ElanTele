package elanTele.ir.values.classes

import elanTele.ir.Context
import elanTele.ir.expressions.Expression
import elanTele.ir.statements.StatementsSequence
import elanTele.ir.values.Value
import elanTele.ir.values.ValueClass

class FunctionValue(
        val parameters: List<String>,
        val body: StatementsSequence,
        val returnExpression: Expression
) : Value(ValueClass.REAL) {

    fun call(arguments: List<Value>, context: Context): Value =
            arguments.mapIndexed { index, value -> parameters[index] to value }
                    .associate { it }
                    .let {arguments ->
                        context.getChildContext(arguments).let {
                            body.execute(it)
                            returnExpression.execute(it)
                        }
                    }

}
