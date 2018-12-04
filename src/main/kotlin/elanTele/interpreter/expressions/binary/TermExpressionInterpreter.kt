package elanTele.interpreter.expressions.binary

import elanTele.interpreter.expressions.unary.UnaryExpressionInterpreter
import elanTele.ir.exceptions.UnresolvedOperatorException
import elanTele.ir.expressions.BinaryExpression
import elanTele.ir.expressions.Expression
import elanTele.ir.expressions.OperatorType
import elanTele.parser.ElanTeleParser

object TermExpressionInterpreter {

    fun getTermExpression(tree: ElanTeleParser.TermContext): Expression =
            tree.term()?.let { term ->
                BinaryExpression(
                        getTermExpression(term),
                        UnaryExpressionInterpreter.getUnaryExpression(tree.unary()),
                        tree.getOperator()
                )
            } ?: UnaryExpressionInterpreter.getUnaryExpression(tree.unary())

    private fun ElanTeleParser.TermContext.getOperator(): OperatorType = when {
        DIV() != null -> OperatorType.DIVIDE
        MULT() != null -> OperatorType.MULTIPLY
        else -> throw UnresolvedOperatorException("Unresolved operator element")
    }
}