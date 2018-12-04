package elanTele.interpreter.expressions.binary

import elanTele.ir.expressions.BinaryExpression
import elanTele.ir.expressions.Expression
import elanTele.ir.expressions.OperatorType
import elanTele.parser.ElanTeleParser


object DisjunctionInterpreter {

    /**
     *  @param [tree] is [ElanTeleParser.DisjuntionContext]
     *  @return [Expression] that contains  disjunction expression
     */
    fun getDisjunctionExpression(tree: ElanTeleParser.DisjuntionContext): Expression =
            tree.disjuntion()?.let {
                BinaryExpression(
                        getDisjunctionExpression(tree.disjuntion()),
                        RelationExpressionInterpreter.getRelationExpression(tree.relation()),
                        OperatorType.AND)
            } ?: RelationExpressionInterpreter.getRelationExpression(tree.relation())


}