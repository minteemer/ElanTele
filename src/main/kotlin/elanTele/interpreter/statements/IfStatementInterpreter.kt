package elanTele.interpreter.statements

import elanTele.interpreter.expressions.ExpressionInterpreter
import elanTele.ir.statements.ForStatement
import elanTele.ir.statements.IfStatement
import elanTele.ir.statements.Statement
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree

object IfStatementInterpreter {

    fun getStatement(tree: ParseTree): Statement {
        if (tree is ElanTeleParser.If_expressionContext) {
            val elseBody = tree.else_branch()?.
                    let { it -> BodyStatementInterpreter.getBody(it.body()) }
            return IfStatement(ExpressionInterpreter.getExpression(tree.expression()),
                    BodyStatementInterpreter.getBody(tree.body()),
                    elseBody)

        } else {
            throw Exception("IfStatement exception")
        }
    }

}
