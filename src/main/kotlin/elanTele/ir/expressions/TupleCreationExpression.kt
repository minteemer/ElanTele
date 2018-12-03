package elanTele.ir.expressions

import elanTele.ir.Context
import elanTele.ir.values.Value
import elanTele.ir.values.classes.TupleValue

class TupleCreationExpression(private val expressions: Map<String, Expression>):Expression {

    override fun execute(context: Context): Value =
            TupleValue(expressions.mapValues { (key, value) -> value.execute(context) })

}