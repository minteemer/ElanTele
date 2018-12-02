package ir.values.classes

import ir.values.Value
import ir.values.ValueClass

class EmptyValue() : Value(ValueClass.EMPTY){
    override fun toString(): String = "empty"
}
