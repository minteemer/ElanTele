package elanTele.ir.values.classes

import elanTele.ir.Context
import elanTele.ir.expressions.Expression
import elanTele.ir.statements.StatementsSequence
import elanTele.ir.values.Value
import elanTele.ir.values.ValueClass

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
