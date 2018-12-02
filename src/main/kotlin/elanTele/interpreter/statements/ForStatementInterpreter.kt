package elanTele.interpreter.statements

import elanTele.interpreter.expressions.ExpressionInterpreter
import elanTele.ir.statements.ForStatement
import elanTele.parser.ElanTeleParser

object ForStatementInterpreter {

    fun getForStatement(tree: ElanTeleParser.For_loopContext): ForStatement =
            ForStatement(
                    tree.Identifier()?.toString(),
                    ExpressionInterpreter.getExpression(tree.expression(0)),
                    ExpressionInterpreter.getExpression(tree.expression(1)),
                    BodyStatementInterpreter.getBody(tree.body())
            )


}
