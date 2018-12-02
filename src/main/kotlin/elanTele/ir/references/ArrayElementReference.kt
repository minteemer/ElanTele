package elanTele.ir.references

import elanTele.ir.Context
import elanTele.ir.exceptions.InvalidIndexException
import elanTele.ir.exceptions.InvalidTypeException
import elanTele.ir.expressions.Expression
import elanTele.ir.values.classes.ArrayValue
import elanTele.ir.values.classes.IntegerValue
import elanTele.ir.values.Value

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
            throw InvalidTypeException("Expected ArrayValue, got ${array.javaClass.simpleName}")
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
            throw InvalidTypeException("Expected ArrayValue, got ${array.javaClass.simpleName}")
    }
}