package elanTele

import elanTele.interpreter.ProgramInterpreter
import elanTele.interpreter.exceptions.InterpreterException
import elanTele.ir.Context
import elanTele.ir.exceptions.InternalRepresentationException
import elanTele.parser.ElanTeleLexer
import elanTele.parser.ElanTeleLexerTatarcha
import elanTele.parser.ElanTeleParser
import elanTele.parser.ParserErrorListener
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.Lexer
import org.antlr.v4.runtime.misc.ParseCancellationException
import java.io.File

object ElanTeleExecutor {
    private const val EXIT_KEYWORD = "exit"
    private const val REPL_COMMAND_PROMPT = ">>> "


    fun executeFile(sourceFilePath: String, tatarTokens: Boolean) {
        val lexer = getLexer(tatarTokens, CharStreams.fromPath(File(sourceFilePath).toPath()))
        safeExecute(Context(), lexer)
    }

    fun runRepl(tatarTokes: Boolean) {
        val context = Context()
        print(REPL_COMMAND_PROMPT)
        var input = readLine()
        while (input != null && input != EXIT_KEYWORD) {
            val lexer = getLexer(tatarTokes, CharStreams.fromString(input))
            safeExecute(context, lexer)

            print(REPL_COMMAND_PROMPT)
            input = readLine()
        }
    }

    private fun getLexer(useTatarTokes: Boolean, charStream: CharStream) =
            if (useTatarTokes)
                ElanTeleLexerTatarcha(charStream)
            else
                ElanTeleLexer(charStream)

    fun unsafeExecute(context: Context, lexer: Lexer) {
        val errorListener = ParserErrorListener()

        lexer.apply {
            removeErrorListeners()
            addErrorListener(errorListener)
        }

        val parser = ElanTeleParser(CommonTokenStream(lexer)).apply {
            removeErrorListeners()
            addErrorListener(errorListener)
        }

        val program = ProgramInterpreter.getProgram(parser.program())
        program.execute(context)
    }

    fun safeExecute(context: Context, lexer: Lexer) {
        try {
            unsafeExecute(context, lexer)
        } catch (e: InternalRepresentationException) {
            System.err.println("Execution error: " + e.message)
        } catch (e: InterpreterException) {
            System.err.println("Interpretation error: " + e.message)
        } catch (e: ParseCancellationException) {
            System.err.println("Parser error: " + e.message)
        }
    }
}