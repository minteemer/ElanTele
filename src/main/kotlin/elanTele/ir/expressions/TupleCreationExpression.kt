package elanTele.ir.expressions

import elanTele.ir.Context
import elanTele.ir.values.Value
import elanTele.ir.values.classes.DictValue

class TupleCreationExpression(private val expressions: Map<String, Expression>):Expression {

    override fun execute(context: Context): Value =
            DictValue(expressions.mapValues { (key, value) -> value.execute(context) })

}