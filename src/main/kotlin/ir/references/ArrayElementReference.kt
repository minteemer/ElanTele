package ir.references

import ir.Context
import ir.expressions.Expression
import ir.values.classes.ArrayValue
import ir.values.classes.IntegerValue
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
                throw InvalidIndexException("Expected IntegerValue index, got ${index.javaClass.simpleName}")
        } else
            throw InvalidVariableTypeException("Expected ArrayValue, got ${array.javaClass.simpleName}")
    }

    override fun getValue(context: Context): Value {
        val array = arrayReference.getValue(context)
        if (array is ArrayValue) {
            val index = indexExpression.execute(context)
            if (index is IntegerValue) {
                return array.getElement(index.value)
            } else
                throw InvalidIndexException("Expected IntegerValue index, got ${index.javaClass.simpleName}")
        } else
            throw InvalidVariableTypeException("Expected ArrayValue, got ${array.javaClass.simpleName}")
    }
}