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

        private val trivialProgramsFolder = classLoader.getResource("trivial_programs").file
        private val trivialFiles: List<File> = File(trivialProgramsFolder).listFiles().filter { it.isFile }

        private val incorrectProgramsFolder = classLoader.getResource("incorrect_programs").file
        private val incorrectFiles: List<File> = File(incorrectProgramsFolder).listFiles().filter { it.isFile }

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