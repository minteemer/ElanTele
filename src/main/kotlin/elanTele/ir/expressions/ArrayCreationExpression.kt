package elanTele.ir.expressions

import elanTele.ir.Context
import elanTele.ir.statements.StatementsSequence
import elanTele.ir.values.Value
import elanTele.ir.values.classes.ArrayValue
import elanTele.parser.ElanTeleParser

class ArrayCreationExpression(private val expressions: Map<Int, Expression>) : Expression {
    /**
     *  @param [Context] of program depending on it expressions are executed
     *  @return [Value] that is [ArrayValue] which contains calculated expressions
     *
     */
    override fun execute(context: Context): Value =
            ArrayValue(expressions.mapValues { (_, value) -> value.execute(context) })

}