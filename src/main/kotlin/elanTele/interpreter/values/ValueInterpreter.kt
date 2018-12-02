package elanTele.interpreter.statements

import elanTele.ir.values.Value
import elanTele.ir.values.classes.*
import elanTele.parser.ElanTeleLexer
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.Token

object ValueInterpreter {
    /*
    primary
        : literal
        | ReadInt
        | ReadReal
        | ReadString
        | functionLiteral
        | LPAREN expression RPAREN
        ;
    */
    fun getValue(tree: ElanTeleParser.PrimaryContext): Value = tree.payload.let { node ->
        when (node) {
            is ElanTeleParser.LiteralContext -> getLiteral(tree.literal())
            is ElanTeleParser.FunctionLiteralContext -> getFunction(tree.literal())
            else -> throw Exception("Unexpected node class: ${node.javaClass.simpleName}")
        }
    }

    private fun getLiteral(tree: ElanTeleParser.LiteralContext): Value = tree.payload.let { node ->
        when (node) {
            is ElanTeleParser.TupleContext -> interpretTuple(node)
            is ElanTeleParser.ArrayContext -> interpretArray(node)
            is ElanTeleParser.LineStringLiteralContext -> StringValue(node.text!!) // FIXME node.text contains ""?
            is Token -> when (node.type) {
                ElanTeleLexer.IntegerLiteral -> IntegerValue(node.payload.text.toInt())
                ElanTeleLexer.RealLiteral -> RealValue(node.payload.text.toDouble())
                ElanTeleLexer.BooleanLiteral -> BooleanValue(node.payload.text!!.toBoolean())
                ElanTeleLexer.EmptyType -> EmptyValue()
                else -> throw Exception("Unknown node type for Token: ${node.type}")
            }
            else -> {
                throw Exception("The tree has unknown LiteralContext payload: $tree")
            }
        }
    }

    private fun getFunction(literalContext: ElanTeleParser.LiteralContext?): Value {
        TODO("Implement function interpreter. Received literalContext context: $literalContext")
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