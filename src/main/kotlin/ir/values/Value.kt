package ir.values

open class Value(val valueClass: ValueClass) {

    open fun add(other: Value): Value =
            throw UnresolvedOperatorException("Can not add ${javaClass.simpleName} to ${other.javaClass.simpleName}")

    open fun subtract(other: Value): Value =
            throw UnresolvedOperatorException("Can not subtract ${other.javaClass.simpleName} from ${javaClass.simpleName}")

    open fun unaryMinus(): Value =
            throw UnresolvedOperatorException("Can not make negative ${javaClass.simpleName}")

    open fun multiply(other: Value): Value =
            throw UnresolvedOperatorException("Can not multiply ${javaClass.simpleName} and ${other.javaClass.simpleName}")

    open fun divide(other: Value): Value =
            throw UnresolvedOperatorException("Can not divide  ${javaClass.simpleName} by ${other.javaClass.simpleName}")

    open fun equals(other: Value): Value =
            throw UnresolvedOperatorException("Can not match ${javaClass.simpleName} and ${other.javaClass.simpleName}")

    open fun greater(other: Value): Value =
            throw UnresolvedOperatorException("Can not apply > operation to ${javaClass.simpleName} and ${other.javaClass.simpleName}")

    open fun less(other: Value): Value =
            throw UnresolvedOperatorException("Can not apply < operation to  ${javaClass.simpleName} and ${other.javaClass.simpleName}")

    open fun greaterOrEqual(other: Value): Value =
            throw UnresolvedOperatorException("Can not apply >= operation to  ${javaClass.simpleName} and ${other.javaClass.simpleName}")

    open fun lessOrEqual(other: Value): Value =
            throw UnresolvedOperatorException("Can not apply <= operation to  ${javaClass.simpleName} and ${other.javaClass.simpleName}")

    open fun notEquals(other: Value): Value =
            throw UnresolvedOperatorException("Can not apply /= operation to  ${javaClass.simpleName} and ${other.javaClass.simpleName}")

    open fun and(other: Value): Value =
            throw UnresolvedOperatorException("Can not apply 'and' logical operation to ${javaClass.simpleName} and ${other.javaClass.simpleName}")

    open fun or(other: Value): Value =
            throw UnresolvedOperatorException("Can not apply 'or' logical operation to ${javaClass.simpleName} to ${other.javaClass.simpleName}")

    open fun xor(other: Value): Value =
            throw UnresolvedOperatorException("Can not apply 'xor' logical operation to ${javaClass.simpleName} to ${other.javaClass.simpleName}")

    open fun not(): Value =
            throw UnresolvedOperatorException("Can not apply 'not' logical operation to ${javaClass.simpleName}")

    open fun isClass(valueClass: ValueClass) = BooleanValue(valueClass == this.valueClass)
}
