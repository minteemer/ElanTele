package elanTele.ir.expressions

/**
 * Possible types of binary operations
 */
enum class OperatorType {
    OR, AND, XOR,
    LESS, LESS_EQUAL, GREATER, GREATER_EQUAL, EQUAL, NOT_EQUAL,
    ADD, SUBTRACT,
    MULTIPLY, DIVIDE,
    IS, UNARY_PLUS, UNARY_MINUS, UNARY_NOT
}