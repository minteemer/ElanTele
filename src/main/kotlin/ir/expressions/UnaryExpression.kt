package ir.expressions

import ir.Context
import ir.values.Value

class UnaryExpression(val a: Value, val op: OperatorType) : Expression {
    override fun execute(context: Context): Value {
        return when (op) {
            // @formatter:off
            OperatorType.UNARY_NOT   -> a.not()
            OperatorType.UNARY_MINUS -> a.unaryMinus()
            OperatorType.UNARY_PLUS  -> a
            else -> throw ExpressionException("Unknown op passed to ${javaClass.simpleName}")
            // @formatter:on
        }
    }

    override fun toString(): String {
        return "UnaryExpression(a=$a, op=$op)"
    }
}