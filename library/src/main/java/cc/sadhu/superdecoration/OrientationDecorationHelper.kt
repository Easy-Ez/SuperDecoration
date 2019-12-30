package cc.sadhu.superdecoration

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

interface OrientationDecorationHelper {

    fun needOffset(view: View, parent: RecyclerView): Boolean

    fun setOffset(outRect: Rect, childAdapterPosition: Int, itemCount: Int)

    fun drawDivide(c: Canvas, builder: SuperOffsetDecoration.Builder, parent: RecyclerView, state: RecyclerView.State)

    companion object {
        fun createOrientationDecorationHelper(
            builder: SuperOffsetDecoration.Builder,
            layoutManager: LinearLayoutManager
        ): OrientationDecorationHelper {
            return when (layoutManager) {
                // 一种是GridLayoutManager
                is GridLayoutManager -> createGridHelper(
                    builder,
                    layoutManager
                )
                // 一种是LinearLayoutManager,
                else -> createLinearHelper(
                    builder,
                    layoutManager
                )
            }
        }

        private fun createLinearHelper(
            builder: SuperOffsetDecoration.Builder,
            layoutManager: LinearLayoutManager
        ): OrientationDecorationHelper {
            return object : SimpleLinearDecorationHelper(builder, layoutManager) {
                override fun needOffset(view: View, parent: RecyclerView): Boolean {
                    return true
                }
            }
        }

        private fun createGridHelper(
            builder: SuperOffsetDecoration.Builder,
            layoutManager: LinearLayoutManager
        ): OrientationDecorationHelper {
            return object : SimpleGridDecorationHelper(builder, layoutManager) {
                override fun needOffset(view: View, parent: RecyclerView): Boolean {
                    return true
                }
            }
        }

    }
}

