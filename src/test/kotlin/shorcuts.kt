import elanTele.ir.Context
import elanTele.ir.expressions.ReferenceExpresion
import elanTele.ir.expressions.ValueExpression
import elanTele.ir.references.VariableReference
import elanTele.ir.statements.Statement
import elanTele.ir.statements.StatementsSequence
import elanTele.ir.values.Value
import elanTele.ir.values.classes.BooleanValue
import elanTele.ir.values.classes.IntegerValue
import elanTele.ir.values.classes.RealValue
import elanTele.ir.values.classes.StringValue


// Values
fun Int.toVal() = IntegerValue(this)

fun Double.toVal() = RealValue(this)
fun String.toVal() = StringValue(this)
fun Boolean.toVal() = BooleanValue(this)

// Expressions
fun Int.toExpr() = ValueExpression(toVal())

fun Double.toExpr() = ValueExpression(toVal())
fun String.toExpr() = ValueExpression(toVal())
fun Boolean.toExpr() = ValueExpression(toVal())

// Statements
fun statementSequence(vararg statements: Statement) = StatementsSequence(statements.toList())

// References
fun String.toVarRef() = VariableReference(this)

fun String.toVarExpr() = ReferenceExpresion(toVarRef())

// Context
fun buildContext(vararg nameAndValue: Pair<String, Value>) = Context().apply {
    nameAndValue.forEach { (name, value) -> createLocalVariable(name, value) }
}