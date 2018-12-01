@file:JvmName("Main")

import com.google.gson.GsonBuilder
import java.io.File

const val INPUT_FILE = "in.txt"
const val OUTPUT_FILE = "out.txt"

private val gson = GsonBuilder().setPrettyPrinting().create()

fun main(args: Array<String>) {
    println("Generating tree...")
    val tree = KotlinSyntaxTreeGenerator.generateTree(File(INPUT_FILE).toPath())

    println("Converting to JSON...")
    val output = gson.toJson(tree)

    println("Writing output...")
    File(OUTPUT_FILE).writeText(output)

    println("Complete!")
}