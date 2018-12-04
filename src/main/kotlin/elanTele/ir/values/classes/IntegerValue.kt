package elanTele.ir.values.classes

import elanTele.ir.values.Value
import elanTele.ir.values.ValueClass

/**
 * This class declares [IntegerValue] type and operations with it.
 *
 * Operations implemented on the [IntegerValue] type:
 * [add] -
 *      Int + Int = Real
 *      Int + Real = Real
 * [subtract] -
 *      Int - Int = Real
 *      Int + Real = Real
 * [multiply] -
 *      Int * Int = Real
 *      Int * Real = Real
 * [divide] -
 *      Int / Int = Real
 *      Int / Real = Real
 * [equals] -
 *      Int == Int = Boolean
 *      Int == Real = Boolean
 * [notEquals] -
 *      Int != Int = Boolean
 *      Int != Real = Boolean
 * [greater] -
 *      Int > Int = Boolean
 *      Int > Real = Boolean
 * [greaterOrEqual] -
 *      Int >= Int = Boolean
 *      Int >= Real = Boolean
 * [less] -
 *      Int < Int = Boolean
 *      Int < Real = Boolean
 * [lessOrEqual] -
 *      Int <= Int = Boolean
 *      Int <= Real = Boolean
 * [unaryMinus] -
 *      - Int = Int
 */
class IntegerValue(val value: Int) : Value(ValueClass.INTEGER) {

    override fun add(other: Value): Value = when (other) {
        is IntegerValue -> IntegerValue(value + other.value)
        is RealValue -> RealValue(value + other.value)
        else -> super.add(other)
    }

    override fun subtract(other: Value): Value = when (other) {
        is IntegerValue -> IntegerValue(value - other.value)
        is RealValue -> RealValue(value - other.value)
        else -> super.subtract(other)
    }

    override fun multiply(other: Value): Value = when (other) {
        is IntegerValue -> IntegerValue(value * other.value)
        is RealValue -> RealValue(value * other.value)
        else -> super.multiply(other)
    }

    override fun divide(other: Value): Value = when (other) {
        is IntegerValue -> IntegerValue(value / other.value)
        is RealValue -> RealValue(value.toDouble() / other.value)
        else -> super.divide(other)
    }

    override fun equals(other: Value): BooleanValue = when (other) {
        is IntegerValue -> BooleanValue(value == other.value)
        is RealValue -> BooleanValue(value.toDouble() == other.value)
        else -> super.equals(other)
    }

    override fun notEquals(other: Value): BooleanValue = when (other) {
        is IntegerValue -> BooleanValue(value != other.value)
        is RealValue -> BooleanValue(value.toDouble() != other.value)
        else -> super.notEquals(other)
    }

    override fun greater(other: Value): BooleanValue = when (other) {
        is IntegerValue -> BooleanValue(value > other.value)
        is RealValue -> BooleanValue(value > other.value)
        else -> super.greater(other)
    }

    override fun greaterOrEqual(other: Value): BooleanValue = when (other) {
        is IntegerValue -> BooleanValue(value >= other.value)
        is RealValue -> BooleanValue(value >= other.value)
        else -> super.greaterOrEqual(other)
    }

    override fun less(other: Value): BooleanValue = when (other) {
        is IntegerValue -> BooleanValue(value < other.value)
        is RealValue -> BooleanValue(value < other.value)
        else -> super.less(other)
    }

    override fun lessOrEqual(other: Value): BooleanValue = when (other) {
        is IntegerValue -> BooleanValue(value <= other.value)
        is RealValue -> BooleanValue(value <= other.value)
        else -> super.lessOrEqual(other)
    }

    override fun equals(other: Any?): Boolean =
            when (other) {
                is IntegerValue -> other.value == value
                else -> super.equals(other)
            }

    override fun unaryMinus(): Value = IntegerValue(-value)

    override fun toString(): String = value.toString()
}