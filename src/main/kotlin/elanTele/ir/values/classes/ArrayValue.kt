package elanTele.ir.values.classes

import elanTele.ir.values.Value
import elanTele.ir.values.ValueClass
import java.lang.IndexOutOfBoundsException

class ArrayValue(values: Map<Int, Value>) : Value(ValueClass.ARRAY) {

    private val values = HashMap(values)

    fun getElement(index: Int): Value = values[index] ?: EmptyValue()

    fun setElement(index: Int, value: Value) {
        if (index !in 1..Int.MAX_VALUE)
            throw IndexOutOfBoundsException("Array indexes must be positive non-zero integers")
        values[index] = value
    }

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

    override fun toString(): String = values.entries
            .joinToString(prefix = "[", postfix = "]") { (index, value) -> "$index: $value" }

}
