package ir.expressions

import ir.Context
import ir.values.Value

data class UnaryExpression(val expression: Expression, val op: OperatorType) : Expression {
    override fun execute(context: Context): Value {
        val value = expression.execute(context)
        return when (op) {
            // @formatter:off
            OperatorType.UNARY_NOT   -> value.not()
            OperatorType.UNARY_MINUS -> value.unaryMinus()
            OperatorType.UNARY_PLUS  -> value
            else -> throw ExpressionException("Unknown op passed to ${javaClass.simpleName}")
            // @formatter:on
        }
    }
}