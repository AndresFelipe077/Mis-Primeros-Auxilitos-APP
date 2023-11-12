package com.auxilitos.mis_primeros_auxilitos.classesImport

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawingView(context: Context, attrs: AttributeSet) : View(context, attrs) {

  private var paint: Paint = Paint()
  private var path: Path = Path()

  init {
    setupPaint()
  }

  private fun setupPaint() {
    paint.color = Color.BLACK
    paint.isAntiAlias = true
    paint.strokeWidth = 5f
    paint.style = Paint.Style.STROKE
    paint.strokeJoin = Paint.Join.ROUND
    paint.strokeCap = Paint.Cap.ROUND
  }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)
    canvas.drawPath(path, paint)
  }

  @SuppressLint("ClickableViewAccessibility")
  override fun onTouchEvent(event: MotionEvent): Boolean {
    val x = event.x
    val y = event.y

    when (event.action) {
      MotionEvent.ACTION_DOWN -> {
        path.moveTo(x, y)
        return true
      }
      MotionEvent.ACTION_MOVE -> {
        path.lineTo(x, y)
      }
      MotionEvent.ACTION_UP -> {
        // No hacer nada específico en este ejemplo
      }
    }

    // Invalidate para redibujar la vista
    invalidate()
    return true
  }

  // Función para borrar el contenido de la vista
  fun clearDrawing() {
    path.reset()
    invalidate()
  }

}