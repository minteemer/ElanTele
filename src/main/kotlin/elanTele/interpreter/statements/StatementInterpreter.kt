package elanTele.interpreter.statements

import elanTele.ir.statements.Statement
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree

object StatementInterpreter {

    fun getStatement(tree: ElanTeleParser.StatementContext): Statement =
            tree.declaration()?.let { DeclarationInterpreter.getDeclaration(it) }
                    ?: tree.assignment()?.let { AssignmentStatementInterpreter.getAssignmentStatement(it) }
                    ?: tree.loop()?.let { LoopStatementInterpreter.getLoopStatement(it) }
                    ?: tree.print()?.let { PrintStatementInterpreter.getPrintStatement(it) }
                    ?: tree.if_expression()?.let { IfStatementInterpreter.getIfStatement(it) }
                    ?: throw ClassCastException("Unknown tree element")


}
