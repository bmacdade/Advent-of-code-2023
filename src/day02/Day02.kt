package day02

import println
import readInput

fun main() {

    val limits = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14,
    )
    class Game(inputString:String){
        val gameNumber:Int
        val hands:MutableList<Pair<Int,String>> = mutableListOf()
        var eligible = true
        val maxOfEachColour:MutableMap<String,Int> = mutableMapOf()

        init {
            val data = inputString.split(':',';',',')
            gameNumber = data[0].split(" ")[1].toInt()

            data.drop(1).forEach {
                val hand = it.trimStart(' ').split(' ')
                val pair = Pair(hand[0].toInt(),hand[1])
                hands.add(pair)
                if(limits.getOrDefault(pair.second,0) < pair.first) eligible = false
                if(!maxOfEachColour.containsKey(pair.second) || maxOfEachColour[pair.second]!! < pair.first){
                    maxOfEachColour[pair.second] = pair.first
                }
            }
        }
    }

    fun part1(input: List<String>): Int {
        var sum = 0

        input.forEach {line ->
            Game(line).let{if(it.eligible) sum += it.gameNumber}
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0

        input.forEach {line ->
            Game(line).let {
                sum += it.maxOfEachColour.values.reduce { acc, i -> acc * i }
            }
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("\\day02\\Day02_test")
//    part1(testInput)
//    check(part1(testInput) == 142)
//    part2(testInput)

    val input = readInput("\\day02\\day02")
    part1(input).println()
    part2(input).println()
}
