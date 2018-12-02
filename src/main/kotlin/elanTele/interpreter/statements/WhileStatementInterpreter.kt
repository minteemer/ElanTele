package elanTele.interpreter.statements

import elanTele.interpreter.expressions.ExpressionInterpreter
import elanTele.ir.statements.Statement
import elanTele.ir.statements.WhileStatement
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree

object WhileStatementInterpreter {

    fun getWhileStatement(tree: ElanTeleParser.While_loopContext): WhileStatement =
            WhileStatement(
                    ExpressionInterpreter.getExpression(tree.expression()),
                    BodyStatementInterpreter.getBody(tree.body())
            )

}
