package ir.values

interface Value {
    fun plus(other: Value): Value

    // TODO: add -,*,/, =, <, >, <=, >=, /=, and, or xor, not
}
