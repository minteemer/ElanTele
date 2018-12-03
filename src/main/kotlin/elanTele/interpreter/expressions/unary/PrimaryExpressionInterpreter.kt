package elanTele.interpreter.expressions.unary

import elanTele.interpreter.expressions.binary.ExpressionInterpreter
import elanTele.interpreter.values.FunctionLiteralInterpreter
import elanTele.interpreter.values.LiteralInterpreter
import elanTele.ir.expressions.Expression
import elanTele.ir.expressions.ReadExpression
import elanTele.ir.expressions.ValueExpression
import elanTele.parser.ElanTeleParser

object PrimaryExpressionInterpreter {

    fun getPrimaryExpression(tree: ElanTeleParser.PrimaryContext): Expression =
            tree.literal()?.let { ValueExpression(LiteralInterpreter.getLiteral(it)) }
                    ?: tree.functionLiteral()?.let { ValueExpression(FunctionLiteralInterpreter.getFunction(it)) }
                    ?: tree.expression()?.let { ExpressionInterpreter.getExpression(it) }
                    ?: tree.ReadInt()?.let { ReadExpression(ReadExpression.InputType.INTEGER) }
                    ?: tree.ReadString()?.let { ReadExpression(ReadExpression.InputType.STRING) }
                    ?: tree.ReadReal()?.let { ReadExpression(ReadExpression.InputType.REAL) }
                    ?: throw ClassCastException("Unknown tree element")

}