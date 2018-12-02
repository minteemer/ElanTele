package elanTele.interpreter.statements

import elanTele.interpreter.expressions.ExpressionInterpreter
import elanTele.ir.expressions.Expression
import elanTele.ir.statements.PrintStatement
import elanTele.ir.statements.Statement
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree

object PrintStatementInterpreter {

    fun getPrintStatement(tree: ElanTeleParser.PrintContext): PrintStatement =
            PrintStatement(tree.expression().map { ExpressionInterpreter.getExpression(it) })

}


