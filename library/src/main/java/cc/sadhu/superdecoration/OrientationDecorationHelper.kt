package cc.sadhu.superdecoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cc.sadhu.neteasecloudmusic.view.derocation.SpaceInfoData

interface OrientationDecorationHelper {

    fun needOffset(outRect: Rect, view: View, parent: RecyclerView): Boolean

    fun setOffset(outRect: Rect, childAdapterPosition: Int, itemCount: Int)

    companion object {
        fun createOrientationDecorationHelper(
            info: SpaceInfoData,
            layoutManager: LinearLayoutManager
        ): OrientationDecorationHelper {
            return when (layoutManager) {
                // 一种是GridLayoutManager
                is GridLayoutManager -> createGridHelper(
                    info,
                    layoutManager
                )
                // 一种是LinearLayoutManager,
                else -> createLinearHelper(
                    info,
                    layoutManager
                )
            }
        }

        private fun createLinearHelper(
            info: SpaceInfoData,
            layoutManager: LinearLayoutManager
        ): OrientationDecorationHelper {
            return object : SimpleLinearDecorationHelper(info, layoutManager) {
                override fun needOffset(outRect: Rect, view: View, parent: RecyclerView): Boolean {
                    return true
                }
            }
        }

        private fun createGridHelper(
            info: SpaceInfoData,
            layoutManager: LinearLayoutManager
        ): OrientationDecorationHelper {
            return object : SimpleGridDecorationHelper(info, layoutManager) {
                override fun needOffset(outRect: Rect, view: View, parent: RecyclerView): Boolean {
                    return true
                }
            }
        }

    }
}

