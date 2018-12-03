package elanTele.interpreter.values

import elanTele.interpreter.expressions.binary.ExpressionInterpreter
import elanTele.ir.expressions.ArrayCreationExpression
import elanTele.ir.expressions.Expression
import elanTele.ir.values.Value
import elanTele.ir.values.classes.*
import elanTele.parser.ElanTeleParser


object LiteralInterpreter {

    fun getLiteral(tree: ElanTeleParser.LiteralContext): Value = with(tree) {
        lineStringLiteral()?.let { StringInterpreter.interpretString(it) }
                ?: IntegerLiteral()?.let { IntegerValue(it.text.toInt()) }
                ?: RealLiteral()?.let { RealValue(it.text.toDouble()) }
                ?: BooleanLiteral()?.let { BooleanValue(it.text!!.toBoolean()) }
                ?: EmptyType()?.let { EmptyValue() }
                ?: throw Exception("The tree has unknown LiteralContext payload: $tree")
    }
}