package elanTele.ir.references

import elanTele.ir.Context
import elanTele.ir.exceptions.InvalidTypeException
import elanTele.ir.expressions.Expression
import elanTele.ir.values.classes.FunctionValue
import elanTele.ir.values.Value

data class FunctionCallReference(
        val identifier: String,
        val arguments: Map<String, Expression>
) : Reference {
    override fun setValue(context: Context, value: Value) =
            throw Exception() // TODO: handle set value to function call

    override fun getValue(context: Context): Value {
        val function = context.getValue(identifier)
        if (function is FunctionValue) {
            return function.call(
                    arguments.mapValues { (_, expression) -> expression.execute(context) },
                    context
            )
        } else
            throw InvalidTypeException("Expected FunctionValue, got ${function.javaClass.simpleName}")
    }
}