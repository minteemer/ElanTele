package elanTele.interpreter.values

import elanTele.ir.values.Value
import elanTele.ir.values.classes.BooleanValue
import elanTele.ir.values.classes.EmptyValue
import elanTele.ir.values.classes.IntegerValue
import elanTele.ir.values.classes.RealValue
import elanTele.parser.ElanTeleParser


object LiteralInterpreter {
    /**
     *  @param [tree] is [ElanTeleParser.LiteralContext]
     *  @return [Value] that contains literal
     *
     */
    fun getLiteral(tree: ElanTeleParser.LiteralContext): Value = with(tree) {
        lineStringLiteral()?.let { StringInterpreter.interpretString(it) }
                ?: IntegerLiteral()?.let { IntegerValue(it.text.toInt()) }
                ?: RealLiteral()?.let { RealValue(it.text.toDouble()) }
                ?: BooleanLiteral()?.let { BooleanValue(it.text!!.toBoolean()) }
                ?: EmptyType()?.let { EmptyValue() }
                ?: throw Exception("The tree has unknown LiteralContext payload: $tree")
    }
}