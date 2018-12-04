package elanTele.ir.statements

import elanTele.ir.Context
import elanTele.ir.values.Value

/**
 * Represents abstraction of a statement or a set of
 * statements that can be executed
 */
interface Statement {
    /**
     * Executes the statement
     * @param context execution context for the statement
     * @return [Value] if a value was returned during
     * execution of the statement, null otherwise
     */
    fun execute(context: Context): Value?
}
