package elanTele.ir.statements

import elanTele.ir.Context
import elanTele.ir.expressions.Expression
import elanTele.ir.values.Value

class ReturnStatement(val expression: Expression) : Statement {

    override fun execute(context: Context): Value? {
        return expression.execute(context)
    }

}
