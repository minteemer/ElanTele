import elanTele.ElanTeleExecutor
import elanTele.ir.Context
import elanTele.ir.values.Value
import elanTele.ir.values.classes.*
import elanTele.parser.ElanTeleLexer
import org.antlr.v4.runtime.CharStreams
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
                                "a" to 0.toVal()
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
                ),
                OutputTest(
                        "while loop",
                        "var a := 1;\n" +
                                "while a<10 loop print a; a:= 1 + a; end;\n" +
                                "var b := 10\n" +
                                "while b /= 0\n" +
                                "loop\n" +
                                "print b\n" +
                                "b := b - 2\n" +
                                "end",
                        mapOf(
                                "a" to 10.toVal(),
                                "b" to 0.toVal()
                        )
                ),
                OutputTest(
                        "assignments",
                        "var a := 1;\n" +
                                "var b := []\n" +
                                "var c := {1, j:=2, a}\n" +
                                "var d := \"d\"\n" +
                                "var e := 2.3\n" +
                                "var f := true\n" +
                                "var g := func(x) => x + 2\n" +
                                "var l := g is func",
                        mapOf(
                                "a" to 1.toVal(),
                                "b" to ArrayValue(mapOf()),
                                "c" to TupleValue(listOf(null to IntegerValue(1),
                                        "j" to IntegerValue(2),
                                        null to IntegerValue(1))),
                                "d" to "d".toVal(),
                                "e" to 2.3.toVal(),
                                "f" to true.toVal(),
                                "l" to true.toVal()
                        )
                ),
                OutputTest(
                        "unary operations",
                        "var a:= 1337\n" +
                                "var q,w,e,r,t,y,u,i,o,p,s,d,f;\n" +
                                "q := - (a)\n" +
                                "w := + (a)\n" +
                                "e := - 2\n" +
                                "r := + 2\n" +
                                "t := a\n" +
                                "y := a is int\n" +
                                "u := a is real\n" +
                                "i := a is bool\n" +
                                "o := a is string\n" +
                                "p := a is empty\n" +
                                "s := a is []\n" +
                                "d := a is {}\n" +
                                "f := a is func\n",
                        mapOf(
                                "q" to (-1 * 1337).toVal(),
                                "w" to 1337.toVal(),
                                "e" to (-1 * 2).toVal(),
                                "r" to 2.toVal(),
                                "t" to 1337.toVal(),
                                "y" to true.toVal(),
                                "u" to false.toVal(),
                                "i" to false.toVal(),
                                "o" to false.toVal(),
                                "p" to false.toVal(),
                                "s" to false.toVal(),
                                "d" to false.toVal(),
                                "f" to false.toVal()
                        )
                ),
                OutputTest(
                        "tuple operations",
                        "var t := {a:=1, b:=2, {d:=3}, [22, 33]};\n" +
                                "var x := t.b;\n" +
                                "var y1 := t.1;\n" +
                                "var y2 := t.4[2];\n" +
                                "var y3 := t.3.d;",
                        mapOf(
                                "x" to 2.toVal(),
                                "y1" to 1.toVal(),
                                "y2" to 33.toVal(),
                                "y3" to 3.toVal()
                        )
                ),
                OutputTest(
                        "string escapes",
                        "var a := ('succ' + \"t\\\'e\\\"s\tt\\\\ttt\"); \n" +
                                "var b := a is string",
                        mapOf(
                                "a" to "succt\'e\"s\tt\\ttt".toVal(),
                                "b" to true.toVal()
                        )
                ),
                OutputTest(
                        "return ends execution",
                        "var a := 1; return a; var a := 2",
                        mapOf(
                                "a" to 1.toVal()
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
            ElanTeleExecutor.unsafeExecute(context, lexer)
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