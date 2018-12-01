package ir.values

class StringValue(val value: String) : Value(){
    override fun toString(): String = value
}
