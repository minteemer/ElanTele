package elanTele.interpreter.expressions

import elanTele.interpreter.references.ReferenceInterpreter
import elanTele.ir.exceptions.UnresolvedOperatorException
import elanTele.ir.expressions.Expression
import elanTele.ir.expressions.OperatorType
import elanTele.ir.expressions.UnaryExpression
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree

object UnaryExpressionInterpreter {

    private fun getOP(tree: ElanTeleParser.UnaryContext): OperatorType? {
        if (tree.IS() != null){
            return OperatorType.IS
        }
        if (tree.NOT() != null){
            return OperatorType.UNARY_NOT
        }
        if (tree.SUB() != null){
            return OperatorType.UNARY_MINUS
        }
        if (tree.ADD() != null){
            return OperatorType.UNARY_PLUS
        } else {
            return null
        }
    }

    fun getUnaryExpression(tree: ParseTree): Expression {
        when (tree) {
            is ElanTeleParser.UnaryContext -> {
                if (tree.reference() != null) {
                    if (tree.typeIndicator() == null){
                        return ReferenceInterpreter.getReference(tree.reference())
                    } else {
                        //TODO 'IS'
                    }
                }
                if (tree.primary() != null && getOP(tree) == null){
                    return PrimaryExpressionInterpreter.getPrimaryExpression(tree.primary())
                } else {
                    return UnaryExpression(PrimaryExpressionInterpreter.getPrimaryExpression(tree.primary()), getOP(tree)!!)
                }
            }
            else -> throw ClassCastException("Unknown tree element")
        }
    }

}