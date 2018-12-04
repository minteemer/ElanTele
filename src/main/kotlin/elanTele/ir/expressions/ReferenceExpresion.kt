package elanTele.ir.expressions

import elanTele.ir.Context
import elanTele.ir.references.Reference
import elanTele.ir.values.Value

data class ReferenceExpresion(val reference: Reference) : Expression {
    /**
     *  return value of reference
     */
    override fun execute(context: Context): Value = reference.getValue(context)

}