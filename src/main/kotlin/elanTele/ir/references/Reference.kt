package elanTele.ir.references

import elanTele.ir.Context
import elanTele.ir.values.Value

interface Reference {

    fun setValue(context: Context, value: Value)

    fun getValue(context: Context): Value

}