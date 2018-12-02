package elanTele.ir.references

import elanTele.ir.Context
import elanTele.ir.values.Value

data class VariableReference(val identifier: String) : Reference {

    override fun setValue(context: Context, value: Value) =
            context.setValue(identifier, value)


    override fun getValue(context: Context): Value =
            context.getValue(identifier)

}