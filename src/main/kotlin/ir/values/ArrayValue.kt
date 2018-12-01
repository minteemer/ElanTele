package ir.values

class ArrayValue(val values: List<Value>) : Value(ValueClass.ARRAY){

    override fun add(other: Value): Value = when (other) {
        is ArrayValue -> ArrayValue(values + other.values)
        else -> super.add(other)
    }

}
