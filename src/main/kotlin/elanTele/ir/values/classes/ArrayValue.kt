package elanTele.ir.values.classes

import elanTele.ir.exceptions.InvalidIndexException
import elanTele.ir.values.Value
import elanTele.ir.values.ValueClass

class ArrayValue(values: Map<Int, Value>) : Value(ValueClass.ARRAY) {

    private val values = HashMap(values)

    /**
     *  @param index of element of array
     *  @return [Value] that is element of array by index [index]
     *
     */
    fun getElement(index: Int): Value =
            if (index in 1..Int.MAX_VALUE)
                values[index] ?: EmptyValue()
            else
                throw InvalidIndexException("Array indexes must be positive non-zero integers")


    /**
     *  @param index of element of array
     *  @param value that will be written in [index] element of array
     *
     */
    fun setElement(index: Int, value: Value) {
        if (index in 1..Int.MAX_VALUE)
            values[index] = value
        else
            throw InvalidIndexException("Array indexes must be positive non-zero integers")
    }

    /**
     *  @param other array that will be concatenated with current array
     *  @return [ArrayValue] that is concat of current and other arrays
     *  @throws [UnresolvedOperatorException] if other is not of type [ArrayValue]
     *
     */
    override fun add(other: Value): Value = when (other) {
        is ArrayValue -> {
            val size = values.keys.max() ?: 0
            val newSparseArray = HashMap(values).apply {
                other.values.forEach { (index, value) -> put(size + index, value) }
            }
            ArrayValue(newSparseArray)
        }
        else -> super.add(other)
    }

    /**
     *  @return [String] representation of current array
     *
     */
    override fun toString(): String = values.entries
            .joinToString(prefix = "[", postfix = "]") { (index, value) -> "$index: $value" }

    /**
     *  @return true if current array equal to other, false otherwise
     *  @throws [UnresolvedOperatorException] if other is not of type [ArrayValue]
     *
     */
    override fun equals(other: Any?): Boolean =
            when (other) {
                is ArrayValue -> other.values == values
                else -> super.equals(other)
            }
}
