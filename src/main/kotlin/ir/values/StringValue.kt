package ir.values

class StringValue(val value: String) : Value(ValueClass.STRING) {

    override fun add(other: Value): Value = when (other) {
        is StringValue -> StringValue(value + other.value)
        else -> super.add(other)
    }

    override fun toString(): String = value
}
