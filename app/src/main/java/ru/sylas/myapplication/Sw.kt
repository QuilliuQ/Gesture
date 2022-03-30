package ru.sylas.myapplication

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import kotlin.math.abs


open class OnSwipeTouchListener(ctx: Context?, private val action: Action) : OnTouchListener {

    var dX = 0f
    var dY = 0f
    var viexX = 0f
    var viewY = 0f
    override fun onTouch(view: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                viexX = view.x
                viewY = view.y
                dX = view.x - event.rawX
                dY = view.y - event.rawY
            }
            MotionEvent.ACTION_MOVE -> {
                view.animate()
                    .x(event.rawX + dX)
                    .setDuration(0)
                    .start()
                Log.d("My","${viexX}${viewY}")
            }

            else -> return false
        }
        return true
    }


}