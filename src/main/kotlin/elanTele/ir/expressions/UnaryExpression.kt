package elanTele.ir.expressions

import elanTele.ir.Context
import elanTele.ir.exceptions.ExpressionException
import elanTele.ir.values.Value

data class UnaryExpression(val expression: Expression, val op: OperatorType) : Expression {
    override fun execute(context: Context): Value {
        val value = expression.execute(context)
        return when (op) {
            // @formatter:off
            OperatorType.UNARY_NOT -> value.not()
            OperatorType.UNARY_MINUS -> value.unaryMinus()
            OperatorType.UNARY_PLUS -> value
            else -> throw ExpressionException("Unknown op passed to ${javaClass.simpleName}")
            // @formatter:on
        }
    }
}