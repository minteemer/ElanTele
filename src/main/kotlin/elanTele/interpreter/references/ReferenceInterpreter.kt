package elanTele.interpreter.references


import elanTele.interpreter.expressions.binary.ExpressionInterpreter
import elanTele.ir.references.*
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree

object ReferenceInterpreter {

    fun getReference(tree: ElanTeleParser.ReferenceContext): Reference =
            tree.variableReference()?.let { VariableReference(it.text) }
                    ?: getReferenceIdentifier(getReference(tree.reference()), tree)

    private fun getReferenceIdentifier(variable: Reference, tree: ElanTeleParser.ReferenceContext): Reference =
            tree.arrayElementReference()?.let {
                ArrayElementReference(variable, ExpressionInterpreter.getExpression(it.expression()))
            }
                    ?: tree.dictElementIdentifierReference()?.let {
                        DictElementReference(variable, it.Identifier().text)
                    }
                    ?: tree.dictElementNumberReference()?.let {
                        TODO("Implement access tuple element by index")
                        // DictElementReference(variable, it.IntegerLiteral().text.toInt())
                    }
                    ?: tree.functionCallReference()?.let {
                        FunctionCallReference(
                                variable,
                                it.expression().map { ExpressionInterpreter.getExpression(it) }
                        )
                    }
                    ?: throw Exception("Exception during interpreting reference type in class ReferenceInterpreter")

}
