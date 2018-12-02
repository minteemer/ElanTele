package elanTele.interpreter.expressions

import elanTele.ir.expressions.Expression
import elanTele.ir.expressions.ReadExpression
import elanTele.ir.expressions.UnaryExpression
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree

object PrimaryExpressionInterpreter {

    fun getPrimaryExpression(tree: ParseTree): Expression {
        when (tree) {
            is ElanTeleParser.PrimaryContext -> {
                return when {
                    //TODO tree.literal() != null -> LiteralExpressionInterpreter.getLiteralExpression()
                    tree.ReadInt() != null -> ReadExpression(ReadExpression.InputType.INTEGER)
                    tree.ReadString() != null -> ReadExpression(ReadExpression.InputType.STRING)
                    tree.ReadReal() != null -> ReadExpression(ReadExpression.InputType.REAL)
                    //TODO tree.functionLiteral() != null ->
                    tree.expression() != null -> ExpressionInterpreter.getExpression(tree.expression())
                    else -> {
                        throw ClassCastException("Unknown tree element")
                    }
                }
            }
            else -> throw ClassCastException("Unknown tree element")
        }
    }

}