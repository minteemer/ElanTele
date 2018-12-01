package ir.statements

import ir.expressions.Expression
import ir.values.Context

class AssignmentStatement(
        private val reference: String,
        private val expression: Expression
) : Statement {

    override fun execute(context: Context) {
        context.setValue(reference, expression.execute(context))
    }

}