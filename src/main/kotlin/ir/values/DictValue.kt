package ir.values

import sun.security.util.ObjectIdentifier

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
