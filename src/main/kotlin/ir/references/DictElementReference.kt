package ir.references

import ir.Context
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
        } else
            throw Exception() // TODO: handle non-array type
    }

    override fun getValue(context: Context): Value {
        val dict = arrayReference.getValue(context)
        if (dict is DictValue) {
            return dict.getElement(identifier)
                    ?: throw Exception() // TODO: handle unresolved identifier
        } else
            throw Exception() // TODO: handle non-array type
    }
}