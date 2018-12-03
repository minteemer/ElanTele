package elanTele.ir.statements

import elanTele.ir.Context
import elanTele.ir.exceptions.UniterableRangeException
import elanTele.ir.expressions.Expression
import elanTele.ir.values.Value
import elanTele.ir.values.classes.IntegerValue

class ForStatement(
        private val variable: String? = null,
        private val firstExpression: Expression,
        private val secondExpression: Expression,
        private val forBody: StatementsSequence
) : Statement {

    override fun execute(context: Context): Value? {
        val begin = firstExpression.execute(context)
        val end = secondExpression.execute(context)
        if (begin is IntegerValue && end is IntegerValue) {
            for (i in begin.value..end.value) {
                val newContext = context.getChildContext()
                variable?.let { newContext.createLocalReference(it, IntegerValue(i)) }
                forBody.execute(newContext)?.let { return it }
            }
            return null
        } else
            throw UniterableRangeException("Iteration can not be done through $firstExpression and $secondExpression")
    }
}