import elanTele.execute
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

class OutputTests {
    companion object {

        private val outputTests = listOf(
                OutputTest(
                        "Assignment test",
                        "var a := 1; var b := 2",
                        mapOf(
                                "a" to 1.toVal(),
                                "b" to 2.toVal()
                        )
                ),
                OutputTest(
                        "Multiline exmple",
                        """
                            var a:= 1337
                            var b;
                            b := a - a
                            b := a + a
                        """,
                        mapOf("b" to 2674.toVal())
                )
        )

        private fun Int.toVal() = IntegerValue(this)
        private fun Double.toVal() = RealValue(this)
        private fun String.toVal() = StringValue(this)
        private fun Boolean.toVal() = BooleanValue(this)
    }


    @TestFactory
    fun outputTestsGenerator() = outputTests.map { test ->
        DynamicTest.dynamicTest(test.name) {
            val lexer = ElanTeleLexer(CharStreams.fromString(test.sourceCode))
            val context = Context()
            execute(context, lexer)
            println("Result context: $context")

            test.outputs.forEach { (varName, value) ->
                assertEquals(value, context.getValue(varName))
            }
        }
    }

    private data class OutputTest(
            val name: String,
            val sourceCode: String,
            val outputs: Map<String, Value>
    )


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