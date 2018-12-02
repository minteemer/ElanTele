package elanTele.ir.expressions

import elanTele.ir.Context
import elanTele.ir.values.Value

interface Expression {
    fun execute(context: Context): Value
}