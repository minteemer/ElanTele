package elanTele.ir.statements

import elanTele.ir.values.classes.BooleanValue
import elanTele.ir.expressions.Expression
import elanTele.ir.Context
import elanTele.ir.exceptions.InvalidTypeException


class WhileStatement(
        private val expression: Expression,
        private val whileBody: StatementsSequence
) : Statement {

    override fun execute(context: Context) {
        while (true) {
            val conditionExpression = expression.execute(context)
            if (conditionExpression is BooleanValue) {
                if (conditionExpression.value)
                    whileBody.execute(context.getChildContext())
                else
                    break
            } else
                throw InvalidTypeException("Expected boolean value for \"while\" loop $conditionExpression")
        }
    }
}