package ir.statements

import ir.expressions.Expression
import ir.Context
import ir.values.IntegerValue
import ir.values.UniterableRangeException
import ir.values.WrongTypeException

class ForStatement(
        private val variable: String? = null,
        private val firstExpression: Expression,
        private val secondExpression: Expression,
        private val forBody: StatementsSequence
) : Statement {

    override fun execute(context: Context) {

        val begin = firstExpression.execute(context)
        val end = secondExpression.execute(context)
        if (begin is IntegerValue && end is IntegerValue) {
            var i = begin.value
            while ( i <=end.value) {
                if (variable != null) {
                    val newContext = context.getChildContext(mapOf(Pair(variable, IntegerValue(i))))
                    forBody.executeAll(newContext)
                    val newI =newContext.getValue(variable)
                    if (newI is IntegerValue)
                        i = newI.value
                    else
                        throw WrongTypeException("Variable ${variable} was reassigned to wrong type.")

                } else {
                    val newContext = context.getChildContext()
                    forBody.executeAll(newContext)
                }
                i++
            }
        } else {
            throw UniterableRangeException("Iteration can not be done through ${firstExpression.toString()} and ${secondExpression.toString()}")
        }

    }
}