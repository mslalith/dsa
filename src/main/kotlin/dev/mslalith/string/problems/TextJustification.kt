package dev.mslalith.string.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class TextJustification : TestCaseProblem<Pair<Array<String>, Int>, List<String>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = TextJustification().runForConsole()
    }

    override fun displayOutput(output: List<String>): String = displayStringFromList(output.map { "|$it|" }, true)
    override fun displayExpected(expected: List<String>): String = displayStringFromList(expected.map { "|$it|" }, true)

    override fun getTestCases(): Array<TestCase<Pair<Array<String>, Int>, List<String>>> = arrayOf(
        TestCase(
            input = arrayOf("This", "is", "an", "example", "of", "text", "justification.") to 16,
            output = listOf(
                "This    is    an",
                "example  of text",
                "justification.  "
            )
        ),
        TestCase(
            input = arrayOf("What", "must", "be", "acknowledgment", "shall", "be") to 16,
            output = listOf(
                "What   must   be",
                "acknowledgment  ",
                "shall be        "
            )
        ),
        TestCase(
            input = arrayOf("Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do") to 20,
            output = listOf(
                "Science  is  what we",
                "understand      well",
                "enough to explain to",
                "a  computer.  Art is",
                "everything  else  we",
                "do                  "
            )
        ),
        TestCase(
            input = arrayOf("The", "important", "thing", "is", "not", "to", "stop", "questioning.", "Curiosity", "has", "its", "own", "reason", "for", "existing.") to 17,
            output = listOf(
                "The     important",
                "thing  is  not to",
                "stop questioning.",
                "Curiosity has its",
                "own   reason  for",
                "existing.        "
            )
        ),
        TestCase(
            input = arrayOf(
                "When", "I", "was", "just", "a", "little", "girl", "I", "asked", "my", "mother", "what", "will", "I", "be", "Will", "I", "be", "pretty", "Will", "I", "be", "rich", "Here's", "what", "she",
                "said", "to", "me", "Que", "sera", "sera", "Whatever", "will", "be", "will", "be", "The", "future's", "not", "ours", "to", "see", "Que", "sera", "sera", "When", "I", "was", "just", "a", "child", "in", "school",
                "I", "asked", "my", "teacher", "what", "should", "I", "try", "Should", "I", "paint", "pictures", "Should", "I", "sing", "songs", "This", "was", "her", "wise", "reply", "Que", "sera", "sera", "Whatever", "will", "be",
                "will", "be", "The", "future's", "not", "ours", "to", "see", "Que", "sera", "sera", "When", "I", "grew", "up", "and", "fell", "in", "love", "I", "asked", "my", "sweetheart", "what", "lies", "ahead", "Will", "there",
                "be", "rainbows", "day", "after", "day", "Here's", "what", "my", "sweetheart", "said", "Que", "sera", "sera", "Whatever", "will", "be", "will", "be", "The", "future's", "not", "ours", "to", "see", "Que", "sera", "sera",
                "What", "will", "be,", "will", "be", "Que", "sera", "sera..."
            ) to 60,
            output = listOf(
                "When  I was just a little girl I asked my mother what will I",
                "be  Will  I be pretty Will I be rich Here's what she said to",
                "me  Que  sera sera Whatever will be will be The future's not",
                "ours  to see Que sera sera When I was just a child in school",
                "I asked my teacher what should I try Should I paint pictures",
                "Should  I  sing  songs This was her wise reply Que sera sera",
                "Whatever  will  be  will be The future's not ours to see Que",
                "sera  sera  When  I  grew  up  and  fell  in love I asked my",
                "sweetheart  what lies ahead Will there be rainbows day after",
                "day  Here's  what  my sweetheart said Que sera sera Whatever",
                "will  be  will be The future's not ours to see Que sera sera",
                "What will be, will be Que sera sera...                      "
            )
        )
    )

    override fun solve(testCaseInput: Pair<Array<String>, Int>): List<String> {
        return fullJustify(testCaseInput.first, testCaseInput.second)
    }

    private fun fullJustify(words: Array<String>, maxWidth: Int): List<String> {
        val lines = mutableListOf<String>()
        val sb = StringBuilder()
        val n = words.size

        var i = 0
        var currentWidth = 0
        var wordCount = 0

        while (i < n) {
            val word = words[i]
            if (word.length + currentWidth > maxWidth) {
                justify(sb, wordCount, maxWidth)
                lines.add(sb.toString())
                sb.clear()
                currentWidth = 0
                wordCount = 0
            } else {
                sb.append(word)
                wordCount++
                currentWidth += word.length

                if (i != words.lastIndex && currentWidth + 1 <= maxWidth) {
                    sb.append(' ')
                    currentWidth++
                }

                i++
            }
        }

        if (sb.isNotEmpty()) {
            justify(sb, wordCount, maxWidth, true)
            lines.add(sb.toString())
        }

        return lines
    }

    private fun justify(sb: StringBuilder, wordCount: Int, maxWidth: Int, lastLine: Boolean = false) {
        if (sb.isEmpty()) return

        if (sb.last() == ' ') sb.deleteAt(sb.lastIndex)
        val spaceArray = findSpaces(sb, wordCount, maxWidth, lastLine)

        if (lastLine) {
            repeat(spaceArray.last()) { sb.append(' ') }
            return
        }

        var firstSpace = true
        var i = 0
        var si = 0
        while (i < sb.length) {
            if (sb[i] == ' ') {
                val spaces = spaceArray[si++]
                repeat(spaces) { sb.insert(i, ' ') }
                if (firstSpace) firstSpace = false
                i += spaces
            }
            i++
        }

        if (firstSpace) {
            // no spaces present in sb
            repeat(spaceArray.first()) { sb.append(' ') }
        }
    }

    private fun findSpaces(sb: StringBuilder, wordCount: Int, maxWidth: Int, lastLine: Boolean): IntArray {
        val spaceArray = IntArray(wordCount) { 0 }
        val extraSpaces = maxWidth - sb.length
        if (extraSpaces == 0) return spaceArray

        if (lastLine) {
            spaceArray[spaceArray.lastIndex] = extraSpaces
            return spaceArray
        }

        val spaceCount = wordCount - 1
        if (spaceCount == 0) {
            spaceArray[spaceArray.lastIndex] = extraSpaces
            return spaceArray
        }
        if (spaceCount == 1) {
            spaceArray[0] = extraSpaces
            return spaceArray
        }

        if (extraSpaces < spaceCount) {
            for (i in 0 until extraSpaces) spaceArray[i]++
            return spaceArray
        }

        val remaining = extraSpaces / spaceCount
        if ((remaining * spaceCount) == extraSpaces) {
            for (i in 0 until spaceCount) spaceArray[i] = remaining
            return spaceArray
        }

        for (i in 0 until spaceArray.lastIndex) spaceArray[i] = remaining
        val multiple = extraSpaces / spaceCount
        val extra = extraSpaces - (spaceCount * multiple)

        for (i in 0 until extra) spaceArray[i]++
        return spaceArray
    }
}
