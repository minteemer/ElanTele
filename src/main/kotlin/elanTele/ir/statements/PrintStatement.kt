package elanTele.ir.statements

import elanTele.ir.Context
import elanTele.ir.expressions.Expression
import elanTele.ir.values.Value

class PrintStatement(
        private val expressions: List<Expression>
) : Statement {

    constructor(expression: Expression) : this(listOf(expression))

    override fun execute(context: Context): Value? {
        expressions
                .map { it.execute(context) }
                .joinToString(", ")
                .let { println(it) }
        return null
    }
}