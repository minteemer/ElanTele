package elanTele.ir.values.classes

import elanTele.ir.values.Value
import elanTele.ir.values.ValueClass

class BooleanValue(val value: Boolean) : Value(ValueClass.BOOLEAN) {

    /**
     *  @param other that will be used for 'and' operation
     *  @return [BooleanValue] that is result of applying 'and' operation on [current] and [other] boolean values
     *  @throws [UnresolvedOperatorException] if other is not of type [BooleanValue]
     *
     */
    override fun and(other: Value): BooleanValue = when (other) {
        is BooleanValue -> BooleanValue(value and other.value)
        else -> super.and(other)
    }

    /**
     *  @param other that will be used for 'or' operation
     *  @return [BooleanValue] that is result of applying 'or' operation on [current] and [other] boolean values
     *  @throws [UnresolvedOperatorException] if other is not of type [BooleanValue]
     *
     */
    override fun or(other: Value): BooleanValue = when (other) {
        is BooleanValue -> BooleanValue(value or other.value)
        else -> super.or(other)
    }

    /**
     *  @param other that will be used for 'xor' operation
     *  @return [BooleanValue] that is result of applying 'xor' operation on [current] and [other] boolean values
     *  @throws [UnresolvedOperatorException] if other is not of type [BooleanValue]
     *
     */
    override fun xor(other: Value): BooleanValue = when (other) {
        is BooleanValue -> BooleanValue(value xor other.value)
        else -> super.xor(other)
    }

    /**
     *  @return [BooleanValue] that is result of applying 'not' operation on [current] boolean value
     *  @throws [UnresolvedOperatorException] if current is not of type [BooleanValue]
     *
     */
    override fun not(): BooleanValue = BooleanValue(!value)

    /**
     *  @return [String] representation of current boolean
     *
     */
    override fun toString(): String = value.toString()

    /**
     *  @return true if current boolean equal to other, false otherwise
     *  @throws [UnresolvedOperatorException] if other is not of type [BooleanValue]
     *
     */
    override fun equals(other: Any?): Boolean =
            when (other) {
                is BooleanValue -> other.value == value
                else -> super.equals(other)
            }

}

