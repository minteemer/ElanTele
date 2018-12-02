package elanTele.ir.statements

import elanTele.ir.expressions.Expression
import elanTele.ir.Context

class DeclarationStatement(
        private val variableName: String,
        private val expression: Expression
) : Statement {

    override fun execute(context: Context) {
        context.createLocalReference(variableName, expression.execute(context))
    }

}