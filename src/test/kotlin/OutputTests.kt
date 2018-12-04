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


class OutputTests {
    companion object {

        private val outputTests = listOf(
                OutputTest(
                        "int plus int",
                        "var a := (1 + 1); var b := a is int",
                        mapOf(
                                "a" to 2.toVal(),
                                "b" to true.toVal()
                        )
                ),
                OutputTest(
                        "int plus real",
                        "var a := (1 + 1.0); var b := a is real",
                        mapOf(
                                "a" to 2.0.toVal(),
                                "b" to true.toVal()
                        )
                ),
                OutputTest(
                        "real plus int",
                        "var a := (1.0 + 1); var b := a is real",
                        mapOf(
                                "a" to 2.0.toVal(),
                                "b" to true.toVal()
                        )
                ),
                OutputTest(
                        "real plus real",
                        "var a := (1.0 + 1.0); var b := a is real",
                        mapOf(
                                "a" to 2.0.toVal(),
                                "b" to true.toVal()
                        )
                ),
                OutputTest(
                        "string plus string",
                        "var a := ('succ' + \"test\"); var b := a is string",
                        mapOf(
                                "a" to "succtest".toVal(),
                                "b" to true.toVal()
                        )
                ),
                OutputTest(
                        "tuple plus tuple",
                        "var a := ({a := 1} + {1}); var b := a is {}",
                        mapOf(
                                "a" to TupleValue(listOf("a" to IntegerValue(1), null to IntegerValue(1))),
                                "b" to true.toVal()
                        )
                ),
                OutputTest(
                        "array plus array",
                        "var a := ([1] + [4]); var b := a is []",
                        mapOf(
                                "a" to ArrayValue(mapOf(1 to IntegerValue(1), 2 to IntegerValue(4))),
                                "b" to true.toVal()
                        )
                ),
                OutputTest(
                        "int minus int",
                        "var a := (1 - 1); var b := a is int",
                        mapOf(
                                "a" to 0.toVal(),
                                "b" to true.toVal()
                        )
                ),
                OutputTest(
                        "int minus real",
                        "var a := (1 - 1.0); var b := a is real",
                        mapOf(
                                "a" to 0.0.toVal(),
                                "b" to true.toVal()
                        )
                ),
                OutputTest(
                        "real minus int",
                        "var a := (1.0 - 1); var b := a is real",
                        mapOf(
                                "a" to 0.0.toVal(),
                                "b" to true.toVal()
                        )
                ),
                OutputTest(
                        "real minus real",
                        "var a := (1.0 - 1.0); var b := a is real",
                        mapOf(
                                "a" to 0.0.toVal(),
                                "b" to true.toVal()
                        )
                ),
                OutputTest(
                        "int multiply int",
                        "var a := (1 * 1); var b := a is int",
                        mapOf(
                                "a" to 1.toVal(),
                                "b" to true.toVal()
                        )
                ),
                OutputTest(
                        "int multiply real",
                        "var a := (1 * 1.0); var b := a is real",
                        mapOf(
                                "a" to 1.0.toVal(),
                                "b" to true.toVal()
                        )
                ),
                OutputTest(
                        "real multiply int",
                        "var a := (1.0 * 1); var b := a is real",
                        mapOf(
                                "a" to 1.0.toVal(),
                                "b" to true.toVal()
                        )
                ),
                OutputTest(
                        "real multiply real",
                        "var a := (1.0 * 1.0); var b := a is real",
                        mapOf(
                                "a" to 1.0.toVal(),
                                "b" to true.toVal()
                        )
                ),
                OutputTest(
                        "int divide int",
                        "var a := (1 / 1); var b := a is int",
                        mapOf(
                                "a" to 1.toVal(),
                                "b" to true.toVal()
                        )
                ),
                OutputTest(
                        "int divide real",
                        "var a := (1 / 1.0); var b := a is real",
                        mapOf(
                                "a" to 1.0.toVal(),
                                "b" to true.toVal()
                        )
                ),
                OutputTest(
                        "real divide int",
                        "var a := (1.0 / 1); var b := a is real",
                        mapOf(
                                "a" to 1.0.toVal(),
                                "b" to true.toVal()
                        )
                ),
                OutputTest(
                        "real divide real",
                        "var a := (1.0 / 1.0); var b := a is real",
                        mapOf(
                                "a" to 1.0.toVal(),
                                "b" to true.toVal()
                        )
                ),
                OutputTest(
                        "int comparison int",
                        "var a := (1 > 1); a := (1 < 1); a := (1 <= 1); a := (1 >= 1); a := (1 = 1); a := (1 /= 1); var b := a is bool",
                        mapOf(
                                "a" to false.toVal(),
                                "b" to true.toVal()
                        )
                ),
                OutputTest(
                        "int comparison real",
                        "var a := (1 > 1.0); a := (1 < 1.0); a := (1 <= 1.0); a := (1 >= 1.0); a := (1 = 1.0); a := (1 /= 1.0); var b := a is bool",
                        mapOf(
                                "a" to false.toVal(),
                                "b" to true.toVal()
                        )
                ),
                OutputTest(
                        "real comparison int",
                        "var a := (1.0 > 1); a := (1.0 < 1); a := (1.0 <= 1); a := (1.0 >= 1); a := (1.0 = 1); a := (1.0 /= 1); var b := a is bool",
                        mapOf(
                                "a" to false.toVal(),
                                "b" to true.toVal()
                        )
                ),
                OutputTest(
                        "real comparison real",
                        "var a := (1.0 > 1.0); a := (1.0 < 1.0); a := (1.0 <= 1.0); a := (1.0 >= 1.0); a := (1.0 = 1.0); a := (1.0 /= 1.0); var b := a is bool",
                        mapOf(
                                "a" to false.toVal(),
                                "b" to true.toVal()
                        )
                ),
                OutputTest(
                        "int unary minus",
                        "var a:= -1; var b := -(a)",
                        mapOf(
                                "a" to (-1).toVal(),
                                "b" to 1.toVal()
                        )
                ),
                OutputTest(
                        "int unary plus",
                        "var a:= +1; var b := +(a)",
                        mapOf(
                                "a" to 1.toVal(),
                                "b" to 1.toVal()
                        )
                ),
                OutputTest(
                        "real unary minus",
                        "var a:= -1.0; var b := -(a)",
                        mapOf(
                                "a" to (-1.0).toVal(),
                                "b" to 1.0.toVal()
                        )
                ),
                OutputTest(
                        "real unary plus",
                        "var a:= +1.0; var b := +(a)",
                        mapOf(
                                "a" to 1.0.toVal(),
                                "b" to 1.0.toVal()
                        )
                ),
                OutputTest(
                        "logical or",
                        "var a:= true; var b := a or false",
                        mapOf(
                                "a" to true.toVal(),
                                "b" to true.toVal()
                        )
                ),
                OutputTest(
                        "logical and",
                        "var a:= true; var b := a and false",
                        mapOf(
                                "a" to true.toVal(),
                                "b" to false.toVal()
                        )
                ),
                OutputTest(
                        "logical not",
                        "var a:= true; var b :=  not (a)",
                        mapOf(
                                "a" to true.toVal(),
                                "b" to false.toVal()
                        )
                ),
                OutputTest(
                        "logical xor",
                        "var a:= true; var b := a xor false",
                        mapOf(
                                "a" to true.toVal(),
                                "b" to true.toVal()
                        )
                ),
                OutputTest(
                        "array operations",
                        """var a := []
                                    var c := 2;
                                    a[1] := c
                                    a[c] := 1
                                    var a1 := a[1]
                                    var a2 := a[c]
                                    var d := a[1] + a[c]
                                    a[100]:= func(x)=>x+1;
                                    var fun := a[100] is func
                                    a[1000]:= {d:=1,b:=2.7};
                                    var tup := a[1000] is {}
                                    a[1337] := [1, 2, 3];
                                    var arr := a[1337] is []
                                    a := a + [1, 2, 3]
                                    var c0 := a[100](10)
                                    var c1 := a[1000].d
                                    var c2 := a[1337][1]""",
                        mapOf(
                                "c" to 2.toVal(),
                                "a1" to 2.toVal(),
                                "a2" to 1.toVal(),
                                "d" to 3.toVal(),
                                "fun" to true.toVal(),
                                "tup" to true.toVal(),
                                "arr" to true.toVal(),
                                "c0" to 11.toVal(),
                                "c1" to 1.toVal(),
                                "c2" to 1.toVal()
                        )
                ),
                OutputTest(
                        "boolean operations",
                        "var a:= not(false and (true xor false and true))",
                        mapOf(
                                "a" to true.toVal()
                        )
                ),
                OutputTest(
                        "for const loop",
                        """
                                      var a := 0
                                      for 1..3
                                      loop
                                          a := a + 1
                                          a := a + 1
                                      end""",
                        mapOf(
                                "a" to 6.toVal()
                        )
                ),
                OutputTest(
                        "for const loop",
                        """
                                      var a := 0
                                      for a in 1..3
                                      loop
                                          a := a + 1
                                          a := a + 1
                                      end""",
                        mapOf(
                                "a" to 5.toVal()
                        )
                ),
                OutputTest(
                        "functions literals",
                        """var a := func(x) => x + (x)
                                        var b := func(x) is return x*x end;
                                        var c := b(a(3))
                                        """,
                        mapOf(
                                "c" to 36.toVal()
                        )
                ),
                OutputTest(
                        "functions literals",
                        """  var x, c
                                        if 1 > 0 then x := 2; end;

                                        if x > 0 then
                                        c := 3
                                        else
                                        c := 1
                                        end;
                                        """,
                        mapOf(
                                "c" to 3.toVal(),
                                "x" to 2.toVal()
                        )
                ),
                OutputTest(
                        "functions literals",
                        """var a := func(x) => x + (x)
                                        var b := func(x) is return x*x end;
                                        var c := b(a(3))
                                        """,
                        mapOf(
                                "c" to 36.toVal()
                        )
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
}