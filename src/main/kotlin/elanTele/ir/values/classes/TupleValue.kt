package elanTele.ir.values.classes

import elanTele.ir.values.Value
import elanTele.ir.values.ValueClass
import java.util.*
import kotlin.collections.ArrayList

class TupleValue(values: List<Pair<String?, Value>>) : Value(ValueClass.DICT) {

    private val values = ArrayList(values)

    fun getElement(index: Int): Value? = values.getOrNull(index)?.second

    fun getElement(identifier: String): Value? = getElement(getIndex(identifier))

    fun setElement(index: Int, value: Value) {
        values[index] = (values[index].first to value)
    }

    fun setElement(identifier: String, value: Value) = setElement(getIndex(identifier), value)

    private fun getIndex(identifier: String) = values.indexOfFirst { it.first == identifier }

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
