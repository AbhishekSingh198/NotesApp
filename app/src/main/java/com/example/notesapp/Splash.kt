package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val thread: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(2970)
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    val intent = Intent(this@Splash, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        };thread.start ()
    }
}