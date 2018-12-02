package elanTele.interpreter.expressions

import elanTele.ir.exceptions.UnresolvedOperatorException
import elanTele.ir.expressions.BinaryExpression
import elanTele.ir.expressions.Expression
import elanTele.ir.expressions.OperatorType
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree


object ExpressionInterpreter {

    private fun getOP(tree: ElanTeleParser.ExpressionContext): OperatorType {
        if (tree.AND() != null){
            return OperatorType.AND
        }
        if (tree.OR() != null){
            return OperatorType.OR
        }
        if (tree.XOR() != null){
            return OperatorType.XOR
        } else {
            throw UnresolvedOperatorException("Unresolved operator element")
        }
    }

    fun getExpression(tree: ParseTree): Expression {
        when (tree) {
            is ElanTeleParser.ExpressionContext -> {
                if (tree.expression() != null) {
                    return BinaryExpression(getExpression(tree.expression()), RelationInterpreter.getRelation(tree.relation()), getOP(tree))
                 } else {
                    return RelationInterpreter.getRelation(tree.relation())
                }
            }
            else -> throw ClassCastException("Unknown tree element")
        }
    }

}