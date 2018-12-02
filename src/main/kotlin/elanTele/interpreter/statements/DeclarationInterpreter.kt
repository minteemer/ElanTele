package elanTele.interpreter.statements


import elanTele.interpreter.expressions.ExpressionInterpreter
import elanTele.ir.statements.DeclarationStatement
import elanTele.ir.statements.Statement
import elanTele.ir.statements.StatementsSequence
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree
import java.util.*

object DeclarationInterpreter {

    fun getDeclaration(tree: ParseTree): Statement {
        if (tree is ElanTeleParser.DeclarationContext) {
            val declarations = ArrayList<Statement>()
            for (dec in tree.variableDefinition()) {
                declarations.add(
                        DeclarationStatement(
                                dec.Identifier().toString(),
                                dec.expression()?.let { ExpressionInterpreter.getExpression(it) }
                        )
                )
            }
            return StatementsSequence(declarations)
        } else
            throw ClassCastException("Exception during traversing tree in Declaration Interpreter while ${tree.payload}")
    }

}
