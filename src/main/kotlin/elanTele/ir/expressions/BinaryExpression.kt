package elanTele.ir.expressions

import elanTele.ir.Context
import elanTele.ir.exceptions.ExpressionException
import elanTele.ir.values.Value

data class BinaryExpression(val left: Expression, val right: Expression, val operator: OperatorType) : Expression {
    override fun execute(context: Context): Value {
        val leftValue = left.execute(context)
        val rightValue = right.execute(context)
        return when (operator) {
            // @formatter:off
            OperatorType.ADD -> leftValue.add(rightValue)
            OperatorType.SUBTRACT -> leftValue.subtract(rightValue)
            OperatorType.MULTIPLY -> leftValue.multiply(rightValue)
            OperatorType.DIVIDE -> leftValue.divide(rightValue)
            OperatorType.GREATER -> leftValue.greater(rightValue)
            OperatorType.LESS -> leftValue.less(rightValue)
            OperatorType.GREATER_EQUAL -> leftValue.greaterOrEqual(rightValue)
            OperatorType.LESS_EQUAL -> leftValue.lessOrEqual(rightValue)
            OperatorType.EQUAL -> leftValue.equals(rightValue)
            OperatorType.NOT_EQUAL -> leftValue.notEquals(rightValue)
            OperatorType.OR -> leftValue.or(rightValue)
            OperatorType.AND -> leftValue.and(rightValue)
            OperatorType.XOR -> leftValue.xor(rightValue)
            else -> throw ExpressionException("Unknown op passed to ${javaClass.simpleName}")
            // @formatter:on
        }
    }
}