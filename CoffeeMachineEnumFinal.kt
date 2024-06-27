//package com.practicum.mytestapplication
import java.util.*

enum class Coffee(val coffeeName: String, val water: Int, val beans: Int, val milk: Int) {
    ESPRESSO("эспрессо", 60, 10, 0),
    AMERICANO("американо", 120, 10, 0),
    CAPPUCCINO("капучино", 120, 20, 60),
    LATTE("латте", 240, 20, 120);
}

class CoffeeMachine {
    private var scanner = Scanner(System.`in`)

    private var water = 0
    private var beans = 0
    private var milk = 0

    private fun makeRecipe(water: Int, beans: Int, milk: Int): Boolean {
        if (this.water >= water) this.water -= water else {
            println("Недостаточно воды!")
            return false
        }
        if (this.beans >= beans) this.beans -= beans else {
            println("Недостаточно кофе!")
            return false
        }
        if (this.milk >= milk) this.milk -= milk else {
            println("Недостаточно молока!")
            return false
        }
        return true
    }


    fun start() {
        println("Кофемашина готова к работе")

        while (true) {
            println("Введите команду")
            val inputTerminal = scanner.nextLine().lowercase()

            when (inputTerminal) {
                "выключить" -> {
                    println("До свидания!")
                    break
                }

                "наполнить" -> {
                    water = 2000
                    milk = 1000
                    beans = 500
                    println("Ингридиенты пополнены")
                }

                "статус" -> println("Ингридиентов осталось:\n$water мл воды\n$milk мл молока\n$beans гр кофе")
                "кофе" -> {
                    println("Введите название напитка или \"назад\" для возврата в главное меню")
                    val coffeInput = scanner.nextLine().lowercase()

                    when (coffeInput) {
                        "назад" -> continue
                        else -> {
                            var recipeFound = false

                            for (coffee in Coffee.values()) {
                                if (coffee.coffeeName.equals(coffeInput)) {
                                    recipeFound = true
                                    if (makeRecipe(coffee.water, coffee.beans, coffee.milk)) {
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
    }
}

fun main() {
    val coffeeMachine = CoffeeMachine()
    coffeeMachine.start()
}