package elanTele.ir.values.classes

import elanTele.ir.exceptions.UnresolvedIdentifierException
import elanTele.ir.values.Value
import elanTele.ir.values.ValueClass
import kotlin.collections.ArrayList

class TupleValue(values: List<Pair<String?, Value>>) : Value(ValueClass.DICT) {

    private val values = ArrayList(values)

    fun getElement(index: Int): Value = values.getOrNull(index)?.second
            ?: throw IndexOutOfBoundsException("Tuple $this does not have element with index $index")

    fun getElement(identifier: String): Value? = getElement(getIndex(identifier))


    fun setElement(index: Int, value: Value) {
        if (index in 0 until values.size)
            values[index] = (values[index].first to value)
        else
            throw IndexOutOfBoundsException("Tuple $this does not have element with index $index")
    }

    fun setElement(identifier: String, value: Value) = setElement(getIndex(identifier), value)


    private fun getIndex(identifier: String) = values.indexOfFirst { it.first == identifier }.takeIf { it >= 0 }
            ?: throw UnresolvedIdentifierException("Tuple $this does not have element with identifier $identifier")


    override fun add(other: Value): Value = when (other) {
        is TupleValue -> TupleValue(values + other.values)
        else -> super.add(other)
    }

    override fun toString(): String = values
            .joinToString(prefix = "{", postfix = "}") { (name, value) ->
                name?.let { "$name: $value" } ?: value.toString()
            }

    override fun equals(other: Any?): Boolean =
            when (other) {
                is TupleValue -> other.values == values
                else -> super.equals(other)
            }
}
