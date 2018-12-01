package ir.values

class ArrayValue(val values: List<Value>) : Value(){

    override fun toString(): String = values.joinToString(prefix = "[", postfix = "]")

}
