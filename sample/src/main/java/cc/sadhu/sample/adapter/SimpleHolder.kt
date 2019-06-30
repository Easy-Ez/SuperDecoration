package cc.sadhu.sample.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cc.sadhu.sample.bean.Item
import kotlinx.android.synthetic.main.item_simple.view.*

class SimpleHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
    fun bind(item: Item) = with(itemView) {
        mTvDesc.text = item.name
        mImgIcon.setImageResource(item.resId)
    }
}