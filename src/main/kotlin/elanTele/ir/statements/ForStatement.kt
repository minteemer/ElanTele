package elanTele.ir.statements

import elanTele.ir.Context
import elanTele.ir.exceptions.UniterableRangeException
import elanTele.ir.expressions.Expression
import elanTele.ir.values.Value
import elanTele.ir.values.classes.IntegerValue

class ForStatement(
        private val variable: String? = null,
        private val beginValueExpression: Expression,
        private val endValueExpression: Expression,
        private val forBody: StatementsSequence
) : Statement {

    /**
     * Executes [forBody] iterating in range calculated from [beginValueExpression] and [endValueExpression],
     * if [variable] is not null, creates new variable at the top of given [context] and sets its to value
     * in each iteration according to given range.
     * If on one of the iterations [forBody] returns a non-null value, then the execution stops and
     * the value immediately returned.
     *
     * @return result of execution of [forBody] if it returns non-null value, null otherwise
     * @throws UniterableRangeException if [beginValueExpression] or [endValueExpression] returns
     * non-integer value
     */
    override fun execute(context: Context): Value? {
        val begin = beginValueExpression.execute(context)
        val end = endValueExpression.execute(context)
        if (begin is IntegerValue && end is IntegerValue) {
            for (i in begin.value..end.value) {
                val newContext = context.getChildContext()
                variable?.let { newContext.createLocalVariable(it, IntegerValue(i)) }
                forBody.execute(newContext)?.let { return it }
            }
            return null
        } else
            throw UniterableRangeException("Iteration can not be done through $beginValueExpression and $endValueExpression")
    }

}