@file:JvmName("Main")

package elanTele

import com.google.gson.GsonBuilder
import elanTele.interpreter.ProgramInterpreter
import elanTele.interpreter.statements.BodyStatementInterpreter
import elanTele.parser.ElanTeleLexer
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.File

const val INPUT_FILE = "in.txt"
const val OUTPUT_FILE = "out.txt"

private val gson = GsonBuilder().setPrettyPrinting().create()

fun main(args: Array<String>) {
    val lexer = ElanTeleLexer(CharStreams.fromPath(File(INPUT_FILE).toPath()))
    val parser = ElanTeleParser(CommonTokenStream(lexer))

    ProgramInterpreter.getProgram(parser.program())
    /*println("Generating tree...")
    val tree = ElanTeleSyntaxTreeGenearator.generateTree(File(INPUT_FILE).toPath())

    println("Converting to JSON...")
    val output = gson.toJson(tree)

    println("Writing output...")
    File(OUTPUT_FILE).writeText(output)

    println("Complete!")*/
}