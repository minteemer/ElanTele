package ir.expressions

import ir.Context
import ir.Reference
import ir.values.Value

data class ValueExpression(val value: Value) : Expression {

    override fun execute(context: Context): Value = value

}