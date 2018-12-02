package ir.expressions

import ir.Context
import ir.Reference
import ir.values.Value

data class ReferenceExpresion(val reference: Reference) : Expression {

    override fun execute(context: Context): Value = reference.getValue(context)

}