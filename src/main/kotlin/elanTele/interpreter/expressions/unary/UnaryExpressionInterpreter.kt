package elanTele.interpreter.expressions.unary

import elanTele.interpreter.exceptions.InvalidUnaryExpressionException
import elanTele.interpreter.exceptions.UnresolvedClassNameException
import elanTele.interpreter.references.ReferenceInterpreter
import elanTele.ir.expressions.*
import elanTele.ir.values.ValueClass
import elanTele.parser.ElanTeleParser

object UnaryExpressionInterpreter {

    /**
     *  @param [tree] is [ElanTeleParser.UnaryContext]
     *  @return [Expression] that contains unary expression
     */
    fun getUnaryExpression(tree: ElanTeleParser.UnaryContext): Expression =
            tree.reference()?.let { reference ->
                tree.typeIndicator()?.let { type ->
                    IsExpression(
                            ReferenceExpresion(ReferenceInterpreter.getReference(reference)),
                            type.getClass()
                    )
                } ?: ReferenceExpresion(ReferenceInterpreter.getReference(reference))
            } ?: tree.primary()?.let { PrimaryExpressionInterpreter.getPrimaryExpression(it) }
                    ?.let { primaryExpression ->
                        tree.getOperator()?.let { operator ->
                            UnaryExpression(primaryExpression, operator)
                        } ?: primaryExpression
                    }
            ?: throw InvalidUnaryExpressionException("Invalid UnaryContext: $tree")


    /**
     *  parse unary operation
     */
    private fun ElanTeleParser.UnaryContext.getOperator(): OperatorType? = when {
        IS() != null -> OperatorType.IS
        NOT() != null -> OperatorType.UNARY_NOT
        SUB() != null -> OperatorType.UNARY_MINUS
        ADD() != null -> OperatorType.UNARY_PLUS
        else -> null
    }

    /**
     *  parse type indicator
     */
    private fun ElanTeleParser.TypeIndicatorContext.getClass() = when {
        IntType() != null -> ValueClass.INTEGER
        RealType() != null -> ValueClass.REAL
        StringType() != null -> ValueClass.STRING
        BoolType() != null -> ValueClass.BOOLEAN
        EmptyType() != null -> ValueClass.EMPTY
        ArrayType() != null -> ValueClass.ARRAY
        TupleType() != null -> ValueClass.DICT
        FUNC() != null -> ValueClass.FUNCTION
        else -> throw UnresolvedClassNameException("Unresolved class name: ${this}")
    }
}