package ir.statements

import ir.values.Context

interface Statement {
    fun execute(context: Context)
}
