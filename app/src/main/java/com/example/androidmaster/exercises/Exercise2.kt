package com.example.androidmaster.exercises

fun main(){
    val child=5
    val adult=28
    val senior=87

    val isMonday=true

    println("The movie ticket price for a person aged $child is \$${ticketPrice(child, isMonday)}")
    println("The movie ticket price for a person aged $adult is \$${ticketPrice(adult, isMonday)}")
    println("The movie ticket price for a person aged $senior is \$${ticketPrice(senior, isMonday)}")
}

fun ticketPrice(age:Int,isMonday:Boolean):Int{
    var price=-1;
    if (age in 1..100){
        if (age<12){
            price=15
        }else if (age<61){
            price=30
            if (isMonday){
                price-=5
            }
        }else if (age<101){
            price=20
        }
    }
    return price
}