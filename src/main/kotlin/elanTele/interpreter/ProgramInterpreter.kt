package elanTele.interpreter

import elanTele.interpreter.statements.BodyStatementInterpreter
import elanTele.interpreter.statements.StatementInterpreter
import elanTele.ir.statements.Statement
import elanTele.ir.statements.StatementsSequence
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree

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