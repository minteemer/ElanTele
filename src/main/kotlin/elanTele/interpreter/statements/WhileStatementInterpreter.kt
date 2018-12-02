package elanTele.interpreter.statements

import elanTele.interpreter.expressions.ExpressionInterpreter
import elanTele.ir.statements.ForStatement
import elanTele.ir.statements.Statement
import elanTele.ir.statements.WhileStatement
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree

object WhileStatementInterpreter {

    fun getWhileStatement(tree: ParseTree): Statement {
        if (tree is ElanTeleParser.While_loopContext) {
            return WhileStatement(ExpressionInterpreter.getExpression(tree.expression()),
                    BodyStatementInterpreter.getBody(tree.body()))
        } else {
            throw ClassCastException("WhileStatementInterpreter exception")
        }
    }

}
