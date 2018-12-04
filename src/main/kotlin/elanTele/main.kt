@file:JvmName("Main")

package elanTele

import elanTele.interpreter.ProgramInterpreter
import elanTele.interpreter.exceptions.InterpreterException
import elanTele.ir.Context
import elanTele.ir.exceptions.InternalRepresentationException
import elanTele.parser.ElanTeleLexer
import elanTele.parser.ElanTeleLexerTatarcha
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.Lexer
import org.antlr.v4.runtime.misc.ParseCancellationException
import java.io.File

const val TATAR_KEYWORD_FLAG = "-t"
const val EXIT_KEYWORD = "exit"
const val REPL_COMMAND_PROMPT = ">>> "

fun main(args: Array<String>) {
    val useTatarKeywords = args.contains(TATAR_KEYWORD_FLAG)

    args.lastOrNull { it.first() != '-' }?.let {
        executeFile(it, useTatarKeywords)
    } ?: runRepl(useTatarKeywords)
}

private fun executeFile(sourceFilePath: String, tatarTokens: Boolean) {
    val lexer = getLexer(tatarTokens, CharStreams.fromPath(File(sourceFilePath).toPath()))
    execute(Context(), lexer)
}

private fun runRepl(tatarTokes: Boolean) {
    val context = Context()
    print(REPL_COMMAND_PROMPT)
    var input = readLine()
    while (input != null && input != EXIT_KEYWORD) {
        val lexer = getLexer(tatarTokes, CharStreams.fromString(input))
        execute(context, lexer)

        print(REPL_COMMAND_PROMPT)
        input = readLine()
    }
}

private fun getLexer(useTatarTokes: Boolean, charStream: CharStream) =
        if (useTatarTokes)
            ElanTeleLexerTatarcha(charStream)
        else
            ElanTeleLexer(charStream)

private fun execute(context: Context, lexer: Lexer) {
    val errorListener = ParserErrorListener()

    lexer.apply {
        removeErrorListeners()
        addErrorListener(errorListener)
    }

    val parser = ElanTeleParser(CommonTokenStream(lexer)).apply {
        removeErrorListeners()
        addErrorListener(errorListener)
    }

    try {
        val program = ProgramInterpreter.getProgram(parser.program())
        program.execute(context)
    } catch (e: InternalRepresentationException) {
        System.err.println("Execution error: " + e.message)
    } catch (e: InterpreterException) {
        System.err.println("Interpretation error: " + e.message)
    } catch (e: ParseCancellationException) {
        System.err.println("Parser error: " + e.message)
    }
}