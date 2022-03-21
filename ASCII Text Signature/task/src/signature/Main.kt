package signature

import java.io.File
import kotlin.math.max

class Letter(val char: Char, val width: Int, val lines: List<String>)

class Font(source: File, private val spaceWidth: Int) {
    val lines: Int
    private val mapping: HashMap<Char, Letter> = hashMapOf()

    enum class FState {
        PREAMBLE,
        LETTER_DESCRIPTION,
        LETTER_CONTENT
    }

    init {
        var state = FState.PREAMBLE

        var linesPerLetter = 0
        var currentChar = 'a'
        var currentCharWidth = 0
        var letterLines = mutableListOf<String>()
        source.forEachLine {
            when (state) {
                FState.PREAMBLE -> {
                    val (a, b) = it.split(' ').map(String::toInt)
                    linesPerLetter = a
                    state = FState.LETTER_DESCRIPTION
                }
                FState.LETTER_DESCRIPTION -> {
                    val (char, width) = it.split(' ')
                    currentChar = char.first()
                    currentCharWidth = width.toInt()
                    state = FState.LETTER_CONTENT
                    letterLines = mutableListOf<String>()
                }
                FState.LETTER_CONTENT -> {
                    letterLines.add(it)
                    if (letterLines.size == linesPerLetter) {
                        mapping[currentChar] = Letter(currentChar, currentCharWidth, letterLines.toList())
                        state = FState.LETTER_DESCRIPTION
                    }
                }
            }
        }
        lines = linesPerLetter

        mapping[' '] = Letter(' ', spaceWidth, List(lines) { " ".repeat(spaceWidth) })

        //println("Imported ${mapping.size} characters of $lines lines each")
    }

    fun map(char: Char) = mapping[char]!!
}

class Word(value: String, private val font: Font) {
    private val letters: List<Letter> = value.toCharArray().map(font::map)
    val width = letters.sumOf { it.width }

    fun print(lineWidth: Int) {
        val padLeft = (lineWidth - width) / 2
        val padRight = lineWidth - width - padLeft
        for (i in 0 until font.lines)
            println("88  ${" ".repeat(padLeft)}${letters.joinToString("") { it.lines[i] }}${" ".repeat(padRight)}  88")
    }
}

fun main() {
    val roman = Font(File("C:\\Users\\bfeya\\IdeaProjects\\ASCII Text Signature\\ASCII Text Signature\\task\\src\\signature\\roman.txt"), 10)
    val medium = Font(File("C:\\Users\\bfeya\\IdeaProjects\\ASCII Text Signature\\ASCII Text Signature\\task\\src\\signature\\medium.txt"), 5)

    print("Enter name and surname: ")
    val name = readLine()!!
    val formattedName = Word(name, roman)

    print("Enter person's status:")
    val status = readLine()!!
    val formattedStatus = Word(status, medium)

    print(formattedName, formattedStatus)
}

fun print(name: Word, status: Word) {
    val maxWidth = max(name.width, status.width)
    printHorizontalLine(maxWidth)
    name.print(maxWidth)
    status.print(maxWidth)
    printHorizontalLine(maxWidth)
}

fun printHorizontalLine(contentWidth: Int) {
    println("8".repeat(contentWidth + 8))
}