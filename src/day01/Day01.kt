package day01

import println
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        val interestedIn = "0123456789"
        input.forEach {line ->
            var first:Char? = null
            var last:Char? = null

            line.forEach {
                if(it in interestedIn){
                    if(first == null) first = it
                    last = it
                }

            }
            "$first$last".let{
                if(first != null && it.isNotBlank()) sum += it.toInt()
            }
        }
        println("Result: $sum")
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        val stringToIntMap = mapOf(
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9,
            "zero" to 0,
            "1" to 1,
            "2" to 2,
            "3" to 3,
            "4" to 4,
            "5" to 5,
            "6" to 6,
            "7" to 7,
            "8" to 8,
            "9" to 9,
            "0" to 0,
        )
        input.forEach {line ->
            val first = line.findAnyOf(stringToIntMap.keys)?.second ?: return@forEach
            val last = line.findLastAnyOf(stringToIntMap.keys)?.second ?: return@forEach
            val localTotal = stringToIntMap[first]!! * 10 + stringToIntMap[last]!!
            println("Line: $line, localTotal: $localTotal")
            sum += localTotal
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 142)
//    val testInput = readInput("Day01_test_part2")
//    part2(testInput)

    val input = readInput("day01")
    part1(input).println()
    part2(input).println()
}
