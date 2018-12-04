package elanTele.ir.values.classes

import elanTele.ir.values.Value
import elanTele.ir.values.ValueClass

class TupleValue(values: List<Pair<String?, Value>>) : Value(ValueClass.DICT) {

    private val values = ArrayList(values)

    /** Gets element from the tuple by [index] */
    fun getElement(index: Int): Value? = values.getOrNull(index)?.second

    /** Gets element from the tuple by [identifier] */
    fun getElement(identifier: String): Value? = getElement(getIndex(identifier))

    /** Puts element to the tuple by [index] */
    fun setElement(index: Int, value: Value) {
        values[index] = (values[index].first to value)
    }

    /** Puts element to the tuple by [identifier] */
    fun setElement(identifier: String, value: Value) = setElement(getIndex(identifier), value)

    private fun getIndex(identifier: String) = values.indexOfFirst { it.first == identifier }

    /**
     * Implements concatenation of the tuples by adding them together
     *
     * @return concatenated tuple of the type [Value]
     */
    override fun add(other: Value): Value = when (other) {
        is TupleValue -> TupleValue(values + other.values)
        else -> super.add(other)
    }

    /**
     * Represents tuple as a string in an appropriate format (e.g. {c: 2, b: 1, 4} )
     *
     * @return created ouput format of the type [String]
     */
    override fun toString(): String = values
            .joinToString(prefix = "{", postfix = "}") { (name, value) ->
                name?.let { "$name: $value" } ?: value.toString()
            }

    /**
     * Implements comparison of the tuples
     *
     * @return [Boolean] result
     */
    override fun equals(other: Any?): Boolean =
            when (other) {
                is TupleValue -> other.values == values
                else -> super.equals(other)
            }
}
