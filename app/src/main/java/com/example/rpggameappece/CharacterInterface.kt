package com.example.rpggameappece

// Interface to define actions for both hero and enemy
interface CharacterInterface {
    fun attack(opponent: Character)
    fun defend()
    fun heal()
}
