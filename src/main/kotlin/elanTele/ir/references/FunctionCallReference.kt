package elanTele.ir.references

import elanTele.ir.Context
import elanTele.ir.exceptions.AssignmentException
import elanTele.ir.exceptions.InvalidTypeException
import elanTele.ir.expressions.Expression
import elanTele.ir.values.Value
import elanTele.ir.values.classes.FunctionValue

data class FunctionCallReference(
        val identifier: Reference,
        val arguments: List<Expression>
) : Reference {

    /**
     * @throws AssignmentException, if the function call is assigned a value.
     */
    override fun setValue(context: Context, value: Value) =
            throw AssignmentException("Can't assign a value to a function call")

    /**
     * Receives function represented as identifier from the [context] and executes it.
     *
     * @throws InvalidTypeException, if received from the context object is not a function.
     * @return Executed function result of the type [Value]
     */
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