package elanTele.interpreter.expressions.unary

import elanTele.interpreter.references.ReferenceInterpreter
import elanTele.ir.expressions.Expression
import elanTele.ir.expressions.OperatorType
import elanTele.ir.expressions.ReferenceExpresion
import elanTele.ir.expressions.UnaryExpression
import elanTele.ir.values.ValueClass
import elanTele.parser.ElanTeleParser
import java.lang.Exception

object UnaryExpressionInterpreter {

    // oh god, what have i done...
    fun getUnaryExpression(tree: ElanTeleParser.UnaryContext): Expression =
            tree.reference()?.let { reference ->
                tree.typeIndicator()?.let { type ->
                    TODO("Implement is operator, get class with type.getClass()")
                } ?: ReferenceExpresion(ReferenceInterpreter.getReference(reference))
            } ?: tree.primary()?.let { PrimaryExpressionInterpreter.getPrimaryExpression(it) }
                    ?.let { primaryExpression ->
                        tree.getOperator()?.let { operator ->
                            UnaryExpression(primaryExpression, operator)
                        } ?: primaryExpression
                    }
            ?: throw Exception() // TODO: proper exception


    private fun ElanTeleParser.UnaryContext.getOperator(): OperatorType? = when {
        IS() != null -> OperatorType.IS
        NOT() != null -> OperatorType.UNARY_NOT
        SUB() != null -> OperatorType.UNARY_MINUS
        ADD() != null -> OperatorType.UNARY_PLUS
        else -> null
    }

    private fun ElanTeleParser.TypeIndicatorContext.getClass() = when {
        IntType() != null -> ValueClass.INTEGER
        RealType() != null -> ValueClass.REAL
        StringType() != null -> ValueClass.STRING
        BoolType() != null -> ValueClass.BOOLEAN
        EmptyType() != null -> ValueClass.EMPTY
        ArrayType() != null -> ValueClass.ARRAY
        TupleType() != null -> ValueClass.DICT
        FUNC() != null -> ValueClass.FUNCTION
        else -> throw Exception() // TODO: unresolved class name exception
    }
}