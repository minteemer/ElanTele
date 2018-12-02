package ir.values

class ArrayValue(values: Map<Int, Value>) : Value(ValueClass.ARRAY) {

    constructor(values: List<Value>) : this(
            values
                    .mapIndexed { index, value -> index to value }
                    .associate { it }
    )

    private val values = HashMap(values)

    fun getElement(index: Int): Value = values[index] ?: EmptyValue()

    fun setElement(index: Int, value: Value) {
        values[index] = value
    }

    override fun add(other: Value): Value = when (other) {
        is ArrayValue -> ArrayValue(values + other.values) // TODO: concatenate sparse arrays
        else -> super.add(other)
    }

    override fun toString(): String = values.entries
            .joinToString(prefix = "[", postfix = "]") { (index, value) -> "$index: $value" }

}
