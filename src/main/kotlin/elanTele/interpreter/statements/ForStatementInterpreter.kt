package elanTele.interpreter.statements

import elanTele.interpreter.expressions.ExpressionInterpreter
import elanTele.ir.statements.ForStatement
import elanTele.ir.statements.Statement
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree

object ForStatementInterpreter {

    fun getForStatement(tree: ParseTree): Statement {
        if (tree is ElanTeleParser.For_loopContext) {
            return ForStatement(tree.Identifier()?.toString(),
                    ExpressionInterpreter.getExpression(tree.expression(0)),
                    ExpressionInterpreter.getExpression(tree.expression(1)),
                    BodyStatementInterpreter.getBody(tree.body()))
        } else {
            throw ClassCastException("For loop statement exception")
        }
    }

}
