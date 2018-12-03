package elanTele.ir.statements

import elanTele.ir.Context
import elanTele.ir.expressions.Expression

class PrintStatement(
        private val expressions: List<Expression>
) : Statement {

    constructor(expression: Expression): this(listOf(expression))

    override fun execute(context: Context) {
        expressions
                .map { it.execute(context) }
                .joinToString(", ")
                .let { println(it) }
    }
}