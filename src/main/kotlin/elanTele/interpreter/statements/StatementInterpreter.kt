package elanTele.interpreter.statements

import elanTele.ir.statements.IfStatement
import elanTele.ir.statements.Statement
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree

object StatementInterpreter {

    /**
     *  @param [tree] is [ElanTeleParser.StatementContext]
     *  Determines type of statement and parse it
     *  @return [Statement]
     *
     */

    fun getStatement(tree: ElanTeleParser.StatementContext): Statement =
            tree.declaration()?.let { DeclarationInterpreter.getDeclaration(it) }
                    ?: tree.assignment()?.let { AssignmentStatementInterpreter.getAssignmentStatement(it) }
                    ?: tree.loop()?.let { LoopStatementInterpreter.getLoopStatement(it) }
                    ?: tree.print()?.let { PrintStatementInterpreter.getPrintStatement(it) }
                    ?: tree.if_expression()?.let { IfStatementInterpreter.getIfStatement(it) }
                    ?: tree.return_expression()?.let { ReturnStatementInterpreter.getReturnStatement(it) }
                    ?: throw ClassCastException("Unknown tree element")


}
