package com.example.androidmaster.syntax

fun main(){
    val numbers= arrayOf(23,56,23,67,12,98,45)

    for (i in numbers.indices){
        println(numbers[i])
    }
    println()
    for (i in numbers){
        println(i)
    }
}


