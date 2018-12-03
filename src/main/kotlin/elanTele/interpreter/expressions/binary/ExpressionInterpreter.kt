package elanTele.interpreter.expressions.binary

import elanTele.ir.exceptions.UnresolvedOperatorException
import elanTele.ir.expressions.BinaryExpression
import elanTele.ir.expressions.Expression
import elanTele.ir.expressions.OperatorType
import elanTele.parser.ElanTeleParser


object ExpressionInterpreter {

    fun getExpression(tree: ElanTeleParser.ExpressionContext): Expression =
            tree.expression()?.let {
                BinaryExpression(
                        getExpression(tree.expression()),
                        RelationExpressionInterpreter.getRelationExpression(tree.relation()),
                        tree.getOperator()
                )
            } ?: RelationExpressionInterpreter.getRelationExpression(tree.relation())

    private fun ElanTeleParser.ExpressionContext.getOperator(): OperatorType = when {
        AND() != null -> OperatorType.AND
        OR() != null -> OperatorType.OR
        XOR() != null -> OperatorType.XOR
        else -> throw UnresolvedOperatorException("Unresolved operator element")
    }

}