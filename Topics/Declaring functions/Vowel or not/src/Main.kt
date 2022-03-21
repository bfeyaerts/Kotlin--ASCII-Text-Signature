// write your function here
fun isVowel(char: Char) : Boolean {
    return char.lowercaseChar() in mutableListOf('a', 'e', 'i', 'o', 'u')//, 'y')
}

fun main() {
    val letter = readLine()!!.first()

    println(isVowel(letter))
}