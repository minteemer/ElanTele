package elanTele.interpreter.statements

import elanTele.interpreter.expressions.binary.ExpressionInterpreter
import elanTele.ir.statements.PrintStatement
import elanTele.parser.ElanTeleParser

object PrintStatementInterpreter {

    fun getPrintStatement(tree: ElanTeleParser.PrintContext): PrintStatement =
            PrintStatement(tree.expression().map { ExpressionInterpreter.getExpression(it) })

}


