package ir

import ir.values.Value

class Context(private val parentContext: Context? = null,
              private val values: MutableMap<String, Value> = HashMap()) {

    fun getValue(reference: String): Value =
            values[reference]
                    ?: parentContext?.getValue(reference)
                    ?: throw ContextException("Name $reference not found in context")

    fun setValue(reference: String, value: Value) {
        if (!propagateValue(reference, value)) return
        values[reference] = value
    }

    private fun propagateValue(reference: String, value: Value): Boolean =
            if (values.containsKey(reference)) {
                values[reference] = value
                true
            } else {
                parentContext?.propagateValue(reference, value) ?: false
            }



    fun getChildContext(arguments: Map<String, Value>) = Context(this, HashMap(arguments))
}