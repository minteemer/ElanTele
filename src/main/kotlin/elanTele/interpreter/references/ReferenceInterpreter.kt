package elanTele.interpreter.references


import elanTele.ir.references.Reference
import elanTele.parser.ElanTeleParser
import org.antlr.v4.runtime.tree.ParseTree

object ReferenceInterpreter {

    fun getReference(tree: ParseTree): Reference {
        TODO("Implement array element reference parsing")
//        tree.payload.let { node ->
//            when(node){
//                is ElanTeleParser.
//            }
//        }
    }

}
