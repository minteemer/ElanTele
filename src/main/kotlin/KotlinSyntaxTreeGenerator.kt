import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.RuleContext
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.tree.ParseTree
import java.nio.file.Path

object KotlinSyntaxTreeGenerator {

    /**
     * Parses file with given [filePath] and creates Abstract Syntax Tree
     * @return [Map] that contains created AST
     */
    fun generateTree(filePath: Path): Map<String, Any>? {
        val lexer = DLangLexer(CharStreams.fromPath(filePath))
        val parser = DLangParser(CommonTokenStream(lexer))

        return parser.kotlinFile().toMap()
    }

    /** Traverse through the tree and create [Map] out of it */
    private fun ParseTree.toMap(): Map<String, Any> = payload.let { node ->
        when (node) {
            is Token -> mapOf(node.getName() to node.text) // leaf node
            is RuleContext -> (0 until childCount)
                    .map { getChild(it).toMap() } // map children IDs to their subtrees
                    .let { mapOf(node.getName() to if (it.size == 1) it[0] else it) }

            else -> throw ClassCastException("Unknown tree element")
        }
    }

    /** @return name of the token */
    private fun Token.getName() = DLangLexer.VOCABULARY.getSymbolicName(type)

    /** @return name of the rule */
    private fun RuleContext.getName() = DLangParser.ruleNames[ruleIndex]
}

