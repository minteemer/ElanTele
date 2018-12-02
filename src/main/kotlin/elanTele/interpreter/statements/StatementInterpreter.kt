package elanTele.interpreter.statements

import elanTele.ir.statements.Statement
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree

object StatementInterpreter {

    fun getStatement(tree: ParseTree): Statement {
        val child = tree.getChild(0)
        return when (child) {
            is ElanTeleParser.DeclarationContext -> {
                DeclarationInterpreter.getDeclaration(child)
            }
            is ElanTeleParser.AssignmentContext ->{
                AssignmentStatementInterpreter.getStatement(child)
            }

            else -> throw ClassCastException("Unknown tree element")
        }
    }

}
