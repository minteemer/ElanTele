package elanTele.interpreter.statements


import elanTele.interpreter.expressions.ExpressionInterpreter
import elanTele.ir.statements.DeclarationStatement
import elanTele.ir.statements.Statement
import elanTele.ir.statements.StatementsSequence
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree
import java.util.*

object DeclarationInterpreter {

    fun getDeclaration(tree: ElanTeleParser.DeclarationContext): StatementsSequence =
            StatementsSequence(
                    tree.variableDefinition().map { definition ->
                        DeclarationStatement(
                                definition.Identifier().toString(),
                                definition.expression()?.let { ExpressionInterpreter.getExpression(it) }
                        )
                    }
            )

}
