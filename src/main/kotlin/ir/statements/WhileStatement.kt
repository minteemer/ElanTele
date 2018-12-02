package ir.statements

import ir.values.classes.BooleanValue
import ir.expressions.Expression
import ir.Context
import ir.exceptions.InvalidTypeException


class WhileStatement(
        private val expression: Expression,
        private val whileBody: StatementsSequence
) : Statement {

    override fun execute(context: Context) {
        while (true) {
            val conditionExpression = expression.execute(context)
            if (conditionExpression is BooleanValue) {
                if (conditionExpression.value)
                    whileBody.executeAll(context.getChildContext())
                else
                    break
            } else
                throw InvalidTypeException("Expected boolean value for \"while\" loop $conditionExpression")
        }
    }
}