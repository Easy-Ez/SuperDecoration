package cc.sadhu.superdecoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.IntDef
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class SuperOffsetDecoration(private val builder: Builder) :
    RecyclerView.ItemDecoration() {
    private var mOrientationDecorationHelper =
        OrientationDecorationHelper.createOrientationDecorationHelper(
            builder,
            builder.layoutManager
        )


    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val adapter = parent.adapter
        val itemCount = adapter?.itemCount ?: 0
        val childAdapterPosition = parent.getChildAdapterPosition(view)
        if (itemCount > 0 && mOrientationDecorationHelper.needOffset(view, parent)) {
            mOrientationDecorationHelper.setOffset(outRect, childAdapterPosition, itemCount)
        }
    }


    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val childCount = parent.childCount
        if (childCount > 0 && builder.mShowDividers != SHOW_DIVIDER_NONE) {
            mOrientationDecorationHelper.drawDivide(c, builder, parent, state)
        }
    }

    class Builder(internal val layoutManager: LinearLayoutManager, internal val context: Context) {
        private val displayMetrics = context.resources.displayMetrics
        internal var mMainAxisEdgeSpace = 0
        internal var mCrossAxisEdgeSpace = 0
        internal var mMainAxisSpace = 0
        internal var mCrossAxisSpace = 0
        @ColorInt
        internal var mDividerColor: Int = 0xFFF0F0F1.toInt()
        @ColorInt
        internal var mBackgroundColor: Int = 0xFFFFFFFF.toInt()
        internal var paddingLeft = 0
        internal var paddingRight = 0
        internal var mShowDividers = SHOW_DIVIDER_NONE

//mainAxisAlignment and crossAxisAlignment


        fun setMainAxisSpace(mainAxisASpace: Int): Builder {
            this.mMainAxisSpace = mainAxisASpace
            return this
        }

        fun setCrossAxisSpace(crossAxisSpace: Int): Builder {
            this.mCrossAxisSpace =
                crossAxisSpace
            return this
        }

        fun setMainAxisEdgeSpace(mainAxisEdgeSpace: Int): Builder {
            this.mMainAxisEdgeSpace =
                mainAxisEdgeSpace
            return this
        }

        fun setCrossAxisEdgeSpace(crossAxisEdgeSpace: Int): Builder {
            this.mCrossAxisEdgeSpace =
                crossAxisEdgeSpace
            return this
        }

        fun setPading(padding: Int): Builder {
            this.paddingLeft = padding
            this.paddingRight = padding
            return this
        }

        fun setPaddingLeft(padding: Int): Builder {
            this.paddingLeft = padding
            return this
        }

        fun setPaddingRight(padding: Int): Builder {
            this.paddingRight = padding
            return this
        }

        fun setDividerColor(@ColorInt color: Int): Builder {
            this.mDividerColor = color
            return this
        }

        fun setBackgroundColor(@ColorInt color: Int): Builder {
            this.mBackgroundColor = color
            return this
        }

        /**
         * Set how dividers should be shown between items in this layout
         *
         * @param [showDividers] One or more of [SHOW_DIVIDER_BEGINNING],[SHOW_DIVIDER_MIDDLE] or [SHOW_DIVIDER_END]
         * to show dividers, [SHOW_DIVIDER_NONE] to show no dividers.
         */
        fun setShowDividers(@DividerMode showDividers: Int): Builder {
            this.mShowDividers = showDividers
            return this
        }


        fun build(): SuperOffsetDecoration {
            return SuperOffsetDecoration(this)
        }

    }

    companion object {
        @IntDef(
            flag = true,
            value = [SHOW_DIVIDER_NONE, SHOW_DIVIDER_BEGINNING, SHOW_DIVIDER_MIDDLE, SHOW_DIVIDER_END]
        )
        @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
        annotation class DividerMode

        /**
         * Don't show any dividers.
         */
        const val SHOW_DIVIDER_NONE = 0
        /**
         * Show a divider at the beginning of the group.
         */
        const val SHOW_DIVIDER_BEGINNING = 1
        /**
         * Show dividers between each item in the group.
         */
        const val SHOW_DIVIDER_MIDDLE = 2
        /**
         * Show a divider at the end of the group.
         */
        const val SHOW_DIVIDER_END = 4
    }


}