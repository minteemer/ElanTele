package elanTele.ir.expressions

import elanTele.ir.Context
import elanTele.ir.Reference
import elanTele.ir.values.Value

data class ReferenceExpresion(val reference: Reference) : Expression {

    override fun execute(context: Context): Value = reference.getValue(context)

}