package elanTele.ir.statements

import elanTele.ir.Context
import elanTele.ir.exceptions.InvalidTypeException
import elanTele.ir.expressions.Expression
import elanTele.ir.values.Value
import elanTele.ir.values.classes.BooleanValue

class IfStatement(
        private val conditionExpression: Expression,
        private val ifBody: StatementsSequence,
        private val elseBody: StatementsSequence? = null
) : Statement {

    override fun execute(context: Context): Value? {
        val condition = conditionExpression.execute(context)
        return if (condition is BooleanValue) {
            val newContext = context.getChildContext()
            if (condition.value)
                ifBody.execute(newContext)
            else
                elseBody?.execute(newContext)
        } else
            throw InvalidTypeException("Expected boolean value for \"if\" condition $conditionExpression")
    }
}