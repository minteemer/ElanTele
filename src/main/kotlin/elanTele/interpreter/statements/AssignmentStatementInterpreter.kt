package elanTele.interpreter.statements

import elanTele.interpreter.expressions.ExpressionInterpreter
import elanTele.interpreter.references.ReferenceInterpreter
import elanTele.ir.statements.AssignmentStatement
import elanTele.ir.statements.Statement
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree

object AssignmentStatementInterpreter {

    fun getAssignmentStatement(tree: ElanTeleParser.AssignmentContext): AssignmentStatement =
            AssignmentStatement(
                    ReferenceInterpreter.getReference(tree.reference()),
                    ExpressionInterpreter.getExpression(tree.expression())
            )

}
