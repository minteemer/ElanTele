package elanTele.ir.statements

import elanTele.ir.Context
import elanTele.ir.expressions.Expression
import elanTele.ir.references.Reference
import elanTele.ir.values.Value

class AssignmentStatement(
        private val reference: Reference,
        private val expression: Expression
) : Statement {

    /**
     * Calculates [expression] and sets value referenced by [reference] to the result
     * @return null
     */
    override fun execute(context: Context): Value? {
        reference.setValue(context, expression.execute(context))
        return null
    }

}