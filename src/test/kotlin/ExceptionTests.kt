import elanTele.interpreter.exceptions.InterpreterException
import elanTele.ir.Context
import elanTele.ir.exceptions.InternalRepresentationException
import elanTele.ir.exceptions.InvalidTypeException
import elanTele.ir.exceptions.UnresolvedIdentifierException
import elanTele.parser.ElanTeleLexer
import elanTele.unsafeExecute
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.misc.ParseCancellationException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class ExceptionTests {
    private data class ExceptionTest<E>(
            val name: String,
            val sourceCode: String,
            val exceptionClass: Class<E>
    )

    companion object {
        private val exceptionTests = listOf(
                ExceptionTest(
                        "No such variable",
                        "a := 1;",
                        InternalRepresentationException::class.java
                ),
                ExceptionTest(
                        "Assigning to function call",
                        "var f := func is print 1; f() := 1",
                        ParseCancellationException::class.java
                ),
                ExceptionTest(
                        "Syntax error",
                        "print )1+1(",
                        ParseCancellationException::class.java
                ),
                ExceptionTest(
                        "Invalid index",
                        "var a := {a := 1, b := 2}; print a.3",
                        // TODO: change this exception type
                        InvalidTypeException::class.java
                ),
                ExceptionTest(
                        "Invalid key",
                        "var a := {a := 1, b := 2}; print a.c",
                        UnresolvedIdentifierException::class.java
                )
        )
    }

    @TestFactory
    fun exceptionTestsGenerator() = exceptionTests.map { test ->
        DynamicTest.dynamicTest(test.name) {
            val lexer = ElanTeleLexer(CharStreams.fromString(test.sourceCode))
            val context = Context()
            Assertions.assertThrows(test.exceptionClass) {
                unsafeExecute(context, lexer)
                // uncomment if needed
                // println("Result context: $context")
            }
        }
    }
}