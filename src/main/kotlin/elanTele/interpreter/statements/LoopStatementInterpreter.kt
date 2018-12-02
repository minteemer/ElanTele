package elanTele.interpreter.statements

import elanTele.ir.statements.Statement
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree

object LoopStatementInterpreter {

    fun getLoopStatement(tree: ElanTeleParser.LoopContext): Statement =
            tree.for_loop()?.let { ForStatementInterpreter.getForStatement(it) }
                    ?: tree.while_loop()?.let { WhileStatementInterpreter.getWhileStatement(it) }
                    ?: throw Exception("Exception while traversing tree in LoopStatement for $tree") // TODO: better exception


}
