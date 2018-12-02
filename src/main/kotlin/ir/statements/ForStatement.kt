package ir.statements

import ir.expressions.Expression
import ir.Context
import ir.values.IntegerValue
import ir.values.UniterableRangeException

class ForStatement(
        private val variable: String? = null,
        private val firstExpression: Expression,
        private val secondExpression: Expression,
        private val forBody: StatementsSequence
) : Statement {

    override fun execute(context: Context) {
        // TODO: create child context
        val begin = firstExpression.execute(context)
        val end = secondExpression.execute(context)
        if (begin is IntegerValue && end is IntegerValue) {
            for (i in begin.value..end.value) {
                if (variable != null) {
                    val newContext = context.getChildContext(mapOf(Pair(variable, IntegerValue(i))))
                    forBody.executeAll(newContext)
                } else {
                    forBody.executeAll(context)
                }

            }
        } else {
            throw UniterableRangeException("Iteration can not be done through ${firstExpression.toString()} and ${secondExpression.toString()}")
        }

    }
}