package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.util.*


class WordLadderII : TestCaseProblem<WordLadderIIParams, List<List<String>>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = WordLadderII().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<WordLadderIIParams, List<List<String>>>> = arrayOf(
        TestCase(
            input = WordLadderIIParams(
                beginWord = "hit",
                endWord = "cog",
                wordList = listOf("hot", "dot", "dog", "lot", "log", "cog")
            ),
            output = listOf(
                listOf("hit", "hot", "dot", "dog", "cog"),
                listOf("hit", "hot", "lot", "log", "cog")
            )
        ),
        TestCase(
            input = WordLadderIIParams(
                beginWord = "hit",
                endWord = "cog",
                wordList = listOf("hot", "dot", "dog", "lot", "log")
            ),
            output = emptyList()
        ),
        TestCase(
            input = WordLadderIIParams(
                beginWord = "aaaaa",
                endWord = "ggggg",
                wordList = listOf(
                    "aaaaa",
                    "caaaa",
                    "cbaaa",
                    "daaaa",
                    "dbaaa",
                    "eaaaa",
                    "ebaaa",
                    "faaaa",
                    "fbaaa",
                    "gaaaa",
                    "gbaaa",
                    "haaaa",
                    "hbaaa",
                    "iaaaa",
                    "ibaaa",
                    "jaaaa",
                    "jbaaa",
                    "kaaaa",
                    "kbaaa",
                    "laaaa",
                    "lbaaa",
                    "maaaa",
                    "mbaaa",
                    "naaaa",
                    "nbaaa",
                    "oaaaa",
                    "obaaa",
                    "paaaa",
                    "pbaaa",
                    "bbaaa",
                    "bbcaa",
                    "bbcba",
                    "bbdaa",
                    "bbdba",
                    "bbeaa",
                    "bbeba",
                    "bbfaa",
                    "bbfba",
                    "bbgaa",
                    "bbgba",
                    "bbhaa",
                    "bbhba",
                    "bbiaa",
                    "bbiba",
                    "bbjaa",
                    "bbjba",
                    "bbkaa",
                    "bbkba",
                    "bblaa",
                    "bblba",
                    "bbmaa",
                    "bbmba",
                    "bbnaa",
                    "bbnba",
                    "bboaa",
                    "bboba",
                    "bbpaa",
                    "bbpba",
                    "bbbba",
                    "abbba",
                    "acbba",
                    "dbbba",
                    "dcbba",
                    "ebbba",
                    "ecbba",
                    "fbbba",
                    "fcbba",
                    "gbbba",
                    "gcbba",
                    "hbbba",
                    "hcbba",
                    "ibbba",
                    "icbba",
                    "jbbba",
                    "jcbba",
                    "kbbba",
                    "kcbba",
                    "lbbba",
                    "lcbba",
                    "mbbba",
                    "mcbba",
                    "nbbba",
                    "ncbba",
                    "obbba",
                    "ocbba",
                    "pbbba",
                    "pcbba",
                    "ccbba",
                    "ccaba",
                    "ccaca",
                    "ccdba",
                    "ccdca",
                    "cceba",
                    "cceca",
                    "ccfba",
                    "ccfca",
                    "ccgba",
                    "ccgca",
                    "cchba",
                    "cchca",
                    "cciba",
                    "ccica",
                    "ccjba",
                    "ccjca",
                    "cckba",
                    "cckca",
                    "cclba",
                    "cclca",
                    "ccmba",
                    "ccmca",
                    "ccnba",
                    "ccnca",
                    "ccoba",
                    "ccoca",
                    "ccpba",
                    "ccpca",
                    "cccca",
                    "accca",
                    "adcca",
                    "bccca",
                    "bdcca",
                    "eccca",
                    "edcca",
                    "fccca",
                    "fdcca",
                    "gccca",
                    "gdcca",
                    "hccca",
                    "hdcca",
                    "iccca",
                    "idcca",
                    "jccca",
                    "jdcca",
                    "kccca",
                    "kdcca",
                    "lccca",
                    "ldcca",
                    "mccca",
                    "mdcca",
                    "nccca",
                    "ndcca",
                    "occca",
                    "odcca",
                    "pccca",
                    "pdcca",
                    "ddcca",
                    "ddaca",
                    "ddada",
                    "ddbca",
                    "ddbda",
                    "ddeca",
                    "ddeda",
                    "ddfca",
                    "ddfda",
                    "ddgca",
                    "ddgda",
                    "ddhca",
                    "ddhda",
                    "ddica",
                    "ddida",
                    "ddjca",
                    "ddjda",
                    "ddkca",
                    "ddkda",
                    "ddlca",
                    "ddlda",
                    "ddmca",
                    "ddmda",
                    "ddnca",
                    "ddnda",
                    "ddoca",
                    "ddoda",
                    "ddpca",
                    "ddpda",
                    "dddda",
                    "addda",
                    "aedda",
                    "bddda",
                    "bedda",
                    "cddda",
                    "cedda",
                    "fddda",
                    "fedda",
                    "gddda",
                    "gedda",
                    "hddda",
                    "hedda",
                    "iddda",
                    "iedda",
                    "jddda",
                    "jedda",
                    "kddda",
                    "kedda",
                    "lddda",
                    "ledda",
                    "mddda",
                    "medda",
                    "nddda",
                    "nedda",
                    "oddda",
                    "oedda",
                    "pddda",
                    "pedda",
                    "eedda",
                    "eeada",
                    "eeaea",
                    "eebda",
                    "eebea",
                    "eecda",
                    "eecea",
                    "eefda",
                    "eefea",
                    "eegda",
                    "eegea",
                    "eehda",
                    "eehea",
                    "eeida",
                    "eeiea",
                    "eejda",
                    "eejea",
                    "eekda",
                    "eekea",
                    "eelda",
                    "eelea",
                    "eemda",
                    "eemea",
                    "eenda",
                    "eenea",
                    "eeoda",
                    "eeoea",
                    "eepda",
                    "eepea",
                    "eeeea",
                    "ggggg",
                    "agggg",
                    "ahggg",
                    "bgggg",
                    "bhggg",
                    "cgggg",
                    "chggg",
                    "dgggg",
                    "dhggg",
                    "egggg",
                    "ehggg",
                    "fgggg",
                    "fhggg",
                    "igggg",
                    "ihggg",
                    "jgggg",
                    "jhggg",
                    "kgggg",
                    "khggg",
                    "lgggg",
                    "lhggg",
                    "mgggg",
                    "mhggg",
                    "ngggg",
                    "nhggg",
                    "ogggg",
                    "ohggg",
                    "pgggg",
                    "phggg",
                    "hhggg",
                    "hhagg",
                    "hhahg",
                    "hhbgg",
                    "hhbhg",
                    "hhcgg",
                    "hhchg",
                    "hhdgg",
                    "hhdhg",
                    "hhegg",
                    "hhehg",
                    "hhfgg",
                    "hhfhg",
                    "hhigg",
                    "hhihg",
                    "hhjgg",
                    "hhjhg",
                    "hhkgg",
                    "hhkhg",
                    "hhlgg",
                    "hhlhg",
                    "hhmgg",
                    "hhmhg",
                    "hhngg",
                    "hhnhg",
                    "hhogg",
                    "hhohg",
                    "hhpgg",
                    "hhphg",
                    "hhhhg",
                    "ahhhg",
                    "aihhg",
                    "bhhhg",
                    "bihhg",
                    "chhhg",
                    "cihhg",
                    "dhhhg",
                    "dihhg",
                    "ehhhg",
                    "eihhg",
                    "fhhhg",
                    "fihhg",
                    "ghhhg",
                    "gihhg",
                    "jhhhg",
                    "jihhg",
                    "khhhg",
                    "kihhg",
                    "lhhhg",
                    "lihhg",
                    "mhhhg",
                    "mihhg",
                    "nhhhg",
                    "nihhg",
                    "ohhhg",
                    "oihhg",
                    "phhhg",
                    "pihhg",
                    "iihhg",
                    "iiahg",
                    "iiaig",
                    "iibhg",
                    "iibig",
                    "iichg",
                    "iicig",
                    "iidhg",
                    "iidig",
                    "iiehg",
                    "iieig",
                    "iifhg",
                    "iifig",
                    "iighg",
                    "iigig",
                    "iijhg",
                    "iijig",
                    "iikhg",
                    "iikig",
                    "iilhg",
                    "iilig",
                    "iimhg",
                    "iimig",
                    "iinhg",
                    "iinig",
                    "iiohg",
                    "iioig",
                    "iiphg",
                    "iipig",
                    "iiiig",
                    "aiiig",
                    "ajiig",
                    "biiig",
                    "bjiig",
                    "ciiig",
                    "cjiig",
                    "diiig",
                    "djiig",
                    "eiiig",
                    "ejiig",
                    "fiiig",
                    "fjiig",
                    "giiig",
                    "gjiig",
                    "hiiig",
                    "hjiig",
                    "kiiig",
                    "kjiig",
                    "liiig",
                    "ljiig",
                    "miiig",
                    "mjiig",
                    "niiig",
                    "njiig",
                    "oiiig",
                    "ojiig",
                    "piiig",
                    "pjiig",
                    "jjiig",
                    "jjaig",
                    "jjajg",
                    "jjbig",
                    "jjbjg",
                    "jjcig",
                    "jjcjg",
                    "jjdig",
                    "jjdjg",
                    "jjeig",
                    "jjejg",
                    "jjfig",
                    "jjfjg",
                    "jjgig",
                    "jjgjg",
                    "jjhig",
                    "jjhjg",
                    "jjkig",
                    "jjkjg",
                    "jjlig",
                    "jjljg",
                    "jjmig",
                    "jjmjg",
                    "jjnig",
                    "jjnjg",
                    "jjoig",
                    "jjojg",
                    "jjpig",
                    "jjpjg",
                    "jjjjg",
                    "ajjjg",
                    "akjjg",
                    "bjjjg",
                    "bkjjg",
                    "cjjjg",
                    "ckjjg",
                    "djjjg",
                    "dkjjg",
                    "ejjjg",
                    "ekjjg",
                    "fjjjg",
                    "fkjjg",
                    "gjjjg",
                    "gkjjg",
                    "hjjjg",
                    "hkjjg",
                    "ijjjg",
                    "ikjjg",
                    "ljjjg",
                    "lkjjg",
                    "mjjjg",
                    "mkjjg",
                    "njjjg",
                    "nkjjg",
                    "ojjjg",
                    "okjjg",
                    "pjjjg",
                    "pkjjg",
                    "kkjjg",
                    "kkajg",
                    "kkakg",
                    "kkbjg",
                    "kkbkg",
                    "kkcjg",
                    "kkckg",
                    "kkdjg",
                    "kkdkg",
                    "kkejg",
                    "kkekg",
                    "kkfjg",
                    "kkfkg",
                    "kkgjg",
                    "kkgkg",
                    "kkhjg",
                    "kkhkg",
                    "kkijg",
                    "kkikg",
                    "kkljg",
                    "kklkg",
                    "kkmjg",
                    "kkmkg",
                    "kknjg",
                    "kknkg",
                    "kkojg",
                    "kkokg",
                    "kkpjg",
                    "kkpkg",
                    "kkkkg",
                    "ggggx",
                    "gggxx",
                    "ggxxx",
                    "gxxxx",
                    "xxxxx",
                    "xxxxy",
                    "xxxyy",
                    "xxyyy",
                    "xyyyy",
                    "yyyyy",
                    "yyyyw",
                    "yyyww",
                    "yywww",
                    "ywwww",
                    "wwwww",
                    "wwvww",
                    "wvvww",
                    "vvvww",
                    "vvvwz",
                    "avvwz",
                    "aavwz",
                    "aaawz",
                    "aaaaz"
                )
            ),
            output = listOf(
                listOf(
                    "aaaaa",
                    "aaaaz",
                    "aaawz",
                    "aavwz",
                    "avvwz",
                    "vvvwz",
                    "vvvww",
                    "wvvww",
                    "wwvww",
                    "wwwww",
                    "ywwww",
                    "yywww",
                    "yyyww",
                    "yyyyw",
                    "yyyyy",
                    "xyyyy",
                    "xxyyy",
                    "xxxyy",
                    "xxxxy",
                    "xxxxx",
                    "gxxxx",
                    "ggxxx",
                    "gggxx",
                    "ggggx",
                    "ggggg"
                )
            )
        )
    )

    override fun solve(testCaseInput: WordLadderIIParams): List<List<String>> {
        return ladderLength(testCaseInput.beginWord, testCaseInput.endWord, testCaseInput.wordList)
    }

    // optimised for LeetCode
    private fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): List<List<String>> {
        val n = beginWord.length
        val wordSet = wordList.toHashSet()

        if (endWord !in wordSet) return emptyList()

        val queue: Queue<String> = LinkedList()
        queue.add(beginWord)
        wordSet.remove(beginWord)

        val wordToLevelMap = hashMapOf<String, Int>()
        wordToLevelMap[beginWord] = 1

        while (queue.isNotEmpty()) {
            val word = queue.poll()
            val level = wordToLevelMap.getValue(word)

            if (word == endWord) break

            val chars = word.toCharArray()
            for (i in 0 until n) {
                val originalChar = chars[i]
                for (ch in 'a'..'z') {
                    chars[i] = ch
                    val newWord = String(chars)
                    if (newWord in wordSet) {
                        queue.add(newWord)
                        wordSet.remove(newWord)
                        wordToLevelMap[newWord] = level + 1
                    }
                }
                chars[i] = originalChar
            }
        }

        val result = mutableListOf<List<String>>()

        fun dfs(word: String, seq: MutableList<String>) {
            if (word == beginWord) {
                result.add(seq.reversed())
                return
            }

            val level = wordToLevelMap.getValue(word)
            val chars = word.toCharArray()

            for (i in 0 until n) {
                val originalChar = chars[i]
                for (ch in 'a'..'z') {
                    chars[i] = ch
                    val newWord = String(chars)
                    if (wordToLevelMap.contains(newWord) && wordToLevelMap.getValue(newWord) + 1 == level) {
                        seq.add(newWord)
                        dfs(newWord, seq)
                        seq.removeLast()
                    }
                }
                chars[i] = originalChar
            }
        }

        if (wordToLevelMap.contains(endWord)) {
            val seq = mutableListOf(endWord)
            dfs(endWord, seq)
        }

        return result
    }

    private fun ladderLengthBfs(beginWord: String, endWord: String, wordList: List<String>): List<List<String>> {
        val n = beginWord.length
        val wordSet = wordList.toHashSet()

        if (endWord !in wordSet) return emptyList()

        val queue: Queue<MutableList<String>> = LinkedList()
        queue.add(mutableListOf(beginWord))
        wordSet.remove(beginWord)

        val result = mutableListOf<List<String>>()
        val usedOnLevel = mutableListOf<String>()

        while (queue.isNotEmpty()) {
            val size = queue.size

            for (x in 0 until size) {
                val currentList = queue.poll()
                val word = currentList.last()

                if (word == endWord) {
                    result.add(currentList.toList())
                }

                val chars = word.toCharArray()
                for (i in 0 until n) {
                    val originalChar = chars[i]
                    for (ch in 'a'..'z') {
                        chars[i] = ch
                        val newWord = String(chars)
                        if (newWord in wordSet) {
                            currentList.add(newWord)
                            usedOnLevel.add(newWord)
                            queue.add(currentList.toMutableList())
                            currentList.removeLast()
                        }
                    }
                    chars[i] = originalChar
                }
            }

            for (w in usedOnLevel) wordSet.remove(w)
            usedOnLevel.clear()
        }

        return result
    }
}

data class WordLadderIIParams(
    val beginWord: String,
    val endWord: String,
    val wordList: List<String>
) {
    override fun toString(): String {
        return """
            
            beginWord: $beginWord
            endWord: $endWord
            wordList: $wordList
        """.trimIndent()
    }
}
