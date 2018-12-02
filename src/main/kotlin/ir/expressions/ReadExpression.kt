package ir.expressions

import ir.Context
import ir.values.classes.IntegerValue
import ir.values.classes.RealValue
import ir.values.classes.StringValue
import ir.values.Value

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