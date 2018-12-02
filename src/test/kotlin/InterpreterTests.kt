import elanTele.interpreter.statements.BodyStatementInterpreter
import elanTele.parser.ElanTeleLexer
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.junit.jupiter.api.*
import java.io.File

class InterpreterTests {
    companion object {
        private val classLoader = InterpreterTests::class.java.classLoader

        private val inputFilesFolder = classLoader.getResource("programs").file

        private val testFiles: List<File> = File(inputFilesFolder).listFiles().filter { it.isFile }
    }

    @TestFactory
    fun testTreeGeneration() = testFiles.map { inputFile ->
        DynamicTest.dynamicTest(inputFile.nameWithoutExtension) {
            //inputFile.readText()
            val lexer = ElanTeleLexer(CharStreams.fromPath(inputFile.toPath()))
            val parser = ElanTeleParser(CommonTokenStream(lexer))
            val program = parser.program()
            val statements = BodyStatementInterpreter.getBody(program)
            Assertions.assertNotNull(statements)
        }
    }
}