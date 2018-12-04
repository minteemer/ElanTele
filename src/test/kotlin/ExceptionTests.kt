import elanTele.ElanTeleExecutor
import elanTele.ir.Context
import elanTele.ir.exceptions.*
import elanTele.parser.ElanTeleLexer
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
                        "Assignment",
                        "var f := func is print 1; end; f() := 1",
                        AssignmentException::class.java
                ),
                ExceptionTest(
                        "Context",
                        "a := 1;",
                        ContextException::class.java
                ),
                ExceptionTest(
                        // ExpressionException is unreachable
                        "Expression",
                        "1",
                        Exception::class.java
                ),
                ExceptionTest(
                        "InvalidIndex",
                        "var a := {a := 1, b := 2}; print a.3",
                        InvalidIndexException::class.java
                ),
                ExceptionTest(
                        "InvalidType",
                        "var a := 1; print a.1",
                        InvalidTypeException::class.java
                ),
                ExceptionTest(
                        "UniterableRange",
                        "var a := func is print 1; end; for i in a..1 loop print i; end;",
                        UniterableRangeException::class.java
                ),
                ExceptionTest(
                        "UnresolvedIdentifier",
                        "var a := {a := 1, b := 2}; print a.c",
                        UnresolvedIdentifierException::
                        class.java
                ),
                ExceptionTest(
                        "UnresolvedOperator",
                        "var f := func is print 1; end; print f + 1",
                        UnresolvedOperatorException::class.java
                ),
                ExceptionTest(
                        "ParseCancellation",
                        "print )1+1(",
                        ParseCancellationException::class.java
                )
        )
    }

    @TestFactory
    fun exceptionTestsGenerator() = exceptionTests.map { test ->
        DynamicTest.dynamicTest(test.name) {
            val lexer = ElanTeleLexer(CharStreams.fromString(test.sourceCode))
            val context = Context()
            Assertions.assertThrows(test.exceptionClass) {
                ElanTeleExecutor.unsafeExecute(context, lexer)
                // uncomment if needed
                // println("Result context: $context")
            }
        }
    }
}