package elanTele.ir

import com.google.gson.GsonBuilder
import elanTele.ir.exceptions.ContextException
import elanTele.ir.values.Value

class Context(private val parentContext: Context? = null) {

    private val values: MutableMap<String, Value> = HashMap()

    /**
     * Tries to get [Value] of variable with name [name], if the name is not
     * found and [parentContext] is not null tries to get value from the parent
     * context.
     * @return [Value] of variable with given [name] from closest context in context
     * stack
     * @throws ContextException if name is not found
     */
    fun getValue(name: String): Value =
            values[name]
                    ?: parentContext?.getValue(name)
                    ?: throw ContextException("Name $name not found in the context")

    /**
     * Sets value of variable in closest context in context stack that contains
     * a variable with given [name] to given [Value].
     * @throws ContextException if [name] is not found in the context stack
     */
    fun setValue(name: String, value: Value) {
        if (values.containsKey(name))
            values[name] = value
        else
            parentContext?.setValue(name, value)
                    ?: throw ContextException("Name $name not found in the context")
    }

    /**
     * Creates new variable in this stack with given [name] and [value].
     */
    fun createLocalVariable(name: String, value: Value) {
        values[name] = value
    }

    /**
     * Creates new context that has this context as parent and initialises
     * it with given [arguments].
     */
    fun getChildContext(arguments: Map<String, Value>? = null) =
            Context(this).apply {
                arguments?.forEach { name, value -> createLocalVariable(name, value) }
            }

    override fun toString(): String = GsonBuilder().setPrettyPrinting().create().toJson(values)
}