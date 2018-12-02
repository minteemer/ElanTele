package elanTele.interpreter.statements

import elanTele.ir.statements.StatementsSequence
import org.antlr.v4.runtime.tree.ParseTree

object StatementsSequenceInterpreter {

    fun getStatementSequence(tree: ParseTree): StatementsSequence =
            (0..tree.childCount)
                    .map { StatementInterpreter.getStatement(tree.getChild(it)) }
                    .let { StatementsSequence(it) }

}