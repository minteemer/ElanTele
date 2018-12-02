package elanTele.interpreter.statements

import elanTele.ir.statements.Statement
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree

object AssignmentStatementInterpreter {

    fun getStatement(tree: ParseTree): Statement {
        val child = tree.getChild(0)
        //TODO Implement function
        when (child) {
            is ElanTeleParser.DeclarationContext -> {
                return DeclarationInterpreter.getDeclaration(child)
            }
            else -> throw ClassCastException("Unknown tree element")
        }
    }

}
