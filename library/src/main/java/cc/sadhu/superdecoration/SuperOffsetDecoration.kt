package cc.sadhu.superdecoration

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cc.sadhu.neteasecloudmusic.view.derocation.SpaceInfoData

class SuperOffsetDecoration(builder: Builder) :
    RecyclerView.ItemDecoration() {
    private var mOrientationDecorationHelper: OrientationDecorationHelper

    init {
        val info = SpaceInfoData(
            builder.mPrimarySpace,
            builder.mSecondarySpace,
            builder.mPrimaryEdgeSpace,
            builder.mSecondaryEdgeSpace
        )
        mOrientationDecorationHelper =
            builder.mOrientationDecorationHelper ?: OrientationDecorationHelper.createOrientationDecorationHelper(
                info,
                builder.layoutManager
            )
    }


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val adapter = parent.adapter
        val itemCount = adapter?.itemCount ?: 0
        val childAdapterPosition = parent.getChildAdapterPosition(view)
        if (itemCount > 0 && mOrientationDecorationHelper.needOffset(outRect, view, parent)) {
            mOrientationDecorationHelper.setOffset(outRect, childAdapterPosition, itemCount)
        }
    }

    class Builder(internal val layoutManager: LinearLayoutManager, internal val context: Context) {
        private val displayMetrics = context.resources.displayMetrics
        internal var mPrimaryEdgeSpace = 0F
        internal var mSecondaryEdgeSpace = 0F
        internal var mPrimarySpace = 0F
        internal var mSecondarySpace = 0F
        internal var mOrientationDecorationHelper: OrientationDecorationHelper? = null


        fun setOrientationDecorationHelper(helper: OrientationDecorationHelper): SuperOffsetDecoration {
            this.mOrientationDecorationHelper = helper
            return SuperOffsetDecoration(this)
        }

        fun setPrimarySpace(primarySpace: Float): Builder {
            this.mPrimarySpace = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, primarySpace, displayMetrics)
            return this
        }

        fun setSecondarySpace(secondarySpace: Float): Builder {
            this.mSecondarySpace =
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, secondarySpace, displayMetrics)
            return this
        }

        fun setPrimaryEdgeSpace(primaryEdgeSpace: Float): Builder {
            this.mPrimaryEdgeSpace =
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, primaryEdgeSpace, displayMetrics)
            return this
        }

        fun setSecondaryEdgeSpace(secondaryEdgeSpace: Float): Builder {
            this.mSecondaryEdgeSpace =
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, secondaryEdgeSpace, displayMetrics)
            return this
        }

        fun build(): SuperOffsetDecoration {
            return SuperOffsetDecoration(this)
        }

    }


}