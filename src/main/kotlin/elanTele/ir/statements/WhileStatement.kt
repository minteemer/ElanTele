package elanTele.ir.statements

import elanTele.ir.Context
import elanTele.ir.exceptions.InvalidTypeException
import elanTele.ir.expressions.Expression
import elanTele.ir.values.Value
import elanTele.ir.values.classes.BooleanValue


class WhileStatement(
        private val conditionExpression: Expression,
        private val whileBody: StatementsSequence
) : Statement {

    /**
     * Executes [whileBody] while value returned by execution of [conditionExpression] is true,
     * If on one of the iterations [whileBody] returns a non-null value, then the execution stops and
     * the value immediately returned.
     *
     * @return result of execution of [whileBody] if it returns non-null value, null otherwise
     * @throws InvalidTypeException if value returned by [conditionExpression] is not [BooleanValue]
     */
    override fun execute(context: Context): Value? {
        while (true) {
            val condition = conditionExpression.execute(context)
            if (condition is BooleanValue) {
                if (condition.value)
                    whileBody.execute(context.getChildContext())?.let { return it }
                else
                    break
            } else
                throw InvalidTypeException("Expected boolean value for \"while\" loop, $condition received.")
        }
        return null
    }
}