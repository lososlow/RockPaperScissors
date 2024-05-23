package com.example.rockpaperscissorskokin

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var playerChoice: ImageView
    private lateinit var computerChoice: ImageView
    private var selectedButton: Button? = null

    private var playerSelection: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerChoice = findViewById(R.id.playerChoice)
        computerChoice = findViewById(R.id.computerChoice)

        val buttons = listOf(
            findViewById<Button>(R.id.buttonRock),
            findViewById<Button>(R.id.buttonScissors),
            findViewById<Button>(R.id.buttonPaper),
            findViewById<Button>(R.id.buttonLizard),
            findViewById<Button>(R.id.buttonSpock)
        )

        buttons.forEach { button ->
            button.setOnClickListener {
                playerSelection = buttons.indexOf(button)
                highlightSelection(button)
                updatePlayerChoice()
            }
        }

        findViewById<Button>(R.id.buttonPlay).setOnClickListener {
            if (playerSelection == null) {
                Toast.makeText(this, "Сделайте выбор", Toast.LENGTH_SHORT).show()
            } else {
                playGame()
            }
        }
    }

    private fun highlightSelection(button: Button) {
        selectedButton?.background = ContextCompat.getDrawable(this, R.drawable.button_selector)
        selectedButton = button
        button.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_700))
    }

    private fun updatePlayerChoice() {
        when (playerSelection) {
            0 -> playerChoice.setImageResource(R.drawable.rock)
            1 -> playerChoice.setImageResource(R.drawable.scissors)
            2 -> playerChoice.setImageResource(R.drawable.paper)
            3 -> playerChoice.setImageResource(R.drawable.lizard)
            4 -> playerChoice.setImageResource(R.drawable.spock)
        }
    }

    private fun playGame() {
        val computerSelection = Random.nextInt(5)
        when (computerSelection) {
            0 -> computerChoice.setImageResource(R.drawable.rock)
            1 -> computerChoice.setImageResource(R.drawable.scissors)
            2 -> computerChoice.setImageResource(R.drawable.paper)
            3 -> computerChoice.setImageResource(R.drawable.lizard)
            4 -> computerChoice.setImageResource(R.drawable.spock)
        }

        val result = when (playerSelection) {
            computerSelection -> "Ничья! Попробуйте снова"
            0 -> when (computerSelection) {
                1, 3 -> "Вы победили!"
                else -> "Вы проиграли!"
            }
            1 -> when (computerSelection) {
                2, 3 -> "Вы победили!"
                else -> "Вы проиграли!"
            }
            2 -> when (computerSelection) {
                0, 4 -> "Вы победили!"
                else -> "Вы проиграли!"
            }
            3 -> when (computerSelection) {
                2, 4 -> "Вы победили!"
                else -> "Вы проиграли!"
            }
            4 -> when (computerSelection) {
                0, 1 -> "Вы победили!"
                else -> "Вы проиграли!"
            }
            else -> "Ошибка"
        }

        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
    }
}