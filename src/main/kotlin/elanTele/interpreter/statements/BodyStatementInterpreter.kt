package elanTele.interpreter.statements

import elanTele.ir.statements.StatementsSequence
import elanTele.parser.ElanTeleParser

object BodyStatementInterpreter {
    /**
     *  @param [ElanTeleParser.BodyContext]
     *  @return StatementSequence that contains list of parsed statements
     */
    fun getBody(tree: ElanTeleParser.BodyContext): StatementsSequence =
            StatementsSequence(tree.statement().map { StatementInterpreter.getStatement(it) })

}