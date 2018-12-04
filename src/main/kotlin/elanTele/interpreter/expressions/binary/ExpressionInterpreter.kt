package elanTele.interpreter.expressions.binary

import elanTele.ir.expressions.BinaryExpression
import elanTele.ir.expressions.Expression
import elanTele.ir.expressions.OperatorType
import elanTele.parser.ElanTeleParser


object ExpressionInterpreter {

    fun getExpression(tree: ElanTeleParser.ExpressionContext): Expression =
            tree.expression()?.let {
                BinaryExpression(
                        getExpression(tree.expression()),
                        XorInterpreter.getXorExpression(tree.xor()),
                        OperatorType.AND)
            } ?: XorInterpreter.getXorExpression(tree.xor())


}