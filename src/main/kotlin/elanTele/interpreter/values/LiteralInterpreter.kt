package elanTele.interpreter.values

import elanTele.ir.values.Value
import elanTele.ir.values.classes.*
import elanTele.parser.ElanTeleParser


object LiteralInterpreter {

    fun getLiteral(tree: ElanTeleParser.LiteralContext): Value = with(tree) {
        tuple()?.let { interpretTuple(it) }
                ?: array()?.let { interpretArray(it) }
                ?: lineStringLiteral()?.let { interpretString(it) }
                ?: IntegerLiteral()?.let { IntegerValue(it.text.toInt()) }
                ?: RealLiteral()?.let { RealValue(it.text.toDouble()) }
                ?: BooleanLiteral()?.let { BooleanValue(it.text!!.toBoolean()) }
                ?: EmptyType()?.let { EmptyValue() }
                ?: throw Exception("The tree has unknown LiteralContext payload: $tree")
    }

    private fun interpretString(stringLiteralContext: ElanTeleParser.LineStringLiteralContext): Value {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun interpretTuple(tupleContext: ElanTeleParser.TupleContext): DictValue {
        TODO("Implement tuple interpreter. Received tuple context: $tupleContext")
    }

    private fun interpretArray(arrayContext: ElanTeleParser.ArrayContext): ArrayValue {
        TODO("Implement array interpreter. Received array context: $arrayContext")
    }

    private fun interpretLineString(lineStringLiteralContext: ElanTeleParser.LineStringLiteralContext): StringValue {
        TODO("Implement LineString interpreter. Received line string literal context: $lineStringLiteralContext")
    }
}