package com.example.androidmaster.syntax

fun main(){
    mutableList()
}

fun mutableList(){

    val listFriends: MutableList<String> = mutableListOf("Leandro","David","Héctor","Gabriel","Ricardo")
    println(listFriends)
    //To add and remove your list, is like java
    listFriends.add("Samuel")
    listFriends.remove("Ricardo")
    println(listFriends)
    listFriends.add(0,"Ana")
    println(listFriends)


}

fun inmutableList(){
    val listMonth:List<String> = listOf("January", "February","March","April",
        "May","June","July","August",
        "September","October","November","December")

    //The methods filter the months which contains the letter "e"
    val example=listMonth.filter { it.contains("e") }
    println(example)

    //This methods is like a for each of java
    listMonth.forEach { it -> println(it) }
}