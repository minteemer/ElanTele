package ir.references

import ir.Context
import ir.values.Value

interface Reference {

    fun setValue(context: Context, value: Value)

    fun getValue(context: Context): Value

}