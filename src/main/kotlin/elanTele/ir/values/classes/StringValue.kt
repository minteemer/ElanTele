package elanTele.ir.values.classes

import elanTele.ir.values.Value
import elanTele.ir.values.ValueClass

class StringValue(val value: String) : Value(ValueClass.STRING) {

    /**
     *  @param other string that will be concatenated with current string
     *  @return [StringValue] that is concat of current and other strings
     *  @throws [UnresolvedOperatorException] if other can not be concatenated as string
     */
    override fun add(other: Value): Value = when (other) {
        is StringValue,
        is IntegerValue,
        is RealValue,
        is BooleanValue,
        is ArrayValue,
        is TupleValue -> StringValue(value + other.toString())
        else -> super.add(other)
    }

    /**
     *  @return [String] representation of current string
     *
     */
    override fun toString(): String = value

    /**
     *  @return true if current string equal to other, false otherwise
     *  @throws [UnresolvedOperatorException] if other is not of type [StringValue]
     *
     */
    override fun equals(other: Any?): Boolean =
            when (other) {
                is StringValue -> other.value == value
                else -> super.equals(other)
            }
}
