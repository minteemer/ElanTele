package elanTele.interpreter.statements

import elanTele.interpreter.expressions.binary.ExpressionInterpreter
import elanTele.ir.statements.IfStatement
import elanTele.parser.ElanTeleParser

object IfStatementInterpreter {
    /**
     *  @param [tree] is [ElanTeleParser.If_expressionContext]
     *  @return [IfStatement] that contains  expression, Body and may contain elseBody
     */
    fun getIfStatement(tree: ElanTeleParser.If_expressionContext): IfStatement =
            IfStatement(
                    ExpressionInterpreter.getExpression(tree.expression()),
                    BodyStatementInterpreter.getBody(tree.body()),
                    tree.else_branch()?.let { BodyStatementInterpreter.getBody(it.body()) }
            )


}
