package elanTele.ir.expressions

import elanTele.ir.Context
import elanTele.ir.values.Value
import elanTele.ir.values.classes.TupleValue

class TupleCreationExpression(private val expressions: List<Pair<String?, Expression>>) : Expression {
    /**
     *  create tuple value by executing inner expressions
     */
    override fun execute(context: Context): Value =
            TupleValue(expressions.map { (key, value) -> key to value.execute(context) })

}