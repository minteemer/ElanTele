package elanTele.interpreter.expressions.binary

import elanTele.interpreter.exceptions.InvalidFactorExpressionException
import elanTele.ir.exceptions.UnresolvedOperatorException
import elanTele.ir.expressions.BinaryExpression
import elanTele.ir.expressions.Expression
import elanTele.ir.expressions.OperatorType
import elanTele.parser.ElanTeleParser

object FactorExpressionInterpreter {

    fun getFactorExpression(tree: ElanTeleParser.FactorContext): Expression =
            tree.term()?.let { term ->
                tree.factor()?.let { factor ->
                    BinaryExpression(
                            getFactorExpression(factor),
                            TermExpressionInterpreter.getTermExpression(term),
                            tree.getOperator()
                    )
                } ?: TermExpressionInterpreter.getTermExpression(term)
            } ?: throw InvalidFactorExpressionException("Invalid FactorContext: $tree")

    private fun ElanTeleParser.FactorContext.getOperator(): OperatorType = when {
        ADD() != null -> OperatorType.ADD
        SUB() != null -> OperatorType.SUBTRACT
        else -> throw UnresolvedOperatorException("Unresolved operator element")
    }
}