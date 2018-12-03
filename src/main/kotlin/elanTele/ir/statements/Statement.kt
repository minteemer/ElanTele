package elanTele.ir.statements

import elanTele.ir.Context
import elanTele.ir.values.Value

interface Statement {
    fun execute(context: Context): Value?
}
