package ir.values

import ir.Context
import ir.expressions.Expression
import ir.statements.StatementsSequence

class FunctionValue(
        val body: StatementsSequence,
        val returnExpression: Expression
) : Value(ValueClass.REAL) {

    fun excecute(arguments: Map<String, Value>, context: Context): Value {
        // TODO: add local context
        arguments.forEach { name, value -> context.setValue(name, value) }
        body.executeAll(context)
        return returnExpression.execute(context)
    }

}
