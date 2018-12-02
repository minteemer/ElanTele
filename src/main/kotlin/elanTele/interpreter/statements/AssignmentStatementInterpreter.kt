package elanTele.interpreter.statements

import elanTele.interpreter.expressions.ExpressionInterpreter
import elanTele.interpreter.references.ReferenceInterpreter
import elanTele.ir.statements.AssignmentStatement
import elanTele.ir.statements.Statement
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree

object AssignmentStatementInterpreter {

    fun getStatement(tree: ParseTree): Statement {
        if (tree is ElanTeleParser.AssignmentContext) {
            return AssignmentStatement(ReferenceInterpreter.getReference(tree.reference())
                    , ExpressionInterpreter.getExpression(tree.expression()))
        } else
            throw ClassCastException("Exception during traversing tree in Assignment Interpreter while ${tree.payload}")
    }

}
