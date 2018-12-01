package ir.values

class DictValue(val values: Map<String, Value>) : Value(ValueClass.DICT) {

    override fun add(other: Value): Value = when (other) {
        is DictValue -> DictValue(values + other.values)
        else -> super.add(other)
    }

}
