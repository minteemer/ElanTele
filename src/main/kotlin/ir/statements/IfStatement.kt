package ir.statements

import ir.expressions.Expression
import ir.Context
import ir.references.InvalidVariableTypeException
import ir.values.BooleanValue

class IfStatement(
        private val conditionExpression: Expression,
        private val ifBody: StatementsSequence,
        private val elseBody: StatementsSequence? = null
) : Statement {

    override fun execute(context: Context) {
        val condition = conditionExpression.execute(context)
        if (condition is BooleanValue){
            val newContext = context.getChildContext()
            if (condition.value)
                ifBody.executeAll(newContext)
            else
                elseBody?.executeAll(newContext)
        } else
            throw InvalidVariableTypeException("Expected boolean for \"if\", got ${condition.javaClass.simpleName} ")
    }
}