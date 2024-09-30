package com.example.rpggameappece

// Enemy class inheriting from Character, with random stats
class Enemy(name: String) : Character(
    name,
    hp = kotlin.random.Random.nextInt(50, 150),
    def = kotlin.random.Random.nextInt(5, 20),
    atk = kotlin.random.Random.nextInt(10, 25)
)
