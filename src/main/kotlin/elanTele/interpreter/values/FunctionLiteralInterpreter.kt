package elanTele.interpreter.values

import elanTele.interpreter.exceptions.InvalidFunctionBodyException
import elanTele.interpreter.expressions.binary.ExpressionInterpreter
import elanTele.interpreter.statements.BodyStatementInterpreter
import elanTele.ir.statements.ReturnStatement
import elanTele.ir.statements.Statement
import elanTele.ir.statements.StatementsSequence
import elanTele.ir.values.classes.FunctionValue
import elanTele.parser.ElanTeleParser
import java.lang.Exception

object FunctionLiteralInterpreter {

    /**
     *  @param [tree] is [ElanTeleParser.FunctionLiteralContext]
     *  @return [FunctionValue] that contains identifier and function body
     *
     */
    fun getFunction(tree: ElanTeleParser.FunctionLiteralContext): FunctionValue =
            FunctionValue(
                    tree.Identifier().map { it.text },
                    getFunBody(tree.funBody())
            )
    /**
     *  @param [tree] is [ElanTeleParser.FunBodyContext]
     *  @return [Statement] that contains either expression either body
     *
     */
    private fun getFunBody(funBody: ElanTeleParser.FunBodyContext): Statement =
            funBody.expression()?.let { ReturnStatement(ExpressionInterpreter.getExpression(it)) }
                    ?: funBody.body()?.let { BodyStatementInterpreter.getBody(it) }
                    ?: throw InvalidFunctionBodyException("Can't parse function body: $funBody")

}