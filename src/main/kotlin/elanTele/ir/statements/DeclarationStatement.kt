package elanTele.ir.statements

import elanTele.ir.Context
import elanTele.ir.expressions.Expression
import elanTele.ir.values.Value
import elanTele.ir.values.classes.EmptyValue

class DeclarationStatement(
        private val variableName: String,
        private val expression: Expression? = null
) : Statement {

    override fun execute(context: Context): Value? {
        context.createLocalReference(variableName,
                expression?.execute(context) ?: EmptyValue())
        return null
    }

}