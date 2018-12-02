package elanTele.interpreter.statements


import elanTele.ElanTeleSyntaxTreeGenearator
import elanTele.interpreter.expressions.ExpressionInterpreter
import elanTele.interpreter.references.ReferenceInterpreter
import elanTele.ir.statements.AssignmentStatement
import elanTele.ir.statements.DeclarationStatement
import elanTele.ir.statements.Statement
import elanTele.ir.statements.StatementsSequence
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree
import java.lang.Exception
import java.util.ArrayList

object DeclarationInterpreter {

    fun getDeclaration(tree: ParseTree): Statement {
        if (tree is ElanTeleParser.DeclarationContext) {
           val declarations = ArrayList<Statement>()
           for (dec in tree.variableDefinition()){
               declarations.add(DeclarationStatement(dec.Identifier().toString(),
                       ExpressionInterpreter.getExpression(dec.expression())))
           }
            return StatementsSequence(declarations)
        }else
            throw Exception("Exception during traversing tree in Declaration Interpreter while ${tree.payload}")
    }

}
