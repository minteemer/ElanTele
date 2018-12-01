package ir.values

class RealValue(val value: Double) : Value(){
    override fun add(other: Value): Value = when (other) {
        is IntegerValue -> RealValue(value + other.value)
        is RealValue -> RealValue(value + other.value)
        else -> super.add(other)
    }

    override fun subtract(other: Value): Value = when (other) {
        is IntegerValue -> RealValue(value - other.value)
        is RealValue -> RealValue(value - other.value)
        else -> super.subtract(other)
    }

    override fun multiply(other: Value): Value = when (other) {
        is IntegerValue -> RealValue(value * other.value)
        is RealValue -> RealValue(value * other.value)
        else -> super.multiply(other)
    }

    override fun divide(other: Value): Value = when (other) {
        is IntegerValue -> RealValue(value / other.value)
        is RealValue -> RealValue(value / other.value)
        else -> super.divide(other)
    }

    override fun greater(other: Value): Value = when (other) {
        is IntegerValue -> BooleanValue(value > other.value)
        is RealValue -> BooleanValue(value > other.value)
        else -> super.greater(other)
    }

    override fun equals(other: Value): Value = when (other) {
        is IntegerValue -> BooleanValue(value == other.value.toDouble())
        is RealValue -> BooleanValue(value == other.value)
        else -> super.equals(other)
    }

    override fun notEquals(other: Value): Value= when (other) {
        is IntegerValue -> BooleanValue(value != other.value.toDouble())
        is RealValue -> BooleanValue(value != other.value)
        else -> super.notEquals(other)
    }

    override fun greaterOrEqual(other: Value): Value = when (other) {
        is IntegerValue -> BooleanValue(value >= other.value)
        is RealValue -> BooleanValue(value >= other.value)
        else -> super.greaterOrEqual(other)
    }

    override fun less(other: Value): Value = when (other) {
        is IntegerValue -> BooleanValue(value < other.value)
        is RealValue -> BooleanValue(value < other.value)
        else -> super.less(other)
    }

    override fun lessOrEqual(other: Value): Value = when (other) {
        is IntegerValue -> BooleanValue(value <= other.value)
        is RealValue -> BooleanValue(value <= other.value)
        else -> super.lessOrEqual(other)
    }

    override fun unaryMinus(): Value = RealValue(-value)

    override fun toString(): String = value.toString()
}
