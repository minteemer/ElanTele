package elanTele.interpreter.statements

import elanTele.interpreter.expressions.ExpressionInterpreter
import elanTele.ir.statements.IfStatement
import elanTele.ir.statements.Statement
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree

object IfStatementInterpreter {

    fun getIfStatement(tree: ParseTree): Statement =
        if (tree is ElanTeleParser.If_expressionContext) {
            IfStatement(
                    ExpressionInterpreter.getExpression(tree.expression()),
                    BodyStatementInterpreter.getBody(tree.body()),
                    tree.else_branch()
                            ?.let { BodyStatementInterpreter.getBody(it.body()) }
            )

        } else {
            throw ClassCastException("IfStatement exception")
        }


}
