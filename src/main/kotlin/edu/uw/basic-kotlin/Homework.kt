package edu.uw.basickotlin

class Library {
    // This is just here as a placeholder, to make sure tests run and pass
    // before you add any code
    fun someLibraryMethod(): Boolean {
        return true
    }
}

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(value: Any): String {
    return when (value) {
        "Hello" -> "world"
        "Howdy", "Bonjour" -> "Say what?"
        is String -> "I don't understand"
        0 -> "zero"
        1 -> "one"
        in 2..10 -> "low number"
//        is Number -> "a number"
        else -> "I don't understand"
    }
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(intA: Int, intB: Int): Int {
    return intA + intB
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(intA: Int, intB: Int): Int {
    return intA - intB
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(intA: Int, intB: Int, mathFun: (Int, Int) -> Int): Int {
    return mathFun(intA, intB)
}

// write a class "Person" with first name, last name and age
class Person(val firstName: String, val lastName: String, val age: Int) {
    val debugString: String
        get() = "[Person firstName:$firstName lastName:$lastName age:$age]"
}

// write a class "Money" with amount and currency, and define a convert() method and the "+" operator
class Money(var amount: Int, var currency: String) {
    init {
        require(amount >= 0) { "Amount cannot be negative" }
        require(currency in setOf("USD", "EUR", "CAN", "GBP")) { "Invalid currency" }
    }

    fun convert(targetCurrency: String): Money {
        return when (currency) {
            "USD" -> {
                when (targetCurrency) {
                    "GBP" -> Money(amount / 2, targetCurrency)
                    "EUR" -> Money(amount * 3 / 2, targetCurrency)
                    "CAN" -> Money(amount * 5 / 4, targetCurrency)
                    else -> this // Same currency, no conversion needed
                }
            }
            "GBP" -> {
                when (targetCurrency) {
                    "USD" -> Money(amount * 2, targetCurrency)
                    "EUR" -> Money(amount * 3, targetCurrency)
                    "CAN" -> Money(amount * 5 / 2, targetCurrency)
                    else -> this
                }
            }
            "EUR" -> {
                when (targetCurrency) {
                    "USD" -> Money(amount * 2 / 3, targetCurrency)
                    "GBP" -> Money(amount / 3, targetCurrency)
                    "CAN" -> Money(amount * 5 / 6, targetCurrency)
                    else -> this
                }
            }
            "CAN" -> {
                when (targetCurrency) {
                    "USD" -> Money(amount * 4 / 5, targetCurrency)
                    "GBP" -> Money(amount * 2 / 5, targetCurrency)
                    "EUR" -> Money(amount * 6 / 5, targetCurrency)
                    else -> this
                }
            }
            else -> this // Invalid currency, no conversion possible
        }
    }

    operator fun plus(other: Money): Money {
        val convertedOther = other.convert(this.currency)
        return Money(amount + convertedOther.amount, this.currency)
    }

    override fun toString(): String {
        return "$amount $currency"
    }
}