package cc.sadhu.sample.bean

import androidx.annotation.DrawableRes

data class Item(@DrawableRes val resId: Int, val name: String)