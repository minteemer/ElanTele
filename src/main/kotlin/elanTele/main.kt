@file:JvmName("Main")

package elanTele

const val TATAR_KEYWORD_FLAG = "-t"

fun main(args: Array<String>) {
    val useTatarKeywords = args.contains(TATAR_KEYWORD_FLAG)

    args.lastOrNull { it.first() != '-' }?.let {
        ElanTeleExecutor.executeFile(it, useTatarKeywords)
    } ?: ElanTeleExecutor.runRepl(useTatarKeywords)
}
