package elanTele.interpreter.references


import elanTele.interpreter.expressions.binary.ExpressionInterpreter
import elanTele.ir.references.*
import elanTele.parser.ElanTeleParser

object ReferenceInterpreter {

    /**
     *  @param [tree] is [ElanTeleParser.ReferenceContext]
     *  @return [Reference] that contains reference
     */
    fun getReference(tree: ElanTeleParser.ReferenceContext): Reference =
            tree.variableReference()?.let { VariableReference(it.text) }
                    ?: getReferenceIdentifier(getReference(tree.reference()), tree)

    private fun getReferenceIdentifier(variable: Reference, tree: ElanTeleParser.ReferenceContext): Reference =
            tree.arrayElementReference()?.let {
                ArrayElementReference(variable, ExpressionInterpreter.getExpression(it.expression()))
            }
                    ?: tree.dictElementIdentifierReference()?.let {
                        TupleElementIdentifierReference(variable, it.Identifier().text)
                    }
                    ?: tree.dictElementNumberReference()?.let {
                        TupleElementIndexReference(variable, it.IntegerLiteral().text.toInt())
                    }
                    ?: tree.functionCallReference()?.let {
                        FunctionCallReference(
                                variable,
                                it.expression().map { ExpressionInterpreter.getExpression(it) }
                        )
                    }
                    ?: throw Exception("Exception during interpreting reference type in class ReferenceInterpreter")

}
