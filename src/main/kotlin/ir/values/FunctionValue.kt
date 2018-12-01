package ir.values

import ir.Context
import ir.expressions.Expression
import ir.statements.StatementsSequence

class FunctionValue(
        val body: StatementsSequence,
        val returnExpression: Expression
) : Value(ValueClass.REAL) {

    fun call(arguments: Map<String, Value>, context: Context): Value =
            context.getChildContext(arguments).let {
                body.executeAll(it)
                returnExpression.execute(it)
            }

}
