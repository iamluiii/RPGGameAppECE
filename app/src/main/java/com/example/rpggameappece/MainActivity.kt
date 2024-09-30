package com.example.rpggameappece

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var gameText: TextView
    private lateinit var attackButton: Button
    private lateinit var defendButton: Button
    private lateinit var healButton: Button  // Updated to match XML

    private val hero = Hero("Hero", hp = 100, def = 10, atk = 20)
    private lateinit var enemy: Enemy
    private var enemyCounter = 1  // Track number of enemies defeated to give them unique names

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        gameText = findViewById(R.id.gameText)
        attackButton = findViewById(R.id.attackButton)
        defendButton = findViewById(R.id.defendButton)
        healButton = findViewById(R.id.btnHeal)  // Updated to match XML ID

        // Create the first enemy
        spawnNewEnemy()

        // Player action: Attack
        attackButton.setOnClickListener {
            hero.attack(enemy)
            enemy.hp = enemy.hp.coerceAtLeast(0) // Ensure enemy HP doesn't go negative
            updateGameText("You attacked the enemy!")
            if (!checkGameOver()) {
                enemyTurn()
            }
        }

        // Player action: Defend
        defendButton.setOnClickListener {
            hero.defend()
            updateGameText("You are defending!")
            enemyTurn()
        }

        // Player action: Heal
        healButton.setOnClickListener {
            if (hero.hp < hero.maxHp) {
                hero.heal(20)  // Hero heals by 20 HP
                updateGameText("You healed yourself by 20 HP!")
            } else {
                updateGameText("You're already at full health!")
            }
        }
    }

    // Enemy randomly picks an action (Attack or Defend)
    private fun enemyTurn() {
        if (checkGameOver()) return

        val action = Random.nextInt(1, 3)
        when (action) {
            1 -> {
                enemy.attack(hero)
                updateGameText("${enemy.name} attacks you!")
            }
            2 -> {
                enemy.defend()
                updateGameText("${enemy.name} is defending!")
            }
        }

        // Check if the game is over after enemy's action
        checkGameOver()
    }

    // Check if either the hero or enemy has 0 HP left
    private fun checkGameOver(): Boolean {
        return when {
            hero.hp <= 0 -> {
                updateGameText("${hero.name} has been defeated. Game Over!")
                true
            }
            enemy.hp <= 0 -> {
                updateGameText("${enemy.name} has been defeated. You gain 50 experience!")
                hero.gainExperience(50) // Gain experience for winning
                if (hero.experience >= 100) {
                    hero.levelUp() // Level up only if experience reaches 100
                    updateGameText("Hero has leveled up to Level ${hero.level} and is fully healed!")
                }
                spawnNewEnemy() // Fight a new enemy
                false
            }
            else -> false
        }
    }

    // Spawn a new enemy with a different name and health
    private fun spawnNewEnemy() {
        enemyCounter++  // Increment enemy number
        enemy = Enemy("Enemy $enemyCounter")  // Create a new enemy with a unique name
        updateGameText("A new ${enemy.name} appears!\nHP: ${enemy.hp}")
    }

    // Update the game status text on the screen to reflect HP changes
    private fun updateGameText(status: String) {
        val gameStatus = "Hero: ${hero.hp} HP, Level ${hero.level}, Exp: ${hero.experience}/100\n" +
                "Enemy: ${enemy.hp} HP\n" + status
        gameText.text = gameStatus
    }
}
