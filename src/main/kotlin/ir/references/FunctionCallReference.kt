package ir.references

import ir.Context
import ir.expressions.Expression
import ir.values.FunctionValue
import ir.values.Value

class FunctionCallReference(
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
            throw Exception() // TODO: handle call on non-function type
    }
}