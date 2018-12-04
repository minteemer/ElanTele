@file:JvmName("Main")

package elanTele

import elanTele.interpreter.ProgramInterpreter
import elanTele.ir.Context
import elanTele.parser.ElanTeleLexer
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.Lexer
import java.io.File

const val TATAR_KEYWORD_FLAG = "-t"
const val EXIT_KEYWORD = "exit"

fun main(args: Array<String>) {
    val useTatarKeywords = args.contains(TATAR_KEYWORD_FLAG)

    args.lastOrNull { it.first() != '-' }?.let {
        executeFile(it, useTatarKeywords)
    } ?: runRepl(useTatarKeywords)
}

private fun executeFile(sourceFilePath: String, tatarTokes: Boolean) {
    val lexer = getLexer(tatarTokes, CharStreams.fromPath(File(sourceFilePath).toPath()))
    execute(Context(), lexer)
}

private fun runRepl(tatarTokes: Boolean) {
    val context = Context()
    print(">>> ")
    var input = readLine()
    while (input != EXIT_KEYWORD) {
        val lexer = getLexer(tatarTokes, CharStreams.fromString(input))
        try {
            execute(context, lexer)
        } catch (e: Exception){
            e.printStackTrace()
        }

        print(">>> ")
        input = readLine()
    }
}

private fun getLexer(useTatarTokes: Boolean, charStream: CharStream) =
        if (useTatarTokes)
            TODO("implement tatar tokens lexer")
        else
            ElanTeleLexer(charStream)

private fun execute(context: Context, lexer: Lexer) {
    val parser = ElanTeleParser(CommonTokenStream(lexer))
    val program = ProgramInterpreter.getProgram(parser.program())
    program.execute(context)
}