package ir.statements

import ir.expressions.Expression
import ir.Context
import ir.values.classes.BooleanValue

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
            throw Exception("Expected boolean value for \"if\" condition") // TODO: throw better exception
    }
}