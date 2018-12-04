package elanTele.ir.references

import elanTele.ir.Context
import elanTele.ir.exceptions.InvalidIndexException
import elanTele.ir.exceptions.InvalidTypeException
import elanTele.ir.expressions.Expression
import elanTele.ir.values.Value
import elanTele.ir.values.classes.ArrayValue
import elanTele.ir.values.classes.IntegerValue

data class ArrayElementReference(
        val arrayReference: Reference,
        val indexExpression: Expression
) : Reference {

    /**
     * Receives array from the [context] and puts [value] to it.
     *
     * @throws InvalidTypeException, if received from the context object is not an array
     * @throws InvalidIndexException, if index is not an Integer value
     */
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

    /**
     * Receives array from the [context] and gets a certain value from it.
     *
     * @throws InvalidTypeException, if received from the context object is not an array
     * @throws InvalidIndexException, if index is not an Integer value
     * @return object of the type [Value] taken from the array
     */
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