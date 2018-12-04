package elanTele.interpreter.expressions.binary

import elanTele.ir.expressions.BinaryExpression
import elanTele.ir.expressions.Expression
import elanTele.ir.expressions.OperatorType
import elanTele.parser.ElanTeleParser


object DisjunctionInterpreter {

    fun getDisjunctionExpression(tree: ElanTeleParser.DisjuntionContext): Expression =
            tree.disjuntion()?.let {
                BinaryExpression(
                        getDisjunctionExpression(tree.disjuntion()),
                        RelationExpressionInterpreter.getRelationExpression(tree.relation()),
                        OperatorType.OR)
            } ?: RelationExpressionInterpreter.getRelationExpression(tree.relation())


}