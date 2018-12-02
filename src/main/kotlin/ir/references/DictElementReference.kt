package ir.references

import ir.Context
import ir.values.ArrayValue
import ir.values.DictValue
import ir.values.IntegerValue
import ir.values.Value

data class DictElementReference(
        val arrayReference: Reference,
        val identifier: String
) : Reference {

    override fun setValue(context: Context, value: Value) {
        val dict = arrayReference.getValue(context)
        if (dict is DictValue) {
            dict.setElement(identifier, value)
        } else {
            throw InvalidVariableTypeException("Expected ArrayValue, got ${dict.javaClass.simpleName}")
        }
    }

    override fun getValue(context: Context): Value {
        val dict = arrayReference.getValue(context)
        if (dict is DictValue) {
            return dict.getElement(identifier)
                    ?: throw UnresolvedIdentifierException("Unresolved identifier: $dict has no $identifier")
        } else {
            throw InvalidVariableTypeException("Expected ArrayValue, got ${dict.javaClass.simpleName}")
        }
    }
}