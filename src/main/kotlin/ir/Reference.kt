package ir

import ir.values.Value

interface Reference {
    fun getValue(context: Context): Value
    fun setValue(context: Context, value: Value)
}