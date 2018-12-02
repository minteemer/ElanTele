package elanTele.interpreter.statements

import elanTele.ir.statements.Statement
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree

object StatementInterpreter {

    fun getStatement(tree: ParseTree): Statement {
        val child = tree.getChild(0)
        when (child) {
            is ElanTeleParser.DeclarationContext -> {
                return DeclarationInterpreter.getDeclaration(child)
            }
            else -> throw ClassCastException("Unknown tree element")
        }
    }

}
