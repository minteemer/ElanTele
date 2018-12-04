package elanTele.ir.references

import elanTele.ir.Context
import elanTele.ir.exceptions.InvalidTypeException
import elanTele.ir.expressions.Expression
import elanTele.ir.values.Value
import elanTele.ir.values.classes.FunctionValue

data class FunctionCallReference(
        val identifier: Reference,
        val arguments: List<Expression>
) : Reference {
    override fun setValue(context: Context, value: Value) =
            throw IllegalAccessException()

    override fun getValue(context: Context): Value {
        val function = identifier.getValue(context)
        if (function is FunctionValue) {
            return function.call(
                    arguments.map { it.execute(context) },
                    context
            )
        } else
            throw InvalidTypeException("Expected FunctionValue, got ${function.javaClass.simpleName}")
    }
}