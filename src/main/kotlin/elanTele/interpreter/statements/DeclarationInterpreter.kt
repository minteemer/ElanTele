package elanTele.interpreter.statements


import elanTele.ElanTeleSyntaxTreeGenearator
import elanTele.ir.statements.DeclarationStatement
import elanTele.ir.statements.Statement
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree

object DeclarationInterpreter {

    fun getDeclaration(tree: ElanTeleParser.DeclarationContext): Statement {
        ElanTeleSyntaxTreeGenearator.printJsonTree(tree)
        TODO("Implement declaration parsing")
    }

}
