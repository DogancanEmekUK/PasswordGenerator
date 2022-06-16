package com.dogancanemek.passwordgenerator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numericalButton = findViewById<Button>(R.id.numericalButton)
        val alphabeticalButton = findViewById<Button>(R.id.alphabeticalButton)
        val symbolicalButton = findViewById<Button>(R.id.symbolicalButton)
        val mixButton = findViewById<Button>(R.id.mixButton)
        val digit = findViewById<EditText>(R.id.digit)
        val password = findViewById<TextView>(R.id.password)
        val copyButton = findViewById<Button>(R.id.copyButton)
        val clearButton = findViewById<Button>(R.id.clearButton)

        numericalButton.setOnClickListener {
            password.setTextColor(resources.getColor(R.color.white))
            if (digit.text.isEmpty()) {
                Toast.makeText(this, "Please enter a number", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (digit != null) {
                val x = digit.text.toString()
                if (checkInteger(x)) {
                    val digitNumber = x.toInt()
                    password.text = generateNumericalPassword(digitNumber)
                } else {
                    Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_LONG).show()
                }
            }
        }

        alphabeticalButton.setOnClickListener {
            password.setTextColor(resources.getColor(R.color.white))
            if (digit.text.isEmpty()) {
                Toast.makeText(this, "Please enter a number", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (digit != null) {
                val x = digit.text.toString()
                if (checkInteger(x)) {
                    val digitNumber = x.toInt()
                    password.text = generateAlphabeticalPassword(digitNumber)
                } else {
                    Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_LONG).show()
                }
            }
        }

        symbolicalButton.setOnClickListener {
            password.setTextColor(resources.getColor(R.color.white))
            if (digit.text.isEmpty()) {
                Toast.makeText(this, "Please enter a number", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (digit != null) {
                val x = digit.text.toString()
                if (checkInteger(x)) {
                    val digitNumber = x.toInt()
                    password.text = generateSymbolicalPassword(digitNumber)
                } else {
                    Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_LONG).show()
                }
            }
        }

        mixButton.setOnClickListener {
            password.setTextColor(resources.getColor(R.color.white))
            if (digit.text.isEmpty()) {
                Toast.makeText(this, "Please enter a number", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (digit != null) {
                val x = digit.text.toString()
                if (checkInteger(x)) {
                    val digitNumber = x.toInt()
                    password.text = generateMixPassword(digitNumber)
                } else {
                    Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_LONG).show()
                }
            }
        }

        copyButton.setOnClickListener {
            val passwordText = password.text
            copyPassword(passwordText.toString())
        }

        clearButton.setOnClickListener {
            digit.text.clear()
            password.setTextColor(resources.getColor(R.color.hint))
            password.text = getString(R.string.your_password)
        }

    }

    private fun checkInteger(x: String): Boolean {
        var condition = true
        for (i in x.indices) {
            condition = x[i] >= 48.toChar() && x[i] <= 57.toChar()
        }
        return condition
    }

    private fun generateSymbolicalPassword(digitNumber: Int): String {
        val myPasswordChars = mutableListOf<Char>()
        val myListOfLists =
            listOf(
                listOf(33.toChar()..47.toChar()),
                listOf(58.toChar()..64.toChar()),
                listOf(91.toChar()..96.toChar()),
                listOf(123.toChar()..126.toChar())
            )
        var index = 0
        while (index < digitNumber) {
            for (i in 0..digitNumber) {
                val randomList = myListOfLists.random()
                myPasswordChars.add(randomList.random().random())
                index += 1
                if (index == digitNumber) {
                    val myCharArray = myPasswordChars.toCharArray()
                    return String(myCharArray)
                }
            }
        }

        val myCharArray = myPasswordChars.toCharArray()
        return String(myCharArray)
    }

    private fun generateAlphabeticalPassword(digitNumber: Int): String {
        val myPasswordChars = mutableListOf<Char>()
        val myListOfLists =
            listOf(listOf(65.toChar()..90.toChar()), listOf(97.toChar()..122.toChar()))
        var index = 0
        while (index < digitNumber) {
            for (i in 0..digitNumber) {
                val randomList = myListOfLists.random()
                myPasswordChars.add(randomList.random().random())
                index += 1
                if (index == digitNumber) {
                    val myCharArray = myPasswordChars.toCharArray()
                    return String(myCharArray)
                }
            }
        }

        val myCharArray = myPasswordChars.toCharArray()
        return String(myCharArray)
    }

    private fun generateNumericalPassword(digitNumber: Int): String {
        val myPasswordChars = mutableListOf<Char>()
        var index = 0
        while (index < digitNumber) {
            for (i in 0..digitNumber) {
                myPasswordChars.add((48.toChar()..57.toChar()).random())
                index += 1
                if (index == digitNumber) {
                    val myCharArray = myPasswordChars.toCharArray()
                    return String(myCharArray)
                }
            }
        }

        val myCharArray = myPasswordChars.toCharArray()
        return String(myCharArray)
    }

    private fun generateMixPassword(digitNumber: Int): String {
        val myPasswordChars = mutableListOf<Char>()
        var index = 0
        while (index < digitNumber) {
            for (i in 0..digitNumber) {
                myPasswordChars.add((33.toChar()..125.toChar()).random())
                index += 1
                if (index == digitNumber) {
                    val myCharArray = myPasswordChars.toCharArray()
                    return String(myCharArray)
                }
            }
        }

        val myCharArray = myPasswordChars.toCharArray()
        return String(myCharArray)
    }

    private fun copyPassword(passwordText: String) {
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("password", passwordText)
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(this, "Password Copied to Clipboard", Toast.LENGTH_LONG).show()
    }
}