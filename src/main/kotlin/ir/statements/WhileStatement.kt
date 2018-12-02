package ir.statements

import ir.values.BooleanValue
import ir.expressions.Expression
import ir.Context

class WhileStatement(
        private val expression: Expression,
        private val forBody: StatementsSequence
) : Statement {

    override fun execute(context: Context) {
        // TODO: create child context
        while (expression.execute(context).equals(BooleanValue(true)).value)
            forBody.executeAll(context)
    }
}