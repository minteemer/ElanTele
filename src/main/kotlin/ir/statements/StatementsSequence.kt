package ir.statements

import ir.values.Context

class StatementsSequence(private val statements: List<Statement>) {

    fun executeAll(context: Context) {
        statements.forEach { it.execute(context) }
    }

}