package elanTele.ir.values.classes

import elanTele.ir.values.Value
import elanTele.ir.values.ValueClass

class StringValue(val value: String) : Value(ValueClass.STRING) {

    override fun add(other: Value): Value = when (other) {
        is StringValue -> StringValue(value + other.value)
        else -> super.add(other)
    }

    override fun toString(): String = value

    override fun equals(other: Any?): Boolean =
            when (other) {
                is StringValue -> other.value == value
                else -> super.equals(other)
            }
}
