package elanTele.ir.statements

import elanTele.ir.Context

class StatementsSequence(private val statements: List<Statement>): Statement {

    override fun execute(context: Context) {
        statements.forEach { it.execute(context) }
    }

}