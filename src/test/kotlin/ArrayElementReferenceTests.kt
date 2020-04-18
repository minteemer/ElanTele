import elanTele.ir.exceptions.InvalidIndexException
import elanTele.ir.exceptions.InvalidTypeException
import elanTele.ir.references.ArrayElementReference
import elanTele.ir.values.classes.ArrayValue
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class ArrayElementReferenceTests {

    @Test
    fun `Set to non-array value (1, 2)`() {
        Assertions.assertThrows(InvalidTypeException::class.java) {
            // context with string variable named `array`
            val context = buildContext("notArray" to "not an array".toVal())

            // trying to set value by array reference on non-array variable
            ArrayElementReference(
                    arrayReference = "notArray".toVarRef(),
                    indexExpression = 1.toExpr()
            ).setValue(context, 10.toVal())
        }
    }

    @Test
    fun `Set to non-int index (1, 3, 4)`() {
        Assertions.assertThrows(InvalidIndexException::class.java) {
            // context with array `arr` with 1 element
            val context = buildContext("arr" to ArrayValue(mapOf(1 to 1.toVal())))

            // trying to use non-integer array index
            ArrayElementReference(
                    arrayReference = "arr".toVarRef(),
                    indexExpression = 1.234.toExpr()
            ).setValue(context, 10.toVal())
        }
    }

    @Test
    fun `Set array value (1, 3, 5)`() {
        val context = buildContext("arr" to ArrayValue(emptyMap()))
        ArrayElementReference(
                arrayReference = "arr".toVarRef(),
                indexExpression = 1.toExpr()
        ).setValue(context, 10.toVal())

        Assertions.assertEquals(
                10.toVal(),
                (context.getValue("arr") as ArrayValue).getElement(1)
        )
    }

    @Test
    fun `Get from non-array value (1, 2)`() {
        Assertions.assertThrows(InvalidTypeException::class.java) {
            // context with string variable named `array`
            val context = buildContext("notArray" to "not an array".toVal())

            // trying to get value from array reference on non-array variable
            ArrayElementReference(
                    arrayReference = "notArray".toVarRef(),
                    indexExpression = 1.toExpr()
            ).getValue(context)
        }
    }

    @Test
    fun `Get from non-int index (1, 3, 4)`() {
        Assertions.assertThrows(InvalidIndexException::class.java) {
            // context with array `arr` with 1 element
            val context = buildContext("arr" to ArrayValue(mapOf(1 to 1.toVal())))

            // trying to use non-integer array index
            ArrayElementReference(
                    arrayReference = "arr".toVarRef(),
                    indexExpression = 1.234.toExpr()
            ).getValue(context)
        }
    }

    @Test
    fun `Get array value (1, 3, 5)`() {
        val context = buildContext("arr" to ArrayValue(emptyMap()))
        ArrayElementReference(
                arrayReference = "arr".toVarRef(),
                indexExpression = 1.toExpr()
        ).setValue(context, 10.toVal())

        Assertions.assertEquals(
                10.toVal(),
                (context.getValue("arr") as ArrayValue).getElement(1)
        )
    }


}
