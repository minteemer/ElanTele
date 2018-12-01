package ir.expressions

import ir.Context
import ir.values.IntegerValue
import ir.values.RealValue
import ir.values.StringValue
import ir.values.Value

class ReadExpression(val inputType: InputType) : Expression {
    enum class InputType { REAL, INTEGER, STRING }

    override fun execute(context: Context): Value = (readLine() ?: "").let { input ->
        when (inputType) {
            InputType.REAL -> RealValue(input.toDouble())
            InputType.INTEGER -> IntegerValue(input.toInt())
            else -> StringValue(input)
        }
    }

}