package elanTele.interpreter.statements

import elanTele.interpreter.expressions.binary.ExpressionInterpreter
import elanTele.interpreter.references.ReferenceInterpreter
import elanTele.ir.statements.AssignmentStatement
import elanTele.parser.ElanTeleParser

object AssignmentStatementInterpreter {

    fun getAssignmentStatement(tree: ElanTeleParser.AssignmentContext): AssignmentStatement =
            AssignmentStatement(
                    ReferenceInterpreter.getReference(tree.reference()),
                    ExpressionInterpreter.getExpression(tree.expression())
            )

}
