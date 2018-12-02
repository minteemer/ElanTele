package ir.references

import ir.Context
import ir.exceptions.InvalidTypeException
import ir.exceptions.UnresolvedIdentifierException
import ir.values.classes.DictValue
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
            throw InvalidTypeException("Expected ArrayValue, got ${dict.javaClass.simpleName}")
        }
    }

    override fun getValue(context: Context): Value {
        val dict = arrayReference.getValue(context)
        if (dict is DictValue) {
            return dict.getElement(identifier)
                    ?: throw UnresolvedIdentifierException("Unresolved identifier: $dict has no $identifier")
        } else {
            throw InvalidTypeException("Expected ArrayValue, got ${dict.javaClass.simpleName}")
        }
    }
}