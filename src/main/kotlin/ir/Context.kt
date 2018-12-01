package ir

import ir.values.Value

class Context(private val parentContext: Context? = null) {
    private val values = HashMap<String, Value>()

    fun getValue(reference: String): Value =
            values[reference]
                    ?: parentContext?.getValue(reference)
                    ?: throw ContextException("Name $reference not found in context")

    fun setValue(reference: String, value: Value) {
        values[reference] = value
    }

    fun getChildContext() = Context(this)
}