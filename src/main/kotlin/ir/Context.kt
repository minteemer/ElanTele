package ir

import ir.values.Value

class Context(private val parentContext: Context? = null) {
    private val values = HashMap<String, Value>()

    fun getValue(reference: String): Value =
            values[reference]
                    ?: parentContext?.getValue(reference)
                    ?: throw ContextException("Name $reference not found in context")

    fun setValue(reference: String, value: Value) {
        if (!propagateValue(reference, value)) return
        values[reference] = value
    }

    private fun propagateValue(reference: String, value: Value): Boolean {
        if (values.containsKey(reference)) {
            values[reference] = value
            return true
        } else {
            return parentContext?.propagateValue(reference, value) ?: return false
        }
    }

    fun getChildContext() = Context(this)
}