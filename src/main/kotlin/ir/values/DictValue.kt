package ir.values

class DictValue(val values: List<Value>) : Value(){
    override fun toString(): String = values.joinToString(prefix = "[", postfix = "]")
}
