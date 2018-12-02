package elanTele.interpreter.expressions

import elanTele.ir.exceptions.UnresolvedOperatorException
import elanTele.ir.expressions.BinaryExpression
import elanTele.ir.expressions.Expression
import elanTele.ir.expressions.OperatorType
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree

object TermInterpreter {

    private fun getOP(tree: ElanTeleParser.TermContext): OperatorType {
        if (tree.DIV() != null){
            return OperatorType.DIVIDE
        }
        if (tree.MULT() != null){
            return OperatorType.MULTIPLY
        } else {
            throw UnresolvedOperatorException("Unresolved operator element")
        }
    }

    fun getTerm(tree: ParseTree): Expression {
        when (tree) {
            is ElanTeleParser.TermContext-> {
                if (tree.term() == null) {
                    return UnaryExpressionInterpreter.getUnaryExpression(tree.unary())
                } else {
                    return BinaryExpression(UnaryExpressionInterpreter.getUnaryExpression(tree.unary()), TermInterpreter.getTerm(tree.term()), getOP(tree))
                }
            }
            else -> throw ClassCastException("Unknown tree element")
        }
    }

}