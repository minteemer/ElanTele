package elanTele.ir.statements

import elanTele.ir.Context
import elanTele.ir.values.Value

class StatementsSequence(private val statements: List<Statement>) : Statement {

    override fun execute(context: Context): Value? {
        statements.forEach { it.execute(context)?.let { returnedValue -> return returnedValue } }
        return null
    }

}