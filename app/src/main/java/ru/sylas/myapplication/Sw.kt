package ru.sylas.myapplication

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import kotlin.math.abs
import kotlin.math.log


open class OnSwipeTouchListener(ctx: Context?) : OnTouchListener {
    private val gestureDetector: GestureDetector
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    private inner class GestureListener : SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        //Fling типо свайпа мы его прослушаивам
        override fun onFling(
            e1: MotionEvent,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            var result = false
            try {
                /*
                * e1 действие начала
                * е2 дейтсвие конца
                * ну или положение нашего пальца чтобы было понятнее
                * для начала нам нужно найти разницу между первый и вторым положением, надо нам это для того чтобы отследить именно свайп
                * */
                val diffY = e2.y - e1.y
                val diffX = e2.x - e1.x
                Log.d("My", "$diffX  $diffY")
                // дальше мы сравниваем эти разницы для того чтобы понять на думать о свайпе влево/вправо или вверх/вниз
                if (abs(diffX) > abs(diffY)) {
                    //дальше идет сравнение разницы сначала расстояния тоесть свайп будет считать если ты пальцем проведешь больше 100 единиц по x
                        // и также сравнивается скорость так как свайп действие быстрого смахивания если соответствует то идем дальше
                    if (abs(diffX) > Companion.SWIPE_THRESHOLD && abs(velocityX) > Companion.SWIPE_VELOCITY_THRESHOLD) {
//                        ну здесь элементарно проверяется в какую сторону произошел свайп так как по системе координат х увеличивается вправо

                        if (diffX > 0) {
                            onSwipeRight()
                        } else {
                            onSwipeLeft()
                        }
                        result = true
                    }
                } else if (abs(diffY) > Companion.SWIPE_THRESHOLD && abs(velocityY) > Companion.SWIPE_VELOCITY_THRESHOLD) {
                    // казалось бы а почему, тогда здесь наоборот. А вот незнаю почемуто увеличение по y идет зеркально стандартной оси координат
                    if (diffY > 0) {
                        onSwipeBottom()
                    } else {
                        onSwipeTop()
                    }
                    result = true
                }

            } catch (exception: Exception) {
                exception.printStackTrace()
            }
            return result
        }


    }

    companion object {
        private const val SWIPE_THRESHOLD = 100
        private const val SWIPE_VELOCITY_THRESHOLD = 100
    }

    open fun onSwipeRight() {}
    open fun onSwipeLeft() {}
    open fun onSwipeTop() {}
    open fun onSwipeBottom() {}

    init {
        gestureDetector = GestureDetector(ctx, GestureListener())
    }
}