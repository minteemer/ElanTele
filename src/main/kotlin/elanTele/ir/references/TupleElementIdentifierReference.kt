package elanTele.ir.references

import elanTele.ir.Context
import elanTele.ir.exceptions.InvalidTypeException
import elanTele.ir.exceptions.UnresolvedIdentifierException
import elanTele.ir.values.Value
import elanTele.ir.values.classes.TupleValue

data class TupleElementIdentifierReference(
        val arrayReference: Reference,
        val identifier: String
) : Reference {

    /**
     * Receives tuple from the [context] and puts [value] with the [identifier] key to it.
     *
     * @throws InvalidTypeException, if received from the context object is not a tuple
     */
    override fun setValue(context: Context, value: Value) {
        val dict = arrayReference.getValue(context)
        if (dict is TupleValue) {
            dict.setElement(identifier, value)
        } else {
            throw InvalidTypeException("Expected TupleValue, got ${dict.javaClass.simpleName}")
        }
    }

    /**
     * Receives tuple from the [context] and gets a certain value from it based on [identifier].
     *
     * @throws InvalidTypeException, if received from the context object is not a tuple
     * @throws UnresolvedIdentifierException, if tuple has no such [identifier]
     * @return object of the type [Value] taken from the tuple
     */
    override fun getValue(context: Context): Value {
        val dict = arrayReference.getValue(context)
        if (dict is TupleValue) {
            return dict.getElement(identifier)
                    ?: throw UnresolvedIdentifierException("Unresolved identifier: $dict has no $identifier")
        } else {
            throw InvalidTypeException("Expected TupleValue, got ${dict.javaClass.simpleName}")
        }
    }
}