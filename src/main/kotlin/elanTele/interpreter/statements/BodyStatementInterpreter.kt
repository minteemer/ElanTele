package elanTele.interpreter.statements

import elanTele.ir.statements.StatementsSequence
import elanTele.parser.ElanTeleParser

object BodyStatementInterpreter {

    fun getBody(tree: ElanTeleParser.BodyContext): StatementsSequence =
            StatementsSequence(tree.statement().map { StatementInterpreter.getStatement(it) })

}