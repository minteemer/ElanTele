package elanTele.interpreter.statements


import elanTele.interpreter.expressions.binary.ExpressionInterpreter
import elanTele.ir.statements.AssignmentStatement
import elanTele.ir.statements.DeclarationStatement
import elanTele.ir.statements.StatementsSequence
import elanTele.parser.ElanTeleParser

object DeclarationInterpreter {


    /**
     *  @param [tree] is [ElanTeleParser.DeclarationContext]
     *  @return [StatementsSequence] that contains list of parsed statements
     */
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
