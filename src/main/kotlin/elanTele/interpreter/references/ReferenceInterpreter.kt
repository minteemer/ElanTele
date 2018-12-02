package elanTele.interpreter.references


import elanTele.interpreter.expressions.ExpressionInterpreter
import elanTele.ir.references.*
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree

object ReferenceInterpreter {

    fun getReference(tree: ParseTree): Reference {
        when (tree) {
            is ElanTeleParser.ReferenceContext -> {
                if (tree.childCount == 1) {
                    return VariableReference(tree.getChild(0).getChild(0).text)
                } else {
                    val variable: Reference = getReference(tree.getChild(0))
                    return getReferenceIdentifier(variable, tree.getChild(1))
                }
            }
            else -> throw Exception("Exception during interpreting reference type in class ReferenceInterpreter")
        }
    }

    private fun getReferenceIdentifier(variable: Reference, tree: ParseTree): Reference {
        when (tree) {
            is ElanTeleParser.ArrayElementReferenceContext -> {
                return ArrayElementReference(variable, ExpressionInterpreter.getExpression(tree))
            }
            is ElanTeleParser.DictElementIdentifierReferenceContext -> {
                return DictElementReference(variable, tree.getChild(1).text)
            }
//            TODO: Add calling dictionary by integer values
//            is ElanTeleParser.DictElementNumberReferenceContext -> {
//                return ???(variable, tree.getChild(1).text)
//            }
            is ElanTeleParser.FunctionCallReferenceContext -> {
                return FunctionCallReference(variable, tree.expression().map{ExpressionInterpreter.getExpression(it)})
            }
            else -> throw Exception("Exception during interpreting reference type in class ReferenceInterpreter")
        }
    }
}
