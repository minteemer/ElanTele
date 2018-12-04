import elanTele.interpreter.ProgramInterpreter
import elanTele.ir.Context
import elanTele.ir.values.Value
import elanTele.ir.values.classes.*
import elanTele.parser.ElanTeleLexer
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
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

    @Test
    fun intPlusInt() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a := (1 + 1); var b := a is int"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(BooleanValue(true), context.getValue("b"))
        assertEquals(IntegerValue(2), context.getValue("a"))
    }

    @Test
    fun intPlusReal() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a := (1 + 1.0); var b := a is real"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(BooleanValue(true), context.getValue("b"))
        assertEquals(RealValue(2.0), context.getValue("a"))
    }

    @Test
    fun realPlusInt() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a := (1.0 + 1); var b := a is real"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(BooleanValue(true), context.getValue("b"))
        assertEquals(RealValue(2.0), context.getValue("a"))
    }

    @Test
    fun realPlusReal() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a := (1.0 + 1.0); var b := a is real"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(BooleanValue(true), context.getValue("b"))
        assertEquals(RealValue(2.0), context.getValue("a"))
    }

    @Test
    fun stringPlusString() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a := ('succ' + \"test\"); var b := a is string"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(BooleanValue(true), context.getValue("b"))
        assertEquals(StringValue("succtest"), context.getValue("a"))
    }

    @Test
    fun tuplePlusTuple() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a := ({a := 1} + {1}); var b := a is {}"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(BooleanValue(true), context.getValue("b"))
        assertEquals(TupleValue(listOf("a" to IntegerValue(1), null to IntegerValue(1))), context.getValue("a"))
    }

    @Test
    fun arrayPlusArray() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a := ([1] + [4]); var b := a is []"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(BooleanValue(true), context.getValue("b"))
        assertEquals(ArrayValue(mapOf(1 to IntegerValue(1), 2 to IntegerValue(4))), context.getValue("a"))
    }

    @Test
    fun intMinusInt() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a := (1 - 1); var b := a is int"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(BooleanValue(true), context.getValue("b"))
        assertEquals(IntegerValue(0), context.getValue("a"))
    }

    @Test
    fun intMinusReal() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a := (1 - 1.0); var b := a is real"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(BooleanValue(true), context.getValue("b"))
        assertEquals(RealValue(0.0), context.getValue("a"))
    }

    @Test
    fun realMinusInt() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a := (1.0 - 1); var b := a is real"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(BooleanValue(true), context.getValue("b"))
        assertEquals(RealValue(0.0), context.getValue("a"))
    }

    @Test
    fun realMinusReal() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a := (1.0 - 1.0); var b := a is real"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(BooleanValue(true), context.getValue("b"))
        assertEquals(RealValue(0.0), context.getValue("a"))
    }

    @Test
    fun intMultiplyInt() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a := (1 * 1); var b := a is int"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(BooleanValue(true), context.getValue("b"))
        assertEquals(IntegerValue(1), context.getValue("a"))
    }

    @Test
    fun intMultiplyReal() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a := (1 * 1.0); var b := a is real"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(BooleanValue(true), context.getValue("b"))
        assertEquals(RealValue(1.0), context.getValue("a"))
    }

    @Test
    fun realMultiplyInt() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a := (1.0 * 1); var b := a is real"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(BooleanValue(true), context.getValue("b"))
        assertEquals(RealValue(1.0), context.getValue("a"))
    }

    @Test
    fun realMultiplyReal() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a := (1.0 * 1.0); var b := a is real"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(BooleanValue(true), context.getValue("b"))
        assertEquals(RealValue(1.0), context.getValue("a"))
    }

    @Test
    fun intDivideInt() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a := (2 / 1); var b := a is int"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(BooleanValue(true), context.getValue("b"))
        assertEquals(IntegerValue(2), context.getValue("a"))
    }

    @Test
    fun intDivideReal() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a := (1 / 1.0); var b := a is real"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(BooleanValue(true), context.getValue("b"))
        assertEquals(RealValue(1.0), context.getValue("a"))
    }

    @Test
    fun realDivideInt() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a := (1.0 / 1); var b := a is real"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(BooleanValue(true), context.getValue("b"))
        assertEquals(RealValue(1.0), context.getValue("a"))
    }

    @Test
    fun realDivideReal() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a := (1.0 / 1.0); var b := a is real"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(BooleanValue(true), context.getValue("b"))
        assertEquals(RealValue(1.0), context.getValue("a"))
    }

    @Test
    fun intComparisonInt() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a := (1 > 1); a := (1 < 1); a := (1 <= 1); a := (1 >= 1); a := (1 = 1); a := (1 /= 1); var b := a is bool"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(BooleanValue(true), context.getValue("b"))
        assertEquals(BooleanValue(false), context.getValue("a"))
    }

    @Test
    fun intComparisonReal() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a := (1 > 1.0); a := (1 < 1.0); a := (1 <= 1.0); a := (1 >= 1.0); a := (1 = 1.0); a := (1 /= 1.0); var b := a is bool"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(BooleanValue(true), context.getValue("b"))
        assertEquals(BooleanValue(false), context.getValue("a"))
    }

    @Test
    fun realComparisonInt() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a := (1.0 > 1); a := (1.0 < 1); a := (1.0 <= 1); a := (1.0 >= 1); a := (1.0 = 1); a := (1.0 /= 1); var b := a is bool"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(BooleanValue(true), context.getValue("b"))
        assertEquals(BooleanValue(false), context.getValue("a"))
    }

    @Test
    fun realComparisonReal() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a := (1.0 > 1.0); a := (1.0 < 1.0); a := (1.0 <= 1.0); a := (1.0 >= 1.0); a := (1.0 = 1.0); a := (1.0 /= 1.0); var b := a is bool"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(BooleanValue(true), context.getValue("b"))
        assertEquals(BooleanValue(false), context.getValue("a"))
    }

    @Test
    fun realUnaryMinus() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a:= -1.0; var b := -(a)"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(RealValue(1.0), context.getValue("b"))
        assertEquals(RealValue(-1.0), context.getValue("a"))
    }

    @Test
    fun realUnaryPlus() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a:= +1.0; var b := +(a)"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(RealValue(1.0), context.getValue("b"))
        assertEquals(RealValue(1.0), context.getValue("a"))
    }

    @Test
    fun intUnaryPlus() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a:= +1; var b := +(a)"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(IntegerValue(1), context.getValue("b"))
        assertEquals(IntegerValue(1), context.getValue("a"))
    }

    @Test
    fun intUnaryMinus() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a:= -1; var b := -(a)"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(IntegerValue(1), context.getValue("b"))
        assertEquals(IntegerValue(-1), context.getValue("a"))
    }

    @Test
    fun logicalOr() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a:= true; var b := a or false"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(BooleanValue(true), context.getValue("b"))
        assertEquals(BooleanValue(true), context.getValue("a"))
    }

    @Test
    fun logicalAnd() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a:= true; var b := a and false"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(BooleanValue(false), context.getValue("b"))
        assertEquals(BooleanValue(true), context.getValue("a"))
    }

    @Test
    fun logicalNot() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a:= true; var b := not (a)"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(BooleanValue(false), context.getValue("b"))
        assertEquals(BooleanValue(true), context.getValue("a"))
    }

    @Test
    fun logicalXor() {
        val lexer = ElanTeleLexer(CharStreams.fromString("var a:= true; var b := a xor false"))
        val parser = ElanTeleParser(CommonTokenStream(lexer))
        val program = parser.program()
        val statements = ProgramInterpreter.getProgram(program)

        val context = Context()
        statements.execute(context)
        assertEquals(BooleanValue(true), context.getValue("b"))
        assertEquals(BooleanValue(true), context.getValue("a"))
    }
}