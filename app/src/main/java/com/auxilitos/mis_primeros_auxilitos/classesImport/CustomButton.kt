import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.auxilitos.mis_primeros_auxilitos.R

class CustomButton @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : AppCompatButton(context, attrs, defStyleAttr) {

  private var imageResId: Int = 0
  private var isCellSelected: Boolean = false

  fun setImage(resId: Int) {
    isCellSelected = false  // Restablece el estado a no seleccionado
    imageResId = resId
  }


  init {
    layoutParams = LinearLayout.LayoutParams(150, 150)
    setOnClickListener {
      onCellClicked()
    }

    val drawable: Drawable? = ContextCompat.getDrawable(context, R.drawable.color_button_triki)
    background = drawable
  }

  private fun onCellClicked() {
    if (!isCellSelected) {
      setBackgroundResource(imageResId)
      isCellSelected = true
      // Agrega la lógica de verificación del ganador aquí si es necesario
    } else {
      Toast.makeText(context, "Celda ya seleccionada", Toast.LENGTH_SHORT).show()
    }
  }
}
