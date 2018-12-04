package elanTele.interpreter.expressions.binary

import elanTele.ir.expressions.BinaryExpression
import elanTele.ir.expressions.Expression
import elanTele.ir.expressions.OperatorType
import elanTele.parser.ElanTeleParser


object ExpressionInterpreter {
    /**
     *  @param [tree] is [ElanTeleParser.ExpressionContext]
     *  @return [Expression] that contains expression
     */
    fun getExpression(tree: ElanTeleParser.ExpressionContext): Expression =
            tree.expression()?.let {
                BinaryExpression(
                        getExpression(tree.expression()),
                        XorInterpreter.getXorExpression(tree.xor()),
                        OperatorType.XOR)
            } ?: XorInterpreter.getXorExpression(tree.xor())


}