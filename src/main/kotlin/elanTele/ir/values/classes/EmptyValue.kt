package elanTele.ir.values.classes

import elanTele.ir.values.Value
import elanTele.ir.values.ValueClass

class EmptyValue() : Value(ValueClass.EMPTY){
    override fun toString(): String = "empty"
}
