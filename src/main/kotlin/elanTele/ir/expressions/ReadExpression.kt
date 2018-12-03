package elanTele.ir.expressions

import elanTele.ir.Context
import elanTele.ir.values.Value
import elanTele.ir.values.classes.IntegerValue
import elanTele.ir.values.classes.RealValue
import elanTele.ir.values.classes.StringValue

data class ReadExpression(val inputType: InputType) : Expression {
    enum class InputType { REAL, INTEGER, STRING }

    override fun execute(context: Context): Value = (readLine() ?: "").let { input ->
        when (inputType) {
            InputType.REAL -> RealValue(input.toDouble())
            InputType.INTEGER -> IntegerValue(input.toInt())
            else -> StringValue(input)
        }
    }

}