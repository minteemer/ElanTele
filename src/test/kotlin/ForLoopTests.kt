import elanTele.ir.Context
import elanTele.ir.exceptions.UniterableRangeException
import elanTele.ir.expressions.BinaryExpression
import elanTele.ir.expressions.OperatorType
import elanTele.ir.statements.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class ForLoopTests {

    @Test
    fun `Uniterable range (1, 2)`() {
        Assertions.assertThrows(UniterableRangeException::class.java) {
            ForStatement(
                    beginValueExpression = "1".toExpr(),
                    endValueExpression = 10.toExpr(),
                    forBody = StatementsSequence(emptyList())
            ).execute(Context())
        }
    }

    @Test
    fun `Immediate end (1, 3, 4)`() {
        Assertions.assertEquals(
                ForStatement(
                        beginValueExpression = 10.toExpr(),
                        endValueExpression = 1.toExpr(),
                        forBody = statementSequence()
                ).execute(Context()),
                null
        )
    }

    @Test
    fun `Immediate return (1, 3, 5, 7, 8)`() {
        Assertions.assertEquals(
                10.toVal(),
                ForStatement(
                        beginValueExpression = 1.toExpr(),
                        endValueExpression = 100.toExpr(),
                        forBody = statementSequence(ReturnStatement(10.toExpr()))
                ).execute(Context())
        )
    }

    @Test
    fun `Immediate return variable (1, 3, 5, 6 , 7, 8)`() {
        Assertions.assertEquals(
                1.toVal(),
                ForStatement(
                        variable = "i",
                        beginValueExpression = 1.toExpr(),
                        endValueExpression = 100.toExpr(),
                        forBody = statementSequence(ReturnStatement("i".toVarExpr()))
                ).execute(Context())
        )
    }

    @Test
    fun `Loops (1, 3, 5, 7, 9, 4)`() {
        val context = buildContext("a" to 0.toVal())
        ForStatement(
                beginValueExpression = 1.toExpr(),
                endValueExpression = 10.toExpr(),
                forBody = statementSequence(
                        // Increment variable `a`
                        AssignmentStatement(
                                "a".toVarRef(),
                                BinaryExpression("a".toVarExpr(), 1.toExpr(), OperatorType.ADD)
                        )
                )
        ).execute(context)

        Assertions.assertEquals(
                10.toVal(),
                context.getValue("a")
        )
    }


    @Test
    fun `Loops with return (1, 3, 5, 7, 9, 5, 7, 8)`() {
        Assertions.assertEquals(
                5.toVal(),
                ForStatement(
                        variable = "i",
                        beginValueExpression = 1.toExpr(),
                        endValueExpression = 100.toExpr(),
                        forBody = statementSequence(
                                // if i == 5 return i
                                IfStatement(
                                        BinaryExpression("i".toVarExpr(), 5.toExpr(), OperatorType.EQUAL),
                                        statementSequence(ReturnStatement("i".toVarExpr()))
                                )
                        )
                ).execute(Context())
        )
    }


}
