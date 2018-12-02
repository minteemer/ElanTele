package elanTele.interpreter.expressions

import elanTele.ir.exceptions.UnresolvedOperatorException
import elanTele.ir.expressions.BinaryExpression
import elanTele.ir.expressions.Expression
import elanTele.ir.expressions.OperatorType
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree

object RelationInterpreter {

    fun getOP(tree: ElanTeleParser.RelationContext): OperatorType {
        if (tree.EQEQ() != null){
            return OperatorType.EQUAL
        }
        if (tree.EXCL_EQ() != null){
            return OperatorType.NOT_EQUAL
        }
        if (tree.LANGLE() != null){
            return OperatorType.LESS
        }
        if (tree.RANGLE() != null){
            return OperatorType.GREATER
        }
        if (tree.LE() != null){
            return OperatorType.LESS_EQUAL
        }
        if (tree.GE() != null){
            return OperatorType.GREATER_EQUAL
        } else {
            throw UnresolvedOperatorException("Unresolved operator element")
        }
    }

    fun getRelation(tree: ParseTree): Expression {
        when (tree) {
            is ElanTeleParser.RelationContext -> {
                if (tree.factor(1) != null) {
                    return BinaryExpression(FactorInterpreter.getFactor(tree.factor(0)), FactorInterpreter.getFactor(tree.factor(1)), getOP(tree))
                } else  {
                    return FactorInterpreter.getFactor(tree.factor(0))
                }
            }
            else -> throw ClassCastException("Unknown tree element")
        }
    }

}