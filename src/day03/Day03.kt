package day03

import println
import readInput

fun main() {
    var engineGrid:List<MutableList<Pair<Char,Boolean>>> = listOf()

    fun findAttachedParts(y:Int,x:Int):Int{
        for(innerY in -1..1){
            for(innerX in -1..1){
                val ch = engineGrid.getOrNull(y+innerY)?.getOrNull(x+innerX)
                if(ch != null && ch.first.isDigit()){

                }
            }
        }

        return -1
    }

    fun part1(input: List<String>): Int {
        var sum = 0
        input.first().first().isLetterOrDigit()
        engineGrid = input.map { it.map {ch -> ch to false }.toMutableList() }
        engineGrid.forEachIndexed { y, line ->
            line.forEachIndexed { x, (char,attached) ->
                if(char != '.' && !char.isLetterOrDigit()){
                    println("Y: $y, X: $x, char: $char, visited: $attached")
                    findAttachedParts(y,x)
                }
            }
        }
        return sum
    }



    fun part2(input: List<String>): Int {
        var sum = 0
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("\\day03\\Day03_test")
//    check(part1(testInput) == 142)
    part1(testInput)
//    part2(testInput)

//    val input = readInput("\\day03\\day03")
//    part1(input).println()
//    part2(input).println()
}
