import java.io.File

// You can experiment here, it won’t be checked

fun main(args: Array<String>) {
    /*val file = File("C:\\Users\\bfeya\\IdeaProjects\\ASCII Text Signature\\Topics\\Lambda expressions\\Theory\\src\\words_with_numbers.txt")
    var counter = 0
    file.forEachLine {
        try {
            it.toInt()
            counter++
        } catch (e: NumberFormatException) { }
    }
    println(counter)*/

    /*
    val file = File("C:\\Users\\bfeya\\IdeaProjects\\ASCII Text Signature\\Topics\\Lambda expressions\\Theory\\src\\words_sequence.txt")
    var longest = 0
    file.forEachLine {
        if (it.length > longest)
            longest = it.length
    }
    println(longest)
     */

    val file = File("C:\\Users\\bfeya\\IdeaProjects\\ASCII Text Signature\\Topics\\Lambda expressions\\Theory\\src\\text.txt")
    var text = file.readText()
    println(text.count { it == ' ' } + 1)
}
