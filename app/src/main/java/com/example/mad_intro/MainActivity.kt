package com.example.mad_intro

fun main() {
    println(welcomeMessage())
    val digitsToGuess = random4Digits()
    //println(digitsToGuess) // delete line before pushing
    do {
        val guessedOrNot = guessingLoop(digitsToGuess)  //return true when guessed
    } while (!guessedOrNot)
}

fun welcomeMessage(): String {
    return "Guess the 4-digit number!\n " +
            "After each guess, you will get a hint to keep guessing: \n " +
            "The first number you get is the number of digits guessed correctly, regardless of the position. \n " +
            "The second number tells you the number of digits guessed at the correct position.\n " +
            "PS: Number does not contain repeating digits."
}

fun random4Digits(): List<Int> {
    return (0..9).shuffled().subList(0, 4)
}

fun input(): List<Int> {
    var inputDigitsList: List<Int>
    try {
        println("Make a guess: ")
        inputDigitsList = readln().map { it.digitToInt() }.subList(0,4) //reads strings, maps it to list of Ints, sublists only first 4 digits in case the user inputs more
    } catch (e: IllegalArgumentException) {
        inputDigitsList = listOf<Int>(0,0,0,0,0) //sends 5x 0s as inputDigits ... not the best solution, I know
        println("Only numbers allowed, guess again.")
    }
    return inputDigitsList //either actual input or "error control" input
}

fun guessingLoop(digitsToGuess: List<Int>): Boolean {
    val usersInput = input()
    var guessed = false; //by default false

    //better error control solution??
    val errorControl = listOf<Int>(0,0,0,0,0)
    if (usersInput == errorControl) { //if the input = errorControl, it means an exception was thrown in input. therefore we need a new input
        guessingLoop(digitsToGuess) //starts again
    }

    var guessedAtCorrectPosition = 0;
    var guessedDigit = 0; //set or re-set to 0 for each input
    digitsToGuess.forEachIndexed { i, value ->
        if (digitsToGuess.contains(usersInput[i])) guessedDigit += 1 // n if digit to guess contains it (in no particular order)
        if (usersInput[i] == value) guessedAtCorrectPosition += 1 // m the number of digits guessed correctly at their correct position.
    }
    println("Hints: $guessedDigit:$guessedAtCorrectPosition") //hints

    if (usersInput == digitsToGuess) {
        println("You guessed it! The number was ${digitsToGuess.joinToString("")}.")
        guessed = true;
    }

    return guessed;
}