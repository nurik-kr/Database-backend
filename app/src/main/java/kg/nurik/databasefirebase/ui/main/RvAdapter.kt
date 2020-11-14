package kg.nurik.databasefirebase.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kg.nurik.databasefirebase.R
import kg.nurik.databasefirebase.data.model.model.NewsItem
import kotlinx.android.synthetic.main.item_recyclerview.view.*

class RvAdapter(private val listener: MainActivity) : RecyclerView.Adapter<RvAdapter.Viewholder>() {

    private val list = arrayListOf<NewsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview, parent, false)
        return Viewholder(view)
    }

    fun update(list: List<NewsItem>?) {
        if (list != null) {
            this.list.clear()
            this.list.addAll(list)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bind(list[position], listener)
    }

    class Viewholder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(point: NewsItem, listener: MainActivity) {
            itemView.tvTitle.text = point.title
            itemView.tvDesc.text = point.desc

            Picasso.get().load(point.image).resize(100, 100).centerCrop().into(itemView.itemImage)

            itemView.setOnClickListener {
                listener.clickListeners(point)
                Picasso.get().load(point.image).resize(100, 100).centerCrop().into(itemView.itemImage)
            }
        }
    }
}

interface itemClick {
    fun clickListeners(point: NewsItem)
}