package elanTele.ir.expressions

import elanTele.ir.Context
import elanTele.ir.values.Value

data class ValueExpression(val value: Value) : Expression {

    override fun execute(context: Context): Value = value

}