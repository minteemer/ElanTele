package ir.values

open class Value {
    // TODO: Write exception messages

    open fun add(other: Value): Value =
            throw UnresolvedOperatorException("Can not add ${javaClass.simpleName} to ${other.javaClass.simpleName}")

    open fun subtract(other: Value): Value =
            throw UnresolvedOperatorException("Can not add ${javaClass.simpleName} to ${other.javaClass.simpleName}")

    open fun unaryMinus(): Value =
            throw UnresolvedOperatorException("Can not add ${javaClass.simpleName}")

    open fun multiply(other: Value): Value =
            throw UnresolvedOperatorException("Can not add ${javaClass.simpleName} to ${other.javaClass.simpleName}")

    open fun divide(other: Value): Value =
            throw UnresolvedOperatorException("Can not add ${javaClass.simpleName} to ${other.javaClass.simpleName}")

    open fun equals(other: Value): Value =
            throw UnresolvedOperatorException("Can not add ${javaClass.simpleName} to ${other.javaClass.simpleName}")

    open fun greater(other: Value): Value =
            throw UnresolvedOperatorException("Can not add ${javaClass.simpleName} to ${other.javaClass.simpleName}")

    open fun less(other: Value): Value =
            throw UnresolvedOperatorException("Can not add ${javaClass.simpleName} to ${other.javaClass.simpleName}")

    open fun greaterOrEqual(other: Value): Value =
            throw UnresolvedOperatorException("Can not add ${javaClass.simpleName} to ${other.javaClass.simpleName}")

    open fun lessOrEqual(other: Value): Value =
            throw UnresolvedOperatorException("Can not add ${javaClass.simpleName} to ${other.javaClass.simpleName}")

    open fun notEquals(other: Value): Value =
            throw UnresolvedOperatorException("Can not add ${javaClass.simpleName} to ${other.javaClass.simpleName}")

    open fun and(other: Value): Value =
            throw UnresolvedOperatorException("Can not add ${javaClass.simpleName} to ${other.javaClass.simpleName}")

    open fun or(other: Value): Value =
            throw UnresolvedOperatorException("Can not add ${javaClass.simpleName} to ${other.javaClass.simpleName}")

    open fun xor(other: Value): Value =
            throw UnresolvedOperatorException("Can not add ${javaClass.simpleName} to ${other.javaClass.simpleName}")

    open fun not(): Value =
            throw UnresolvedOperatorException("Can not add ${javaClass.simpleName}")

}
