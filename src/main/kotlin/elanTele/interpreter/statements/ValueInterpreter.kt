package elanTele.interpreter.statements

import elanTele.ir.values.Value
import elanTele.ir.values.classes.IntegerValue
import org.antlr.runtime.tree.ParseTree

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
    fun getValue(tree: ParseTree): Value {
        val valueString = tree.getChild(0).text

        // ValueInterpreter should only be called on terminals
        if (valueString.startsWith("(")) {
            // TODO create a new exception
            throw Exception("ValueInterpreter called on (expression)")
        }

        return IntegerValue(42)
    }
}