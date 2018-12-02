package ir.values.classes

import ir.values.Value
import ir.values.ValueClass

class BooleanValue(val value: Boolean) : Value(ValueClass.BOOLEAN){
    override fun and(other: Value): BooleanValue = when(other){
        is BooleanValue -> BooleanValue(value and other.value)
        else -> super.and(other)
    }

    override fun or(other: Value): BooleanValue = when(other){
        is BooleanValue -> BooleanValue(value or other.value)
        else -> super.or(other)
    }
    override fun xor(other: Value): BooleanValue = when(other){
        is BooleanValue -> BooleanValue(value xor other.value)
        else -> super.xor(other)
    }

    override fun toString(): String = value.toString()
}
