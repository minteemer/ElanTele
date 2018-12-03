package elanTele.interpreter.expressions.binary

import elanTele.ir.exceptions.UnresolvedOperatorException
import elanTele.ir.expressions.BinaryExpression
import elanTele.ir.expressions.Expression
import elanTele.ir.expressions.OperatorType
import elanTele.parser.ElanTeleParser

object FactorExpressionInterpreter {

    fun getFactorExpression(tree: ElanTeleParser.FactorContext): Expression =
            tree.factor()?.let { factor ->
                BinaryExpression(
                        TermExpressionInterpreter.getTermExpression(tree.term()),
                        getFactorExpression(factor),
                        tree.getOperator()
                )
            } ?: TermExpressionInterpreter.getTermExpression(tree.term())

    private fun ElanTeleParser.FactorContext.getOperator(): OperatorType = when {
        ADD() != null -> OperatorType.ADD
        SUB() != null -> OperatorType.SUBTRACT
        else -> throw UnresolvedOperatorException("Unresolved operator element")
    }
}