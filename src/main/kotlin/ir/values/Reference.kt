package ir.values

interface Reference {
    fun getValue(context: Context): Value
    fun setValue(context: Context, value: Value)
}