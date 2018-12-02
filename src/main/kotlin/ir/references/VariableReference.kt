package ir.references

import ir.Context
import ir.values.Value

class VariableReference(val identifier: String) : Reference {

    override fun setValue(context: Context, value: Value) =
            context.setValue(identifier, value)


    override fun getValue(context: Context): Value =
            context.getValue(identifier)

}