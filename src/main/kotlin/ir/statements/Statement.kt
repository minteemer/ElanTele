package ir.statements

import ir.Context

interface Statement {
    fun execute(context: Context)
}
