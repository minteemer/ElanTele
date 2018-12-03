package elanTele.ir.statements

import elanTele.ir.Context
import elanTele.ir.exceptions.InvalidTypeException
import elanTele.ir.expressions.Expression
import elanTele.ir.values.Value
import elanTele.ir.values.classes.BooleanValue


class WhileStatement(
        private val expression: Expression,
        private val whileBody: StatementsSequence
) : Statement {

    override fun execute(context: Context): Value? {
        while (true) {
            val conditionExpression = expression.execute(context)
            if (conditionExpression is BooleanValue) {
                if (conditionExpression.value)
                    whileBody.execute(context.getChildContext())?.let { return it }
                else
                    break
            } else
                throw InvalidTypeException("Expected boolean value for \"while\" loop $conditionExpression")
        }
        return null
    }
}