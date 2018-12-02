package ir.references

import ir.Context
import ir.expressions.Expression
import ir.values.ArrayValue
import ir.values.IntegerValue
import ir.values.Value

data class ArrayElementReference(
        val arrayReference: Reference,
        val indexExpression: Expression
) : Reference {

    override fun setValue(context: Context, value: Value) {
        val array = arrayReference.getValue(context)
        if (array is ArrayValue) {
            val index = indexExpression.execute(context)
            if (index is IntegerValue) {
                array.setElement(index.value, value)
            } else
                throw Exception() // TODO: handle non-integer index
        } else
            throw Exception() // TODO: handle non-array type
    }

    override fun getValue(context: Context): Value {
        val array = arrayReference.getValue(context)
        if (array is ArrayValue) {
            val index = indexExpression.execute(context)
            if (index is IntegerValue) {
                return array.getElement(index.value)
            } else
                throw Exception() // TODO: handle non-integer index
        } else
            throw Exception() // TODO: handle non-array type
    }
}