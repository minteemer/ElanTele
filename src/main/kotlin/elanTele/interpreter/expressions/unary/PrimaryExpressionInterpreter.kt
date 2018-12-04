package elanTele.interpreter.expressions.unary

import elanTele.interpreter.expressions.binary.ExpressionInterpreter
import elanTele.interpreter.values.FunctionLiteralInterpreter
import elanTele.interpreter.values.LiteralInterpreter
import elanTele.ir.expressions.*
import elanTele.parser.ElanTeleParser

object PrimaryExpressionInterpreter {
    /**
     *  @param [tree] is [ElanTeleParser.PrimaryContext]
     *  @return [Expression] that contains primary expression
     */
    fun getPrimaryExpression(tree: ElanTeleParser.PrimaryContext): Expression =
            tree.literal()?.let {
                it.array()?.let { interpretArray(it) }
                        ?: it.tuple()?.let { interpretTuple(it) }
                        ?: ValueExpression(LiteralInterpreter.getLiteral(it))
            }
                    ?: tree.functionLiteral()?.let { ValueExpression(FunctionLiteralInterpreter.getFunction(it)) }
                    ?: tree.expression()?.let { ExpressionInterpreter.getExpression(it) }
                    ?: tree.ReadInt()?.let { ReadExpression(ReadExpression.InputType.INTEGER) }
                    ?: tree.ReadString()?.let { ReadExpression(ReadExpression.InputType.STRING) }
                    ?: tree.ReadReal()?.let { ReadExpression(ReadExpression.InputType.REAL) }
                    ?: throw ClassCastException("Unknown tree element")

    /**
     *  @param [tree] is [ElanTeleParser.TupleContext]
     *  @return [TupleCreationExpression] that contains list of expression which will be executed in run-time
     */
    private fun interpretTuple(tupleContext: ElanTeleParser.TupleContext): TupleCreationExpression =
            TupleCreationExpression(
                    tupleContext.tupleElement().map { tupleElement ->
                        tupleElement.Identifier()?.toString() to
                                ExpressionInterpreter.getExpression(tupleElement.expression())
                    }
            )


    /**
     *  @param [tree] is [ElanTeleParser.ArrayContext]
     *  @return [ArrayCreationExpression] that contains list of expression which will be executed in run-time
     */
    private fun interpretArray(arrayContext: ElanTeleParser.ArrayContext): ArrayCreationExpression =
            ArrayCreationExpression(
                    arrayContext.expression().mapIndexed { index, expressionContext ->
                        index + 1 to ExpressionInterpreter.getExpression(expressionContext)
                    }.associate { it }
            )
}