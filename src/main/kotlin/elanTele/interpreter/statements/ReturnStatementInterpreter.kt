package elanTele.interpreter.statements

import elanTele.interpreter.exceptions.InvalidReturnStatementException
import elanTele.interpreter.expressions.binary.ExpressionInterpreter
import elanTele.ir.statements.ReturnStatement
import elanTele.parser.ElanTeleParser

object ReturnStatementInterpreter {
    /**
     *  @param [tree] is [ElanTeleParser.Return_expressionContext]
     *  @return [ReturnStatement] that contains expression
     */
    fun getReturnStatement(tree: ElanTeleParser.Return_expressionContext): ReturnStatement =
            tree.expression()?.let { ReturnStatement(ExpressionInterpreter.getExpression(it)) }
                    ?: throw InvalidReturnStatementException("Can't parse return statement: $tree")

}


