package ir.statements

import ir.expressions.Expression
import ir.values.Context

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