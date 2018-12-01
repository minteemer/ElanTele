package ir.statements

import ir.expressions.Expression
import ir.Context
import ir.values.BooleanValue

class IfStatement(
        private val expression: Expression,
        private val ifBody: StatementsSequence,
        private val elseBody:StatementsSequence? =null
) : Statement {

    override fun execute(context: Context) {
        if (expression.execute(context).equals(BooleanValue(true)).value)
            ifBody.executeAll(context)
        else elseBody?.executeAll(context)

    }
}