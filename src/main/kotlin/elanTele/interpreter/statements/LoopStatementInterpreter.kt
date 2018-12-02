package elanTele.interpreter.statements

import elanTele.ir.statements.ForStatement
import elanTele.ir.statements.Statement
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree
import javax.naming.Context

object LoopStatementInterpreter {

    fun getStatement(tree: ParseTree): Statement {
        if (tree is ElanTeleParser.LoopContext) {
            return when {
                tree.for_loop()!=null -> ForStatementInterpreter.getStatement(tree.for_loop())
                tree.while_loop()!=null -> WhileStatementInterpreter.getStatement(tree.for_loop())
                else -> throw Exception("Exception while traversing tree in LoopStatement for $tree")
            }
        }else{
            throw Exception("Exception while traversing tree in LoopStatement for $tree")
        }

    }

}
