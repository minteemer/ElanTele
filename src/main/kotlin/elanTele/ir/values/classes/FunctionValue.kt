package elanTele.ir.values.classes

import elanTele.ir.Context
import elanTele.ir.expressions.Expression
import elanTele.ir.statements.Statement
import elanTele.ir.statements.StatementsSequence
import elanTele.ir.values.Value
import elanTele.ir.values.ValueClass

class FunctionValue(
        val parameters: List<String>,
        val body: Statement
) : Value(ValueClass.FUNCTION) {

    fun call(arguments: List<Value>, context: Context): Value =
            arguments.mapIndexed { index, value -> parameters[index] to value }
                    .associate { it }
                    .let { argsMap ->
                        body.execute(context.getChildContext(argsMap)) ?: EmptyValue()
                    }

    override fun toString(): String = "func(${parameters.joinToString()})"

}
