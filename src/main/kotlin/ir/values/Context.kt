package ir.values

import java.lang.Exception

class Context {
    private val values = HashMap<String, Value>()

    fun getValue(reference: String): Value = values[reference]
            ?: throw Exception() // TODO: Handle unresolved reference

    fun setValue(reference: String, value: Value) {
        values[reference] = value
    }
}