package elanTele.interpreter.statements

import elanTele.interpreter.expressions.binary.ExpressionInterpreter
import elanTele.ir.statements.WhileStatement
import elanTele.parser.ElanTeleParser

object WhileStatementInterpreter {

    fun getWhileStatement(tree: ElanTeleParser.While_loopContext): WhileStatement =
            WhileStatement(
                    ExpressionInterpreter.getExpression(tree.expression()),
                    BodyStatementInterpreter.getBody(tree.body())
            )

}
