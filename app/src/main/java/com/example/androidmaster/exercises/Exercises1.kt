package com.example.androidmaster.exercises

fun main(){
    val morningNotification = 57
    val eveningNotification = 103

    printNotificationSummary(morningNotification)
    printNotificationSummary(eveningNotification)
}

fun printNotificationSummary(numberOfMessages:Int){
    if (numberOfMessages in 1..99){
        println("You have $numberOfMessages notifications")
    }else if (numberOfMessages>=100){
        println("You phone is blowing up! You have 99+ notifications")
    }
}
