package elanTele.ir.references

import elanTele.ir.Context
import elanTele.ir.exceptions.InvalidTypeException
import elanTele.ir.exceptions.UnresolvedIdentifierException
import elanTele.ir.values.Value
import elanTele.ir.values.classes.TupleValue

data class TupleElementIndexReference(
        val arrayReference: Reference,
        val index: Int
) : Reference {

    /**
     * Receives tuple from the [context] and puts [value] with the [index] position to it.
     *
     * @throws InvalidTypeException, if received from the context object is not a tuple
     */
    override fun setValue(context: Context, value: Value) {
        val dict = arrayReference.getValue(context)
        if (dict is TupleValue) {
            dict.setElement(index - 1, value)
        } else {
            throw InvalidTypeException("Expected TupleValue, got ${dict.javaClass.simpleName}")
        }
    }

    /**
     * Receives tuple from the [context] and gets a certain value from it based on [index].
     *
     * @throws InvalidTypeException, if received from the context object is not a tuple
     * @throws UnresolvedIdentifierException, if tuple has no such [index]
     * @return object of the type [Value] taken from the tuple
     */
    override fun getValue(context: Context): Value {
        val dict = arrayReference.getValue(context)
        if (dict is TupleValue) {
            return dict.getElement(index - 1)
                    ?: throw UnresolvedIdentifierException("Unresolved index: $dict has no index $index")
        } else {
            throw InvalidTypeException("Expected TupleValue, got ${dict.javaClass.simpleName}")
        }
    }
}