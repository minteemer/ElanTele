package elanTele.ir.expressions

import elanTele.ir.Context
import elanTele.ir.values.Value
import elanTele.ir.values.classes.ArrayValue

class ArrayCreationExpression(private val expressions: Map<Int, Expression>):Expression {

    override fun execute(context: Context): Value =
            ArrayValue(expressions.mapValues { (key, value) -> value.execute(context) })

}