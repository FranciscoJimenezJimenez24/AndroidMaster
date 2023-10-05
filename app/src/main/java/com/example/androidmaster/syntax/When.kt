package com.example.androidmaster.syntax

fun main(){
    //getMonth(19)
    getGreat(12)
}

fun getResult(value:Any){
    when (value){
        is Int -> value + value
        is String -> print(value)
        is Boolean -> true
    }
}

fun getMonth(month:Int){
    /*
   If you want to put more than one line
   in one of the options, you must to put
   keys in the option that you want to use
     */
    when(month){
        1 -> print("January")
        2 -> print("Febrery")
        3 -> print("March")
        4 -> print("April")
        5 -> print("May")
        6 -> print("June")
        7 -> print("July")
        8 -> print("August")
        9 -> print("September")
        10 -> print("October")
        11 -> print("November")
        12 -> print("December")
        else -> print("That month doesn't exist")
    }
}

fun getGreat(great:Int){
    when(great){
        in 0  ..  4 -> print("Suspense")
        5 -> print("Enough")
        6 -> print("Good")
        in 7 .. 8 -> print("Remarkable")
        9 -> print("Outstanding")
        10 -> print("Honor Roll")
        !in 0 .. 10 -> print("That great doesn't exist")
    }
}