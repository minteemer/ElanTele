package elanTele.ir.statements

import elanTele.ir.Context
import elanTele.ir.expressions.Expression
import elanTele.ir.values.Value

class ReturnStatement(val expression: Expression) : Statement {

    /**
     * @return [Value] retrieved from execution of [expression]
     */
    override fun execute(context: Context): Value = expression.execute(context)

}
