package elanTele.ir.expressions

import elanTele.ir.Context
import elanTele.ir.values.ValueClass
import elanTele.ir.values.classes.BooleanValue

data class IsExpression(val expression: Expression, val className: ValueClass) : Expression {
    /**
     *  @param [Context] of program depending on it expressions are executed
     *  @return [BooleanValue] that verifies if expression belongs to ValuesClass
     *
     */
    override fun execute(context: Context): BooleanValue {
        val value = expression.execute(context)
        return BooleanValue(value.valueClass == className)
    }
}
