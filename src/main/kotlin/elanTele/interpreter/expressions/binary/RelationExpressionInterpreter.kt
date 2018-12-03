package elanTele.interpreter.expressions.binary

import elanTele.ir.exceptions.UnresolvedOperatorException
import elanTele.ir.expressions.BinaryExpression
import elanTele.ir.expressions.Expression
import elanTele.ir.expressions.OperatorType
import elanTele.parser.ElanTeleParser

object RelationExpressionInterpreter {

    fun getRelationExpression(tree: ElanTeleParser.RelationContext): Expression =
            if (tree.factor(1) != null) {
                BinaryExpression(
                        FactorExpressionInterpreter.getFactorExpression(tree.factor(0)),
                        FactorExpressionInterpreter.getFactorExpression(tree.factor(1)),
                        tree.getOperator()
                )
            } else {
                FactorExpressionInterpreter.getFactorExpression(tree.factor(0))
            }


    private fun ElanTeleParser.RelationContext.getOperator(): OperatorType =
            EQEQ()?.let { OperatorType.EQUAL }
                    ?: EXCL_EQ()?.let { OperatorType.NOT_EQUAL }
                    ?: LANGLE()?.let { OperatorType.LESS }
                    ?: RANGLE()?.let { OperatorType.GREATER }
                    ?: LE()?.let { OperatorType.LESS_EQUAL }
                    ?: GE()?.let { OperatorType.GREATER_EQUAL }
                    ?: throw UnresolvedOperatorException("Unresolved operator element")

}