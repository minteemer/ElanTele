package ir.values

class IntegerValue(val value: Int): Value() {

    override fun add(other: Value): Value = when(other){
        is IntegerValue -> IntegerValue(value + other.value)
        is RealValue -> RealValue(value + other.value)
        else -> super.add(other)
    }


}