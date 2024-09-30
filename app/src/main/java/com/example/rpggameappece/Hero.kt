package com.example.rpggameappece

// Hero class inheriting from Character
class Hero(name: String, hp: Int, def: Int, atk: Int) : Character(name, hp, def, atk) {

    var maxHp = hp  // Hero's maximum HP
    var experience = 0  // Hero's experience points
    var level = 1       // Hero's current level

    // Method to gain experience and check for level up
    fun gainExperience(points: Int) {
        experience += points
        println("$name gains $points experience points.")

        // Level up if experience reaches 100
        if (experience >= 100) {
            levelUp()  // Call level up when enough experience is gained
        }
    }

    // Make levelUp method public so it can be called from MainActivity
    fun levelUp() {
        level++
        experience = 0  // Reset experience after leveling up
        maxHp += 10     // Increase maximum health by 10
        hp = maxHp      // Fully heal the hero on level up
        atk += 5        // Increase attack by 5
        def += 3        // Increase defense by 3
        println("$name has leveled up to Level $level! Fully healed and stats increased!")
    }

    // Method to heal the hero
    fun heal(amount: Int) {
        if (hp < maxHp) {
            hp += amount
            if (hp > maxHp) {
                hp = maxHp  // Ensure HP doesn't exceed maxHp
            }
            println("$name heals for $amount HP, current HP is $hp.")
        } else {
            println("$name is already at full health!")
        }
    }
}
