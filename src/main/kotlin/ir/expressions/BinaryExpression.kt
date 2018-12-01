package ir.expressions

import ir.Context
import ir.values.Value

class BinaryExpression(val a: Value, val b: Value, val op: OperatorType) : Expression {
    override fun execute(context: Context): Value {
        return when (op) {
            // @formatter:off
            OperatorType.ADD           -> a.add(b)
            OperatorType.SUBTRACT      -> a.subtract(b)
            OperatorType.MULTIPLY      -> a.multiply(b)
            OperatorType.DIVIDE        -> a.divide(b)
            OperatorType.GREATER       -> a.greater(b)
            OperatorType.LESS          -> a.less(b)
            OperatorType.GREATER_EQUAL -> a.greaterOrEqual(b)
            OperatorType.LESS_EQUAL    -> a.lessOrEqual(b)
            OperatorType.EQUAL         -> a.equals(b)
            OperatorType.NOT_EQUAL     -> a.notEquals(b)
            OperatorType.OR            -> a.or(b)
            OperatorType.AND           -> a.and(b)
            OperatorType.XOR           -> a.xor(b)
            else -> throw ExpressionException("Unknown op passed to ${javaClass.simpleName}")
            // @formatter:on
        }
    }

    override fun toString(): String {
        return "BinaryExpression(a=$a, b=$b, op=$op)"
    }
}