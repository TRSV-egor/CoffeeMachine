package com.practicum.mytestapplication
import java.util.*

interface Coffee {

    val name: String
    val waterRecipe: Int
    val beansRecipe: Int
    val milkRecipe: Int

    fun getRecipe(): Array<Int>{
        return arrayOf(waterRecipe, beansRecipe, milkRecipe)
    }
}

object Espresso : Coffee {

    override val name: String = "эспрессо"
    override val waterRecipe = 60
    override val beansRecipe = 10
    override val milkRecipe = 0

}

object Americano : Coffee {

    override val name: String = "американо"
    override val waterRecipe = 120
    override val beansRecipe = 10
    override val milkRecipe = 0

}

object Cappuccino : Coffee {

    override val name: String = "капучино"
    override val waterRecipe = 120
    override val beansRecipe = 20
    override val milkRecipe = 60

}

object Latte : Coffee {

    override val name: String = "латте"
    override val waterRecipe = 240
    override val beansRecipe = 20
    override val milkRecipe = 120

}

class CoffeeMachine {
    private var scanner = Scanner(System.`in`)

    private var water = 240
    private var beans = 20
    private var milk = 120

    private fun getAllCoffies(): Array<Coffee>{
        return arrayOf(Espresso, Americano, Cappuccino, Latte)
    }

    private fun makeRecipe(recipe: Array<Int>): Boolean{
        var status = true
        if (water >= recipe[0]) water -= recipe[0] else {
            println("Недостаточно воды!")
            status = false
        }
        if (beans >= recipe[1]) beans -= recipe[1] else {
            println("Недостаточно кофе!")
            status = false
        }
        if (milk >= recipe[2]) milk -= recipe[2] else {
            println("Недостаточно молока!")
            status = false
        }
        return status
    }



    fun start() {

        val coffieList = getAllCoffies()

        println("Кофемашина готова к работе")

        while (true) {
            println("Введите команду")
            val inputTerminal = scanner.nextLine()
            if (inputTerminal.equals("выключить")) {
                println("До свидания!")
                break
            } else if (inputTerminal.equals("наполнить")) {
                water = 2000
                milk = 1000
                beans = 500
                println("Ингридиенты пополнены")

            } else if (inputTerminal.equals("статус")) {
                println("Ингридиентов осталось:\n$water мл воды\n$milk мл молока\n$beans гр кофе")

            } else if (inputTerminal.equals("кофе")) {
                println("Введите название напитка или \"назад\" для возврата в главное меню")
                var coffeInput = scanner.nextLine().lowercase()
                if (coffeInput.equals("назад")) {
                    continue
                } else {
                    var recipeFound = false
                    for (coffie in coffieList){
                        if (coffie.name.equals(coffeInput.lowercase())){
                            recipeFound = true
                            if (makeRecipe(coffie.getRecipe())){
                                println("$coffeInput готов")

                            }


                            continue
                        }
                    }
                    if (!recipeFound) println("Рецепт не найден!")

                }

            }
        }
    }
}

fun main(){
    val coffeeMachine = CoffeeMachine()
    coffeeMachine.start()
}