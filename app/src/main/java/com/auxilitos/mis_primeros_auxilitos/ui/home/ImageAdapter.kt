import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.widget.ImageView
import android.widget.TextView
import com.auxilitos.mis_primeros_auxilitos.R
import com.auxilitos.mis_primeros_auxilitos.client.ApiClient
import com.auxilitos.mis_primeros_auxilitos.model.response.ContenidoResponse
class ImageAdapter(private val contentList: List<ContenidoResponse>) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val content = contentList[position]

        // Configurar las vistas en tu ViewHolder utilizando las propiedades de ContenidoResponse
        holder.titleTextView.text = content.title
        // holder.authorTextView.text = content.autor
        holder.descriptionTextView.text = content.description
        // Configurar la imagen usando Glide o cualquier otra biblioteca de carga de imágenes
        Glide.with(holder.itemView.context)
            .load(ApiClient.baseUrl + content.url)
            .error(R.drawable.error)
            .into(holder.imageView)

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.title)
        val descriptionTextView: TextView = itemView.findViewById(R.id.description)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun getItemCount(): Int {
        return contentList.size
    }
}