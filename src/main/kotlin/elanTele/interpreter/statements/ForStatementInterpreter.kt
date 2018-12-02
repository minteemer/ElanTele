package elanTele.interpreter.statements

import elanTele.ir.statements.ForStatement
import elanTele.ir.statements.Statement
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree
import javax.naming.Context

object ForStatementInterpreter {

    fun getStatement(tree: ParseTree): Statement {
        if (tree is ElanTeleParser.LoopContext) {
            if (tree.for_loop()==null){

            }
        }
        throw Exception("")
    }

}
