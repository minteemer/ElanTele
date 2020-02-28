import elanTele.interpreter.ProgramInterpreter
import elanTele.ir.Context
import elanTele.parser.ElanTeleLexer
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.junit.jupiter.api.*
import java.io.File

class InterpreterTests {
    companion object {
        private val classLoader = InterpreterTests::class.java.classLoader

        private val trivialFiles: List<File> = classLoader.getResource("trivial_programs")
                ?.let { File(it.file).listFiles() }
                ?.filter { it.isFile }
                ?: emptyList()

        private val incorrectFiles: List<File> = classLoader.getResource("incorrect_programs")
                ?.let { File(it.file).listFiles() }
                ?.filter { it.isFile }
                ?: emptyList()

    }


    @TestFactory
    fun trivialTestsGenerator() = trivialFiles.map { inputFile ->
        DynamicTest.dynamicTest(inputFile.nameWithoutExtension) {
            val lexer = ElanTeleLexer(CharStreams.fromPath(inputFile.toPath()))
            val parser = ElanTeleParser(CommonTokenStream(lexer))
            val program = parser.program()
            val statements = ProgramInterpreter.getProgram(program)

            val context = Context()
            statements.execute(context)
            println("Result context: $context")
        }
    }

    @TestFactory
    fun incorrectTestsGenerator() = incorrectFiles.map { inputFile ->
        DynamicTest.dynamicTest(inputFile.nameWithoutExtension) {
            val lexer = ElanTeleLexer(CharStreams.fromPath(inputFile.toPath()))
            val parser = ElanTeleParser(CommonTokenStream(lexer))
            val program = parser.program()
            val statements = ProgramInterpreter.getProgram(program)

            val context = Context()
            statements.execute(context)
            println("Result context: $context")
        }
    }

}