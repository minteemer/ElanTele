package elanTele.interpreter

import elanTele.interpreter.statements.BodyStatementInterpreter
import elanTele.interpreter.statements.StatementInterpreter
import elanTele.ir.statements.Statement
import elanTele.ir.statements.StatementsSequence
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree

object ProgramInterpreter {

    fun getProgram(tree: ParseTree): StatementsSequence {
        if (tree is ElanTeleParser.ProgramContext) {
            return BodyStatementInterpreter.getBody(tree.body())
        } else {
            throw ClassCastException("Exception in Statement Sequence in ${tree.payload}")
        }

    }


}