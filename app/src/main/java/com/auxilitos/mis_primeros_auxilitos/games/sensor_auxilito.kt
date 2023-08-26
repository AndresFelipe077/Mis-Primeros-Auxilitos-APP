package com.auxilitos.mis_primeros_auxilitos.games

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RelativeLayout
import com.auxilitos.mis_primeros_auxilitos.R
import java.util.Random

class sensor_auxilito : AppCompatActivity() {


    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometer: Sensor
    private lateinit var layout: RelativeLayout

    private var maxXOffset: Float = 0f
    private var maxYOffset: Float = 0f

    private val random = Random()

    private val sensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            // No se necesita implementar en este caso
        }

        override fun onSensorChanged(event: SensorEvent?) {
            if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
                // Mover todas las imágenes, incluidas las duplicadas
                for (i in 0 until layout.childCount) {
                    val imageView = layout.getChildAt(i) as? ImageView
                    imageView?.let {
                        val xOffset = it.x - event.values[0]
                        val yOffset = it.y + event.values[1]

                        // Ajustar las coordenadas para que la imagen no se salga de la pantalla
                        val newXOffset = xOffset.coerceIn(0f, maxXOffset)
                        val newYOffset = yOffset.coerceIn(0f, maxYOffset)

                        // Establecer las nuevas coordenadas en la vista
                        it.x = newXOffset
                        it.y = newYOffset
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor_auxilito)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        layout = findViewById(R.id.layout) // Buscar el RelativeLayout en el diseño

        // Obtener las dimensiones de la pantalla
        val display = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
        maxXOffset = display.width.toFloat()
        maxYOffset = display.height.toFloat()

        layout.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                // Crear y configurar la nueva imagen
                val duplicatedImage = ImageView(this)
                duplicatedImage.setImageDrawable(getDrawable(R.drawable.botiquin))
                duplicatedImage.layoutParams = RelativeLayout.LayoutParams(100, 100)

                val newX = random.nextInt(display.width - 100).toFloat()
                val newY = random.nextInt(display.height - 100).toFloat()
                duplicatedImage.x = newX
                duplicatedImage.y = newY

                // Agregar la nueva imagen duplicada al RelativeLayout
                layout.addView(duplicatedImage)
            }
            true
        }

    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(
            sensorEventListener,
            accelerometer,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(sensorEventListener)
    }


}