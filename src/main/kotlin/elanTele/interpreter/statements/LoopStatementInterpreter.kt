package elanTele.interpreter.statements

import elanTele.interpreter.exceptions.LoopStatementException
import elanTele.ir.statements.IfStatement
import elanTele.ir.statements.Statement
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree

object LoopStatementInterpreter {
    /**
     *  @param [tree] is [ElanTeleParser.LoopContext]
     *  @return [Statement] that contains  for or while loop
     */

    fun getLoopStatement(tree: ElanTeleParser.LoopContext): Statement =
            tree.for_loop()?.let { ForStatementInterpreter.getForStatement(it) }
                    ?: tree.while_loop()?.let { WhileStatementInterpreter.getWhileStatement(it) }
                    ?: throw LoopStatementException("Exception while traversing tree in LoopStatement for $tree")


}
