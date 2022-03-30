package ru.sylas.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val image: ImageView = findViewById(R.id.imageView)
        image.setOnTouchListener(object : OnSwipeTouchListener(this@MainActivity,object : Action{
            override fun onSwipeRight() {
                super.onSwipeRight()
                Toast.makeText(this@MainActivity, "R", Toast.LENGTH_SHORT).show()
            }

            override fun onSwipeLeft() {
                super.onSwipeLeft()
                Toast.makeText(this@MainActivity, "L", Toast.LENGTH_SHORT).show()
            }

            override fun onSwipeTop() {
                super.onSwipeTop()
                Toast.makeText(this@MainActivity, "T", Toast.LENGTH_SHORT).show()
            }

            override fun onSwipeBottom() {
                super.onSwipeBottom()
                Toast.makeText(this@MainActivity, "B", Toast.LENGTH_SHORT).show()
            }
        }){})
    }
}