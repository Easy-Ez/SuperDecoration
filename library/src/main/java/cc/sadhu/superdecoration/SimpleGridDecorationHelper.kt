package cc.sadhu.superdecoration

import android.graphics.Canvas
import android.graphics.Rect
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class SimpleGridDecorationHelper(
    private val mBuilder: SuperOffsetDecoration.Builder,
    private val mLayoutManager: LinearLayoutManager
) : OrientationDecorationHelper {
    private val spanCount = (mLayoutManager as GridLayoutManager).spanCount
    private val spanSizeLookup = (mLayoutManager as GridLayoutManager).spanSizeLookup
    private val mEachSpace =
        (mBuilder.mCrossAxisEdgeSpace * 2 + mBuilder.mCrossAxisSpace * (spanCount - 1)) / spanCount

    init {
        // 高版本库支持缓存 spanSizeLookup.isSpanGroupIndexCacheEnabled = true
        spanSizeLookup.isSpanIndexCacheEnabled = true

    }

    override fun setOffset(outRect: Rect, childAdapterPosition: Int, itemCount: Int) {
        // 一种是vertical
        val diff =
            ((mEachSpace - mBuilder.mCrossAxisEdgeSpace) - mBuilder.mCrossAxisEdgeSpace) / (spanCount - 1)  // d = (An-A1)/(n-1)
        val column = spanSizeLookup.getSpanIndex(childAdapterPosition, spanCount)  // 列数从0开始计数
        val leftOrTop =
            (column + 1 - 1) * diff + mBuilder.mCrossAxisEdgeSpace // left = (column-1)*diff+A1
        val rightOrBottom = mEachSpace - leftOrTop
        val totalGroup = spanSizeLookup.getSpanGroupIndex(itemCount - 1, spanCount)
        val currentGroup = spanSizeLookup.getSpanGroupIndex(childAdapterPosition, spanCount)
        val isSingleSpan = spanCount == spanSizeLookup.getSpanSize(childAdapterPosition)
        if (mLayoutManager.orientation == RecyclerView.VERTICAL) {
            // 如果就只有一行
            if (totalGroup == 1 && currentGroup == totalGroup) {
                outRect.set(
                    leftOrTop,
                    mBuilder.mMainAxisEdgeSpace,
                    rightOrBottom,
                    mBuilder.mMainAxisEdgeSpace
                )
            } else {
                when (currentGroup) {
                    0 -> // 首行要设置marginTop
                        outRect.set(
                            if (isSingleSpan) mBuilder.mCrossAxisEdgeSpace else leftOrTop,
                            if (mLayoutManager.reverseLayout) mBuilder.mMainAxisSpace else mBuilder.mMainAxisEdgeSpace,
                            if (isSingleSpan) mBuilder.mCrossAxisEdgeSpace else rightOrBottom,
                            if (mLayoutManager.reverseLayout) mBuilder.mMainAxisEdgeSpace else mBuilder.mMainAxisSpace
                        )
                    totalGroup -> // 最后一行要设置marginBottom
                        outRect.set(
                            if (isSingleSpan) mBuilder.mCrossAxisEdgeSpace else leftOrTop,
                            if (mLayoutManager.reverseLayout) mBuilder.mMainAxisEdgeSpace else 0,
                            if (isSingleSpan) mBuilder.mCrossAxisEdgeSpace else rightOrBottom,
                            if (mLayoutManager.reverseLayout) 0 else mBuilder.mMainAxisEdgeSpace
                        )
                    else ->
                        outRect.set(
                            if (isSingleSpan) mBuilder.mCrossAxisEdgeSpace else leftOrTop,
                            if (mLayoutManager.reverseLayout) mBuilder.mMainAxisSpace else 0,
                            if (isSingleSpan) mBuilder.mCrossAxisEdgeSpace else rightOrBottom,
                            if (mLayoutManager.reverseLayout) 0 else mBuilder.mMainAxisSpace
                        )
                }
            }
        } else if (mLayoutManager.orientation == RecyclerView.HORIZONTAL) {
            // 如果就只有一列
            if (totalGroup == 1 && currentGroup == totalGroup) {
                outRect.set(
                    leftOrTop,
                    mBuilder.mMainAxisEdgeSpace,
                    rightOrBottom,
                    mBuilder.mMainAxisEdgeSpace
                )
            } else {
                when (currentGroup) {
                    0 -> // 守列要设置marginLeft
                        outRect.set(
                            if (mLayoutManager.reverseLayout) mBuilder.mMainAxisSpace else mBuilder.mMainAxisEdgeSpace,
                            if (isSingleSpan) mBuilder.mCrossAxisEdgeSpace else leftOrTop,
                            if (mLayoutManager.reverseLayout) mBuilder.mMainAxisEdgeSpace else mBuilder.mMainAxisSpace,
                            if (isSingleSpan) mBuilder.mCrossAxisEdgeSpace else rightOrBottom
                        )
                    totalGroup -> // 最后一列要设置marginRight
                        outRect.set(
                            if (mLayoutManager.reverseLayout) mBuilder.mMainAxisEdgeSpace else 0,
                            if (isSingleSpan) mBuilder.mCrossAxisEdgeSpace else leftOrTop,
                            if (mLayoutManager.reverseLayout) 0 else mBuilder.mMainAxisEdgeSpace,
                            if (isSingleSpan) mBuilder.mCrossAxisEdgeSpace else rightOrBottom
                        )
                    else -> outRect.set(
                        if (mLayoutManager.reverseLayout) mBuilder.mMainAxisSpace else 0,
                        if (isSingleSpan) mBuilder.mCrossAxisEdgeSpace else leftOrTop,
                        if (mLayoutManager.reverseLayout) 0 else mBuilder.mMainAxisSpace,
                        if (isSingleSpan) mBuilder.mCrossAxisEdgeSpace else rightOrBottom
                    )
                }
            }
        }
    }

    override fun drawDivide(
        c: Canvas,
        builder: SuperOffsetDecoration.Builder,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

    }
}