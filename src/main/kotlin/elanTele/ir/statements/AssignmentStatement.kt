package elanTele.ir.statements

import elanTele.ir.expressions.Expression
import elanTele.ir.Context
import elanTele.ir.references.Reference

class AssignmentStatement(
        private val reference: Reference,
        private val expression: Expression
) : Statement {

    override fun execute(context: Context) {
        reference.setValue(context, expression.execute(context))
    }

}