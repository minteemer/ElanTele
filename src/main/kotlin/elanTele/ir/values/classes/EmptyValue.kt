package elanTele.ir.values.classes

import elanTele.ir.values.Value
import elanTele.ir.values.ValueClass

class EmptyValue() : Value(ValueClass.EMPTY) {

    /**
     *  @return [String] representation of empty
     *
     */
    override fun toString(): String = "empty"

    /**
     *  @return true
     *  @throws [UnresolvedOperatorException] if other is not of type [EmptyValue]
     *
     */
    override fun equals(other: Any?): Boolean =
            when (other) {
                is EmptyValue -> true
                else -> super.equals(other)
            }
}
