package elanTele.interpreter.statements

import elanTele.interpreter.expressions.binary.ExpressionInterpreter
import elanTele.ir.statements.IfStatement
import elanTele.parser.ElanTeleParser

object IfStatementInterpreter {

    fun getIfStatement(tree: ElanTeleParser.If_expressionContext): IfStatement =
            IfStatement(
                    ExpressionInterpreter.getExpression(tree.expression()),
                    BodyStatementInterpreter.getBody(tree.body()),
                    tree.else_branch()?.let { BodyStatementInterpreter.getBody(it.body()) }
            )


}
