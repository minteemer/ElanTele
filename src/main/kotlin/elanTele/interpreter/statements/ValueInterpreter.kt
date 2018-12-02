package elanTele.interpreter.statements

import elanTele.ir.values.Value
import elanTele.ir.values.classes.*
import elanTele.parser.ElanTeleLexer
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.tree.ParseTree

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
            is ElanTeleParser.FunctionLiteralContext -> TODO("Parse function")
            else -> TODO("handle unexpected node")
        }
    }

    private fun getLiteral(tree: ElanTeleParser.LiteralContext): Value = tree.payload.let { node ->
        when (node) {
            is ElanTeleParser.TupleContext -> interpretTuple(node)
            is ElanTeleParser.ArrayContext -> interpretArray(node)
            is ElanTeleParser.LineStringLiteralContext -> StringValue(node.text!!) // node.text probably has ""
            is Token -> when (node.type) {
//                ElanTeleLexer.IntegerLiteral -> IntegerValue(node.text.toInt())
//                ElanTeleLexer.RealLiteral -> RealValue(node.text.toDouble())
//                ElanTeleLexer.BooleanLiteral -> BooleanValue(node.text!!.toBoolean())
//                ElanTeleLexer.EmptyType -> EmptyValue()
                else -> TODO()
            }
            else -> {
                throw Exception("The tree has unknown LiteralContext payload: $tree")
            }
        }
    }

    private fun interpretTuple(tupleContext: ElanTeleParser.TupleContext): DictValue {
        TODO("Implement tuple interpreter")
    }

    private fun interpretArray(arrayContext: ElanTeleParser.ArrayContext): ArrayValue {
        TODO("Implement array interpreter")
    }

    private fun interpretLineString(lineStringLiteralContext: ElanTeleParser.LineStringLiteralContext): StringValue {
        TODO("Implement LineString interpreter")
    }
}