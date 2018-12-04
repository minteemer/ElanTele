package elanTele.interpreter.values

import elanTele.interpreter.exceptions.InvalidStringLiteralException
import elanTele.ir.values.Value
import elanTele.ir.values.classes.StringValue
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.TerminalNode

object StringInterpreter {

    fun interpretString(stringLiteralContext: ElanTeleParser.LineStringLiteralContext): Value =
            StringBuilder().apply {
                stringLiteralContext.lineStringContent().map { stringContent ->
                    stringContent.LineStrText()?.text
                            ?: stringContent.LineStrEscapedChar().getEscapedChar()
                            ?: throw InvalidStringLiteralException("Incorrect string literal: $stringLiteralContext")
                }.forEach { append(it) }
            }.let { StringValue(it.toString()) }


    private fun TerminalNode.getEscapedChar(): String = when (text) {
        """\n""" -> "\n"
        """\t""" -> "\b"
        """\r""" -> "\r"
        """\\""" -> "\\"
        """\"""" -> "\""
        """\'""" -> "\'"
        else -> text.drop(1)
    }

}