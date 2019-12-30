package cc.sadhu.sample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cc.sadhu.sample.R
import cc.sadhu.sample.bean.Item

class SimpleAdapter(val list: List<Item>, val isHorizontal: Boolean = false) :
    RecyclerView.Adapter<SimpleHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleHolder {
        return SimpleHolder(
            LayoutInflater.from(parent.context).inflate(
                if (isHorizontal) R.layout.item_simple_horizontal else R.layout.item_simple,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: SimpleHolder, position: Int) {
        holder.bind(list[position])
    }

}