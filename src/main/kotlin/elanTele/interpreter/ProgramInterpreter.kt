package elanTele.interpreter

import elanTele.interpreter.statements.BodyStatementInterpreter
import elanTele.ir.statements.StatementsSequence
import elanTele.parser.ElanTeleParser

object ProgramInterpreter {
    /**
     *  @param [tree] is [ElanTeleParser.ProgramContext]
     *  Entry point of AST
     *  @return [StatementsSequence] that list of statements
     *
     */
    fun getProgram(tree: ElanTeleParser.ProgramContext): StatementsSequence =
            BodyStatementInterpreter.getBody(tree.body())

}