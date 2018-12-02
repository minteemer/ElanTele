package elanTele.interpreter.expressions

import elanTele.ir.exceptions.UnresolvedOperatorException
import elanTele.ir.expressions.BinaryExpression
import elanTele.ir.expressions.Expression
import elanTele.ir.expressions.OperatorType
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree

object FactorInterpreter {

    private fun getOP(tree: ElanTeleParser.FactorContext): OperatorType {
        if (tree.ADD() != null){
            return OperatorType.ADD
        }
        if (tree.SUB() != null){
            return OperatorType.SUBTRACT
        } else {
            throw UnresolvedOperatorException("Unresolved operator element")
        }
    }

    fun getFactor(tree: ParseTree): Expression {
        when (tree) {
            is ElanTeleParser.FactorContext -> {
                if (tree.factor() == null) {
                    return TermInterpreter.getTerm(tree.term())
                } else {
                    return BinaryExpression(TermInterpreter.getTerm(tree.term()), FactorInterpreter.getFactor(tree.factor()), getOP(tree))
                }
            }
            else -> throw ClassCastException("Unknown tree element")
        }
    }
}