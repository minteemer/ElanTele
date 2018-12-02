package ir.statements

import ir.expressions.Expression
import ir.Context

class DeclarationStatement(
        private val variableName: String,
        private val expression: Expression
) : Statement {

    override fun execute(context: Context) {
        context.createLocalReference(variableName, expression.execute(context))
    }

}