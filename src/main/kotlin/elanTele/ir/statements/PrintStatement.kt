package elanTele.ir.statements

import elanTele.ir.Context
import elanTele.ir.expressions.Expression
import elanTele.ir.values.Value

class PrintStatement(
        private val expressions: List<Expression>
) : Statement {

    /**
     * Executes all the [expressions] and prints the results in one line joined by ", "
     * @return null
     */
    override fun execute(context: Context): Value? {
        expressions
                .map { it.execute(context) }
                .joinToString(", ")
                .let { println(it) }
        return null
    }
}