package elanTele.ir.values.classes

import elanTele.ir.values.Value
import elanTele.ir.values.ValueClass

class BooleanValue(val value: Boolean) : Value(ValueClass.BOOLEAN){
    override fun and(other: Value): BooleanValue = when(other){
        is BooleanValue -> BooleanValue(value && other.value)
        else -> super.and(other)
    }

    override fun or(other: Value): BooleanValue = when(other){
        is BooleanValue -> BooleanValue(value || other.value)
        else -> super.or(other)
    }
    override fun xor(other: Value): BooleanValue = when(other){
        is BooleanValue -> BooleanValue(value.xor( other.value))
        else -> super.xor(other)
    }

    override fun not(): BooleanValue =BooleanValue(!value)
    override fun toString(): String = value.toString()
}

