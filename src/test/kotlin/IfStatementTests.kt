import elanTele.ir.Context
import elanTele.ir.exceptions.InvalidTypeException
import elanTele.ir.statements.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class IfStatementTests {

    @Test
    fun `Non-boolean condition (1, 2)`() {
        Assertions.assertThrows(InvalidTypeException::class.java) {
            IfStatement(
                    "not a boolean".toExpr(),
                    statementSequence(),
                    statementSequence()
            ).execute(Context())
        }
    }

    @Test
    fun `True condition (1, 3, 4)`() {
        Assertions.assertEquals(
                "If branch".toVal(),
                IfStatement(
                        conditionExpression = true.toExpr(),
                        ifBody = statementSequence(ReturnStatement("If branch".toExpr())),
                        elseBody = statementSequence(ReturnStatement("Else branch".toExpr()))
                ).execute(Context())
        )
    }

    @Test
    fun `False condition (1, 3, 5)`() {
        Assertions.assertEquals(
                "Else branch".toVal(),
                IfStatement(
                        conditionExpression = false.toExpr(),
                        ifBody = statementSequence(ReturnStatement("If branch".toExpr())),
                        elseBody = statementSequence(ReturnStatement("Else branch".toExpr()))
                ).execute(Context())
        )
    }

}
