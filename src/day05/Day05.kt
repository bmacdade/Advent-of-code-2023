package day05

import kotlinx.coroutines.*
import println
import readInput
import toLongList
import java.lang.Long.parseLong

fun main() {
    data class DestToSourceMap(val dest: LongRange, val source: LongRange)

    val conversionMaps: MutableMap<String, MutableList<DestToSourceMap>> = mutableMapOf()


    fun parseRange(line: String): DestToSourceMap {
        line.split(" ").map(::parseLong).let {
            return DestToSourceMap(
                LongRange(it[0], it[0] + it[2] - 1),
                LongRange(it[1], it[1] + it[2] - 1)
            )
        }
    }

    fun findLocationForSeed(seed:Long):Long{
        var currentLocation = seed
        conversionMaps.forEach { (_, value) ->
            currentLocation = value.firstOrNull { it.source.contains(currentLocation) }?.let {
                val location = it.dest.first + (currentLocation - it.source.first)
                location
            }?: currentLocation
        }
        return currentLocation
    }


    fun part1(input: List<String>): Long {
        val seeds: List<Long> = input[0].removePrefix("seeds:").toLongList()

        var currentSelectedMap = ""
        input.drop(2).forEach {
            if (it.contains("map:")) {
                currentSelectedMap = it.substringBefore(" ").trim()
                conversionMaps[currentSelectedMap] = mutableListOf()
            } else if (it.isNotBlank()) {
                conversionMaps[currentSelectedMap]?.add(parseRange(it))
            }
        }
        conversionMaps.println()

//        seeds.forEach { println("Found location for seed: $it at position: ${findLocationForSeed(it)}") }

        return seeds.map { findLocationForSeed(it) }.minOf { it }
    }

    fun parseSeedRanges(ranges:List<LongRange>):List<Long> = runBlocking {
        ranges.map{range ->
            async(Dispatchers.Default) {
                println("Testing range: $range")
                var lowestValue = Long.MAX_VALUE
                for(value in range){
                    val result = findLocationForSeed(value)
                    if(result < lowestValue) lowestValue = result
                }
                println("LOWEST VALUE: $lowestValue")
                return@async lowestValue
            }
        }.awaitAll()
    }


    fun part2(input: List<String>): Long {
        val seeds = input[0].removePrefix("seeds:").toLongList().chunked(2).map{(f,s) -> LongRange(f,f+s-1)}

        var currentSelectedMap = ""
        input.drop(2).forEach {
            if (it.contains("map:")) {
                currentSelectedMap = it.substringBefore(" ").trim()
                conversionMaps[currentSelectedMap] = mutableListOf()
            } else if (it.isNotBlank()) {
                conversionMaps[currentSelectedMap]?.add(parseRange(it))
            }
        }
        return parseSeedRanges(seeds).minOf { it }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("\\day05\\Day05_test")
//    part1(testInput)
//    part2(testInput).println()

    val input = readInput("\\day05\\day05")
//    part1(input).println()
    part2(input).println()
}
