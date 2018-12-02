package elanTele.ir.statements

import elanTele.ir.expressions.Expression
import elanTele.ir.Context

class AssignmentStatement(
        private val reference: String,
        private val expression: Expression
) : Statement {

    override fun execute(context: Context) {
        context.setValue(reference, expression.execute(context))
    }

}