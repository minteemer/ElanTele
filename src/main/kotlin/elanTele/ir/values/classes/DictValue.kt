package elanTele.ir.values.classes

import elanTele.ir.values.Value
import elanTele.ir.values.ValueClass

class DictValue(values: Map<String, Value>) : Value(ValueClass.DICT) {

    private val values = HashMap(values)

    fun getElement(identifier: String): Value? = values[identifier]

    fun setElement(identifier: String, value: Value) {
        values[identifier] = value
    }

    override fun add(other: Value): Value = when (other) {
        is DictValue -> DictValue(values + other.values)
        else -> super.add(other)
    }

    override fun toString(): String = values.entries
            .joinToString(prefix = "{", postfix = "}") { (name, value) -> "$name: $value" }
}
