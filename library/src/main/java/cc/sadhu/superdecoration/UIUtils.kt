package cc.sadhu.superdecoration

import android.content.res.Resources
import android.util.TypedValue

/**
 * Created by yaocai on 2019-12-30.
 */
object UIUtils {

    fun dp2px(dp: Int): Int {
        return (TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            Resources.getSystem().displayMetrics
        ) + 0.5).toInt()
    }

}