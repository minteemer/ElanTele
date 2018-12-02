package ir.statements

import ir.expressions.Expression
import ir.Context
import ir.values.BooleanValue
import ir.values.IntegerValue

class IfStatement(
        private val expression: Expression,
        private val ifBody: StatementsSequence,
        private val elseBody: StatementsSequence? = null
) : Statement {

    override fun execute(context: Context) {

        if (expression.execute(context).equals(BooleanValue(true)).value) {
            val newContext = context.getChildContext()
            ifBody.executeAll(newContext)
        }
        else {
            val newContext = context.getChildContext()
            elseBody?.executeAll(newContext)
        }
    }
}