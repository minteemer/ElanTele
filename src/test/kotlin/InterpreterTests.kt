import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import elanTele.ElanTeleSyntaxTreeGenearator
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
            inputFile.readText()
        }
    }
}