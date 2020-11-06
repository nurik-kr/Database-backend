package kg.nurik.databasefirebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kg.nurik.databasefirebase.model.NewsItem
import kotlinx.android.synthetic.main.item_recyclerview.view.*

class RvAdapter : RecyclerView.Adapter<RvAdapter.Viewholder>() {

    private val list = arrayListOf<NewsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview, parent, false)
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
        holder.bind(list[position])
    }

    class Viewholder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(point: NewsItem) {
            itemView.tvTitle.text = point.title
            itemView.tvDesc.text = point.desc

            Picasso.get().load(point.image).into(itemView.itemImage)
        }
    }
}
