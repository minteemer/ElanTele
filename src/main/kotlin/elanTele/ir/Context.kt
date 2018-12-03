package elanTele.ir

import com.google.gson.GsonBuilder
import elanTele.ir.exceptions.ContextException
import elanTele.ir.values.Value

class Context(private val parentContext: Context? = null) {

    private val values: MutableMap<String, Value> = HashMap()

    fun getValue(reference: String): Value =
            values[reference]
                    ?: parentContext?.getValue(reference)
                    ?: throw ContextException("Name $reference not found in the context")

    fun setValue(reference: String, value: Value) {
        if (values.containsKey(reference))
            values[reference] = value
        else
            parentContext?.setValue(reference, value)
                    ?: throw ContextException("Name $reference not found in the context")
    }

    fun createLocalReference(reference: String, value: Value) {
        values[reference] = value
    }

    fun getChildContext(arguments: Map<String, Value>? = null) =
            Context(this).apply {
                arguments?.forEach { name, value -> createLocalReference(name, value) }
            }

    override fun toString(): String = GsonBuilder().setPrettyPrinting().create().toJson(values)
}