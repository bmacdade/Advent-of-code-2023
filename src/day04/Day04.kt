package day04

import println
import readInput
import kotlin.math.pow

fun main() {
    data class Card(val id:Int, val winningNumbers:List<Int>, val numbers:List<Int>)


    fun parseCard(input:String):Card = input.split("|",":").let{parts ->
            return Card(
                parts[0].split(" ").last().toInt(),
                parts[1].split(" ").filter { it.isNotBlank() }.map { it.toInt() },
                parts[2].split(" ").filter { it.isNotBlank() }.map { it.toInt() }
            )
        }


    fun part1(input: List<String>): Int {
        var sum = 0
        input.forEach {line ->
            parseCard(line).let{card ->
                val x = card.numbers.filter { it in card.winningNumbers }.size.toDouble()
                val score = 2.toDouble().pow((x - 1.0)).toInt()
                sum+= score
            }
        }
        sum.println()
        return sum
    }

    data class CardContainer(val card:Card, val matches:Int, var numberOfCopies:Int = 1)


    fun part2(input: List<String>): Int {
        val cardList:MutableList<CardContainer> = mutableListOf()
        input.forEach {line ->
            parseCard(line).let{card ->
                val matches = card.numbers.filter { it in card.winningNumbers }.size
                cardList.add(CardContainer(card, matches))
            }
        }
        cardList.forEachIndexed { index, (card,matches,copies) ->
            for(i in index+1 ..index+matches){
                cardList[i].numberOfCopies += copies
            }
        }
        val answer = cardList.map{it.numberOfCopies}.sum()
        answer.println()
        return answer
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("\\day04\\Day04_test")
//    check(part1(testInput) == 142)
//    part1(testInput)
//    part2(testInput)

    val input = readInput("\\day04\\day04")
//    part1(input).println()
    part2(input).println()
}
