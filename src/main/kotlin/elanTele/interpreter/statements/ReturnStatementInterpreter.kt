package elanTele.interpreter.statements

import elanTele.interpreter.expressions.binary.ExpressionInterpreter
import elanTele.ir.statements.PrintStatement
import elanTele.ir.statements.ReturnStatement
import elanTele.parser.ElanTeleParser

object ReturnStatementInterpreter {

    fun getReturnStatement(tree: ElanTeleParser.Return_expressionContext): ReturnStatement =
            tree.expression()?.let { ReturnStatement(ExpressionInterpreter.getExpression(it)) }
                    ?: throw Exception() // TODO: proper exception

}


