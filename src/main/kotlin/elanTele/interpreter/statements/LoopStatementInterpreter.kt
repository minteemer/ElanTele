package elanTele.interpreter.statements

import elanTele.ir.statements.Statement
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree

object LoopStatementInterpreter {

    fun getLoopStatement(tree: ParseTree): Statement {
        if (tree is ElanTeleParser.LoopContext) {
            return when {
                tree.for_loop()!=null -> ForStatementInterpreter.getForStatement(tree.for_loop())
                tree.while_loop()!=null -> WhileStatementInterpreter.getWhileStatement(tree.for_loop())
                else -> throw Exception("Exception while traversing tree in LoopStatement for $tree")
            }
        }else{
            throw ClassCastException("Exception while traversing tree in LoopStatement for $tree")
        }

    }

}
