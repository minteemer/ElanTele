package ir.statements

import ir.expressions.Expression
import ir.values.Context

class DeclarationStatement(
        private val variableName: String,
        private val expression: Expression
) : Statement {

    override fun execute(context: Context) {
        context.setValue(variableName, expression.execute(context))
    }

}