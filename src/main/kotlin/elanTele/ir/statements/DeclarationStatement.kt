package elanTele.ir.statements

import elanTele.ir.Context
import elanTele.ir.expressions.Expression
import elanTele.ir.values.Value
import elanTele.ir.values.classes.EmptyValue

class DeclarationStatement(
        private val variableName: String,
        private val expression: Expression? = null
) : Statement {

    /**
     * Creates new variable at the top of given [context] with given [variableName],
     * sets value of the variable to the value calculated by [expression] if it is
     * not null, otherwise sets value to [EmptyValue]
     * @return null
     */
    override fun execute(context: Context): Value? {
        context.createLocalVariable(variableName,
                expression?.execute(context) ?: EmptyValue())
        return null
    }

}