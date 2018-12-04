package elanTele.ir.values.classes

import elanTele.ir.Context
import elanTele.ir.statements.Statement
import elanTele.ir.values.Value
import elanTele.ir.values.ValueClass

class FunctionValue(
        val parameters: List<String>,
        val body: Statement
) : Value(ValueClass.FUNCTION) {

    /**
     *  @param arguments is arguments for the function
     *  @param context of program depending on it expressions are executed
     *  @return [Value] that is result of the function given [arguments]
     *  @throws [UnresolvedOperatorException] if other is not of type [BooleanValue]
     *
     */
    fun call(arguments: List<Value>, context: Context): Value =
            arguments.mapIndexed { index, value -> parameters[index] to value }
                    .associate { it }
                    .let { argsMap ->
                        body.execute(context.getChildContext(argsMap)) ?: EmptyValue()
                    }

    /**
     *  @return [String] representation of current function
     *
     */
    override fun toString(): String = "func(${parameters.joinToString()})"

}
