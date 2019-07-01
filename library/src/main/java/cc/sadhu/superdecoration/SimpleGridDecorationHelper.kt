package cc.sadhu.superdecoration

import android.graphics.Rect
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cc.sadhu.neteasecloudmusic.view.derocation.SpaceInfoData

abstract class SimpleGridDecorationHelper(
    private val mInfo: SpaceInfoData,
    private val mLayoutManager: LinearLayoutManager
) : OrientationDecorationHelper {
    private val spanCount = (mLayoutManager as GridLayoutManager).spanCount
    private val spanSizeLookup = (mLayoutManager as GridLayoutManager).spanSizeLookup
    private val mEachSpace = (mInfo.secondaryEdgeSpace * 2 + mInfo.secondarySpace * (spanCount - 1)) / spanCount

    init {
        // 高版本库支持缓存 spanSizeLookup.isSpanGroupIndexCacheEnabled = true
        spanSizeLookup.isSpanIndexCacheEnabled = true

    }

    override fun setOffset(outRect: Rect, childAdapterPosition: Int, itemCount: Int) {
        // 一种是vertical
        val diff =
            ((mEachSpace - mInfo.secondaryEdgeSpace) - mInfo.secondaryEdgeSpace) / (spanCount - 1)  // d = (An-A1)/(n-1)
        val column = spanSizeLookup.getSpanIndex(childAdapterPosition, spanCount)  // 列数从0开始计数
        val leftOrTop = (column + 1 - 1) * diff + mInfo.secondaryEdgeSpace // left = (column-1)*diff+A1
        val rightOrBottom = mEachSpace - leftOrTop
        val totalGroup = spanSizeLookup.getSpanGroupIndex(itemCount - 1, spanCount)
        val currentGroup = spanSizeLookup.getSpanGroupIndex(childAdapterPosition, spanCount)
        val isSingleSpan = spanCount == spanSizeLookup.getSpanSize(childAdapterPosition)
        if (mLayoutManager.orientation == RecyclerView.VERTICAL) {
            // 如果就只有一行
            if (totalGroup == 1 && currentGroup == totalGroup) {
                outRect.set(
                    leftOrTop.toInt(),
                    mInfo.primaryEdgeSpace.toInt(),
                    rightOrBottom.toInt(),
                    mInfo.primaryEdgeSpace.toInt()
                )
            } else {
                when (currentGroup) {
                    0 -> // 首行要设置marginTop
                        outRect.set(
                            if (isSingleSpan) mInfo.secondaryEdgeSpace.toInt() else leftOrTop.toInt(),
                            if (mLayoutManager.reverseLayout) (mInfo.primarySpace / 2).toInt() else mInfo.primaryEdgeSpace.toInt(),
                            if (isSingleSpan) mInfo.secondaryEdgeSpace.toInt() else rightOrBottom.toInt(),
                            if (mLayoutManager.reverseLayout) mInfo.primaryEdgeSpace.toInt() else (mInfo.primarySpace / 2).toInt()
                        )
                    totalGroup -> // 最后一行要设置marginBottom
                        outRect.set(
                            if (isSingleSpan) mInfo.secondaryEdgeSpace.toInt() else leftOrTop.toInt(),
                            if (mLayoutManager.reverseLayout) mInfo.primaryEdgeSpace.toInt() else (mInfo.primarySpace / 2).toInt(),
                            if (isSingleSpan) mInfo.secondaryEdgeSpace.toInt() else rightOrBottom.toInt(),
                            if (mLayoutManager.reverseLayout) (mInfo.primarySpace / 2).toInt() else mInfo.primaryEdgeSpace.toInt()
                        )
                    else ->
                        outRect.set(
                            if (isSingleSpan) mInfo.secondaryEdgeSpace.toInt() else leftOrTop.toInt(),
                            (mInfo.primarySpace / 2).toInt(),
                            if (isSingleSpan) mInfo.secondaryEdgeSpace.toInt() else rightOrBottom.toInt(),
                            (mInfo.primarySpace / 2).toInt()
                        )
                }
            }
        } else if (mLayoutManager.orientation == RecyclerView.HORIZONTAL) {
            // 如果就只有一列
            if (totalGroup == 1 && currentGroup == totalGroup) {
                outRect.set(
                    leftOrTop.toInt(),
                    mInfo.primaryEdgeSpace.toInt(),
                    rightOrBottom.toInt(),
                    mInfo.primaryEdgeSpace.toInt()
                )
            } else {
                when (currentGroup) {
                    0 -> // 守列要设置marginLeft
                        outRect.set(
                            if (mLayoutManager.reverseLayout) (mInfo.primarySpace / 2).toInt() else mInfo.primaryEdgeSpace.toInt(),
                            if (isSingleSpan) mInfo.secondaryEdgeSpace.toInt() else leftOrTop.toInt(),
                            if (mLayoutManager.reverseLayout) mInfo.primaryEdgeSpace.toInt() else (mInfo.primarySpace / 2).toInt(),
                            if (isSingleSpan) mInfo.secondaryEdgeSpace.toInt() else rightOrBottom.toInt()
                        )
                    totalGroup -> // 最后一列要设置marginRight
                        outRect.set(
                            if (mLayoutManager.reverseLayout) mInfo.primaryEdgeSpace.toInt() else (mInfo.primarySpace / 2).toInt(),
                            if (isSingleSpan) mInfo.secondaryEdgeSpace.toInt() else leftOrTop.toInt(),
                            if (mLayoutManager.reverseLayout) (mInfo.primarySpace / 2).toInt() else mInfo.primaryEdgeSpace.toInt(),
                            if (isSingleSpan) mInfo.secondaryEdgeSpace.toInt() else rightOrBottom.toInt()
                        )
                    else -> outRect.set(
                        (mInfo.primarySpace / 2).toInt(),
                        if (isSingleSpan) mInfo.secondaryEdgeSpace.toInt() else leftOrTop.toInt(),
                        (mInfo.primarySpace / 2).toInt(),
                        if (isSingleSpan) mInfo.secondaryEdgeSpace.toInt() else rightOrBottom.toInt()
                    )
                }
            }
        }
    }
}