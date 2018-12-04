package elanTele.ir.statements

import elanTele.ir.Context
import elanTele.ir.values.Value

class StatementsSequence(private val statements: List<Statement>) : Statement {

    /**
     * Executes all [statements]. If one of the statements returns a non-null value,
     * then the execution stops and the value immediately returned, otherwise returns null.
     * @return [Value] returned by one of the statements or null if all statements returned null.
     */
    override fun execute(context: Context): Value? {
        statements.forEach { it.execute(context)?.let { returnedValue -> return returnedValue } }
        return null
    }

}