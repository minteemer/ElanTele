package elanTele.interpreter.statements

import elanTele.interpreter.expressions.binary.ExpressionInterpreter
import elanTele.ir.statements.IfStatement
import elanTele.ir.statements.PrintStatement
import elanTele.parser.ElanTeleParser

object PrintStatementInterpreter {
    /**
     *  @param [tree] is [ElanTeleParser.PrintContext]
     *  @return [PrintStatement] that contains list of expressions to print
     */
    fun getPrintStatement(tree: ElanTeleParser.PrintContext): PrintStatement =
            PrintStatement(tree.expression().map { ExpressionInterpreter.getExpression(it) })

}


