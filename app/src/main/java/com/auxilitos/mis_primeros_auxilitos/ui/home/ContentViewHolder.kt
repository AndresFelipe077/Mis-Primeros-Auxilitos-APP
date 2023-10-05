package com.auxilitos.mis_primeros_auxilitos.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.auxilitos.mis_primeros_auxilitos.R
import com.auxilitos.mis_primeros_auxilitos.client.ApiClient
import com.auxilitos.mis_primeros_auxilitos.databinding.ItemContentBinding
import com.auxilitos.mis_primeros_auxilitos.model.response.ContentResponse
import com.bumptech.glide.Glide

class ContentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding: ItemContentBinding = ItemContentBinding.bind(view)

    fun render(contentModel: ContentResponse) {
        binding.title.text = contentModel.title
        binding.author.text = contentModel.autor

        // Necesitas proporcionar un contexto para Glide, por ejemplo, usando itemView.context
        Glide.with(itemView.context)
            .load(ApiClient.baseUrl + contentModel.url)
            .placeholder(R.drawable.logo)
            .error(R.drawable.error)
            .into(binding.imageView)
    }
}
