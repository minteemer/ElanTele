package elanTele.ir.expressions

import elanTele.ir.Context
import elanTele.ir.values.ValueClass
import elanTele.ir.values.classes.BooleanValue

data class IsExpression(val expression: Expression, val className: ValueClass) : Expression {
    override fun execute(context: Context): BooleanValue {
        val value = expression.execute(context)
        return BooleanValue(value.valueClass == className)
    }
}
