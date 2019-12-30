package cc.sadhu.superdecoration

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class SimpleLinearDecorationHelper(
    private val mBuilder: SuperOffsetDecoration.Builder,
    private val mLayoutManager: LinearLayoutManager
) : OrientationDecorationHelper {

    override fun setOffset(outRect: Rect, childAdapterPosition: Int, itemCount: Int) {
        if (mLayoutManager.orientation == RecyclerView.VERTICAL) {
            // 一种是vertical
            // 如果就只有一行
            if (itemCount == 1) {
                outRect.set(
                    mBuilder.mCrossAxisEdgeSpace,
                    mBuilder.mMainAxisEdgeSpace,
                    mBuilder.mCrossAxisEdgeSpace,
                    mBuilder.mMainAxisEdgeSpace
                )
            } else {
                // 首行
                when (childAdapterPosition) {
                    0 -> // 第一个要设置marginTop
                        outRect.set(
                            mBuilder.mCrossAxisEdgeSpace,
                            if (mLayoutManager.reverseLayout) mBuilder.mMainAxisSpace else mBuilder.mMainAxisEdgeSpace,
                            mBuilder.mCrossAxisEdgeSpace,
                            if (mLayoutManager.reverseLayout) mBuilder.mMainAxisEdgeSpace else mBuilder.mMainAxisSpace
                        )
                    itemCount - 1 -> // 最后一行要设置marginBottom
                        outRect.set(
                            mBuilder.mCrossAxisEdgeSpace,
                            if (mLayoutManager.reverseLayout) mBuilder.mMainAxisEdgeSpace else 0,
                            mBuilder.mCrossAxisEdgeSpace,
                            if (mLayoutManager.reverseLayout) 0 else mBuilder.mMainAxisEdgeSpace
                        )
                    else -> outRect.set(
                        mBuilder.mCrossAxisEdgeSpace,
                        if (mLayoutManager.reverseLayout) mBuilder.mMainAxisSpace else 0,
                        mBuilder.mCrossAxisEdgeSpace,
                        if (mLayoutManager.reverseLayout) 0 else mBuilder.mMainAxisSpace
                    )
                }
            }
        } else if (mLayoutManager.orientation == RecyclerView.HORIZONTAL) {
            //  一种是horizontal
            if (itemCount == 1) {
                outRect.set(
                    mBuilder.mMainAxisEdgeSpace,
                    mBuilder.mCrossAxisEdgeSpace,
                    mBuilder.mMainAxisEdgeSpace,
                    mBuilder.mCrossAxisEdgeSpace
                )
            } else {
                // 首行
                when (childAdapterPosition) {
                    0 -> // 第一个要设置marginLeft
                        outRect.set(
                            if (mLayoutManager.reverseLayout) mBuilder.mMainAxisSpace else mBuilder.mMainAxisEdgeSpace,
                            mBuilder.mCrossAxisEdgeSpace,
                            if (mLayoutManager.reverseLayout) mBuilder.mMainAxisEdgeSpace else mBuilder.mMainAxisSpace,
                            mBuilder.mCrossAxisEdgeSpace
                        )
                    itemCount - 1 -> // 最后一行要设置marginRight
                        outRect.set(
                            if (mLayoutManager.reverseLayout) mBuilder.mMainAxisEdgeSpace else 0,
                            mBuilder.mCrossAxisEdgeSpace,
                            if (mLayoutManager.reverseLayout) 0 else mBuilder.mMainAxisEdgeSpace,
                            mBuilder.mCrossAxisEdgeSpace

                        )
                    else -> outRect.set(
                        if (mLayoutManager.reverseLayout) mBuilder.mMainAxisSpace else 0,
                        mBuilder.mCrossAxisEdgeSpace,
                        if (mLayoutManager.reverseLayout) 0 else mBuilder.mMainAxisSpace,
                        mBuilder.mCrossAxisEdgeSpace
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
        val childCount = parent.childCount
        var left: Float
        var top: Float
        var right: Float
        var bottom: Float
        val rectPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        // 最后一个暂时不画分割线
        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)
            if (needOffset(child, parent)) {
                if (mLayoutManager.orientation == RecyclerView.VERTICAL) {
                    left = 0F
                    top = child.bottom.toFloat()
                    right = parent.width.toFloat()
                    bottom = top + builder.mMainAxisSpace
                    // 如果没有padding, 则不需要要背景色
                    if (builder.paddingLeft == 0 && builder.paddingRight == 0) {
                        // 直接画divider
                        rectPaint.color = builder.mDividerColor
                        c.drawRect(left, top, right, bottom, rectPaint)
                    } else {
                        // 先画背景色色,一般是白色
                        rectPaint.color = builder.mBackgroundColor
                        c.drawRect(left, top, right, bottom, rectPaint)
                        // 再画分割线颜色,注意处理左右padding
                        rectPaint.color = builder.mDividerColor
                        c.drawRect(
                            left + builder.paddingLeft,
                            top,
                            right - builder.paddingRight,
                            bottom,
                            rectPaint
                        )
                    }
                } else if (mLayoutManager.orientation == RecyclerView.HORIZONTAL) {
                    left = child.right.toFloat()
                    top = 0F
                    right = left + builder.mMainAxisSpace
                    bottom = parent.height.toFloat()
                    // 如果没有padding, 则不需要要背景色
                    if (builder.paddingLeft == 0 && builder.paddingRight == 0) {
                        // 直接画divider
                        rectPaint.color = builder.mDividerColor
                        c.drawRect(left, top, right, bottom, rectPaint)
                    } else {
                        // 先画背景色色,一般是白色
                        rectPaint.color = builder.mBackgroundColor
                        c.drawRect(left, top, right, bottom, rectPaint)
                        // 再画分割线颜色,注意处理左右padding
                        rectPaint.color = builder.mDividerColor
                        c.drawRect(
                            left,
                            top + builder.paddingLeft,
                            right,
                            bottom - builder.paddingRight,
                            rectPaint
                        )
                    }
                }

            }
        }

    }
}

