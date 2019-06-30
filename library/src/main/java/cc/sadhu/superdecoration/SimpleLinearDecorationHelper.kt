package cc.sadhu.superdecoration

import android.graphics.Rect
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cc.sadhu.neteasecloudmusic.view.derocation.SpaceInfoData

abstract class SimpleLinearDecorationHelper(
    private val mInfo: SpaceInfoData,
    private val mLayoutManager: LinearLayoutManager
) :
    OrientationDecorationHelper {

    override fun setOffset(outRect: Rect, childAdapterPosition: Int, itemCount: Int) {
        if (mLayoutManager.orientation == RecyclerView.VERTICAL) {
            // 一种是vertical
            // 如果就只有一行
            if (itemCount == 1) {
                outRect.set(
                    mInfo.secondaryEdgeSpace.toInt(),
                    mInfo.primaryEdgeSpace.toInt(),
                    mInfo.secondaryEdgeSpace.toInt(),
                    mInfo.primaryEdgeSpace.toInt()
                )
            } else {
                // 首行
                when (childAdapterPosition) {
                    0 -> // 第一个要设置PaddingTop
                        outRect.set(
                            mInfo.secondaryEdgeSpace.toInt(),
                            if (mLayoutManager.reverseLayout) (mInfo.primarySpace / 2).toInt() else mInfo.primaryEdgeSpace.toInt(),
                            mInfo.secondaryEdgeSpace.toInt(),
                            if (mLayoutManager.reverseLayout) mInfo.primaryEdgeSpace.toInt() else (mInfo.primarySpace / 2).toInt()
                        )
                    itemCount - 1 -> // 最后一行要设置PaddingBottom
                        outRect.set(
                            mInfo.secondaryEdgeSpace.toInt(),
                            if (mLayoutManager.reverseLayout) mInfo.primaryEdgeSpace.toInt() else (mInfo.primarySpace / 2).toInt(),
                            mInfo.secondaryEdgeSpace.toInt(),
                            if (mLayoutManager.reverseLayout) (mInfo.primarySpace / 2).toInt() else mInfo.primaryEdgeSpace.toInt()
                        )
                    else -> outRect.set(
                        mInfo.secondaryEdgeSpace.toInt(),
                        (mInfo.primarySpace / 2).toInt(),
                        mInfo.secondaryEdgeSpace.toInt(),
                        (mInfo.primarySpace / 2).toInt()
                    )
                }
            }
        } else if (mLayoutManager.orientation == RecyclerView.HORIZONTAL) {
            //  一种是horizontal
            if (itemCount == 1) {
                outRect.set(
                    mInfo.primaryEdgeSpace.toInt(),
                    mInfo.secondaryEdgeSpace.toInt(),
                    mInfo.primaryEdgeSpace.toInt(),
                    mInfo.secondaryEdgeSpace.toInt()
                )
            } else {
                // 首行
                when (childAdapterPosition) {
                    0 -> // 第一个要设置PaddingLeft
                        outRect.set(
                            if (mLayoutManager.reverseLayout) (mInfo.primarySpace / 2).toInt() else mInfo.primaryEdgeSpace.toInt(),
                            mInfo.secondaryEdgeSpace.toInt(),
                            if (mLayoutManager.reverseLayout) mInfo.primaryEdgeSpace.toInt() else (mInfo.primarySpace / 2).toInt(),
                            mInfo.secondaryEdgeSpace.toInt()
                        )
                    itemCount - 1 -> // 最后一行要设置PaddingRight
                        outRect.set(
                            if (mLayoutManager.reverseLayout) mInfo.primaryEdgeSpace.toInt() else (mInfo.primarySpace / 2).toInt(),
                            mInfo.secondaryEdgeSpace.toInt(),
                            if (mLayoutManager.reverseLayout) (mInfo.primarySpace / 2).toInt() else mInfo.primaryEdgeSpace.toInt(),
                            mInfo.secondaryEdgeSpace.toInt()

                        )
                    else -> outRect.set(
                        (mInfo.primarySpace / 2).toInt(),
                        mInfo.secondaryEdgeSpace.toInt(),
                        (mInfo.primarySpace / 2).toInt(),
                        mInfo.secondaryEdgeSpace.toInt()
                    )
                }
            }
        }
    }

}
