package ir.expressions

import ir.Context
import ir.values.Value

interface Expression {
    fun execute(context: Context): Value
}