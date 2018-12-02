package elanTele.ir.references

import elanTele.ir.Context
import elanTele.ir.expressions.Expression
import elanTele.ir.values.Value

interface Reference : Expression {

    fun setValue(context: Context, value: Value)

    fun getValue(context: Context): Value

}