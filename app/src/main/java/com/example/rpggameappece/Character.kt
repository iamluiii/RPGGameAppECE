package com.example.rpggameappece

// Base Character class
open class Character(
    var name: String,
    var hp: Int,
    var def: Int,
    var atk: Int
) : CharacterInterface {

    var defending: Boolean = false // Tracks if the character is defending

    override fun attack(opponent: Character) {
        // If opponent is defending, reduce damage
        val damage = if (opponent.defending) {
            (atk - opponent.def / 2).coerceAtLeast(0)  // Reduce damage by half the defense
        } else {
            atk
        }
        opponent.hp -= damage  // Deduct damage from the opponent's health
        println("$name attacks ${opponent.name} for $damage damage.")
        opponent.defending = false  // Reset opponent's defending state after attack
    }

    override fun defend() {
        defending = true
        println("$name is defending!")
    }

    override fun heal() {
        // Randomly heal between 10 and 20 HP
        val healAmount = kotlin.random.Random.nextInt(10, 20)
        hp += healAmount
        println("$name heals for $healAmount HP.")
    }
}
