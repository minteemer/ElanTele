package elanTele.interpreter.statements

import elanTele.interpreter.expressions.binary.ExpressionInterpreter
import elanTele.ir.statements.ForStatement
import elanTele.ir.statements.StatementsSequence
import elanTele.parser.ElanTeleParser

object ForStatementInterpreter {
    /**
     *  @param [tree] is [ElanTeleParser.For_loopContext]
     *  @return [ForStatement] that contains 2 expression, body and may contain Identifier
     */
    fun getForStatement(tree: ElanTeleParser.For_loopContext): ForStatement =
            ForStatement(
                    tree.Identifier()?.toString(),
                    ExpressionInterpreter.getExpression(tree.expression(0)),
                    ExpressionInterpreter.getExpression(tree.expression(1)),
                    BodyStatementInterpreter.getBody(tree.body())
            )


}
