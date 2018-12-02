package elanTele.ir.statements

import elanTele.ir.Context

interface Statement {
    fun execute(context: Context)
}
