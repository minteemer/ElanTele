package ir.expressions

import ir.values.Context
import ir.values.Value

interface Expression {
    fun execute(context: Context): Value
}