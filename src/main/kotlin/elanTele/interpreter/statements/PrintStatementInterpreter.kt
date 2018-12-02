package elanTele.interpreter.statements

import elanTele.interpreter.expressions.ExpressionInterpreter
import elanTele.ir.expressions.Expression
import elanTele.ir.statements.PrintStatement
import elanTele.ir.statements.Statement
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree

object PrintStatementInterpreter {

    fun getPrintStatement(tree: ParseTree): Statement {
        if (tree is ElanTeleParser.PrintContext) {
            val expression = ArrayList<Expression>()
            for (ex in tree.expression()) {
                expression.add(ExpressionInterpreter.getExpression(ex))
            }
            return PrintStatement(expression)
        } else {
            throw ClassCastException("Print Statement Exception")
        }
    }
}


