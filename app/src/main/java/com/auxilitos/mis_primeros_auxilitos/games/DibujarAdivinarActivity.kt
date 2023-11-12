package com.auxilitos.mis_primeros_auxilitos.games

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.auxilitos.mis_primeros_auxilitos.MainActivity
import com.auxilitos.mis_primeros_auxilitos.R
import com.auxilitos.mis_primeros_auxilitos.classesImport.ToastCustom
import de.hdodenhof.circleimageview.CircleImageView

class DibujarAdivinarActivity : AppCompatActivity() {

  private lateinit var gridLayout: GridLayout
  private lateinit var buttons: Array<Array<Button>>
  private lateinit var btnResetGame: ImageButton
  private lateinit var btnReturnView: CircleImageView
  private val toast = ToastCustom()

  private var currentPlayer = "😊"
  private var board = Array(3) { Array(3) { "" } }

  @SuppressLint("MissingInflatedId")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_dibujar_adivinar)

    btnReturnView = findViewById(R.id.btn_return_view)
    btnReturnView.setOnClickListener {
      startActivity(Intent(this@DibujarAdivinarActivity, MainActivity::class.java))
    }

    btnResetGame = findViewById(R.id.btn_reset_game)
    btnResetGame.setOnClickListener {
      resetGame()
    }

    gridLayout = findViewById(R.id.gridLayout)
    initializeButtons()
  }

  private fun initializeButtons() {
    buttons = Array(3) { row ->
      Array(3) { col ->
        val button = Button(this)
        button.textSize = 40f
        button.setOnClickListener {
          onCellClicked(row, col, button)
        }
        gridLayout.addView(button)
        button
      }
    }
  }

  private fun onCellClicked(row: Int, col: Int, button: Button) {
    if (board[row][col].isEmpty()) {
      board[row][col] = currentPlayer
      button.text = currentPlayer
      checkForWinner(row, col)
      currentPlayer = if (currentPlayer == "\uD83D\uDE0A") "😎" else "\uD83D\uDE0A"
    }
  }

  private fun checkForWinner(row: Int, col: Int) {
    // Check row
    if (board[row][0] == currentPlayer && board[row][1] == currentPlayer && board[row][2] == currentPlayer) {
      announceWinner()
    }
    // Check column
    if (board[0][col] == currentPlayer && board[1][col] == currentPlayer && board[2][col] == currentPlayer) {
      announceWinner()
    }
    // Check diagonals
    if (row == col && board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
      announceWinner()
    }
    if (row + col == 2 && board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
      announceWinner()
    }
  }

  private fun announceWinner() {
    toast.toastSuccess(this@DibujarAdivinarActivity, "Ganador", currentPlayer)
    resetGame()
  }

  private fun resetGame() {
    for (row in 0 until 3) {
      for (col in 0 until 3) {
        board[row][col] = ""
        buttons[row][col].text = ""
      }
    }
    currentPlayer = "\uD83D\uDE0A"
  }
}
