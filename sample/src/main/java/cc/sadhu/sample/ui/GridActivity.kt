package cc.sadhu.sample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cc.sadhu.sample.R
import cc.sadhu.sample.adapter.SimpleAdapter
import cc.sadhu.sample.bean.Item
import cc.sadhu.superdecoration.SuperOffsetDecoration
import cc.sadhu.superdecoration.UIUtils
import kotlinx.android.synthetic.main.activity_linear.*
import kotlinx.android.synthetic.main.layout_control.*
import kotlin.random.Random

class GridActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear)
        mBtnUpdate.setOnClickListener {
            update()
        }
        initData()
    }

    private fun update() {
        val oldLayoutManager = mRvContent.layoutManager
        val layoutManager = GridLayoutManager(
            this,
            4,
            if (mGroup.checkedRadioButtonId == R.id.mBtnHorizontal) RecyclerView.HORIZONTAL else RecyclerView.VERTICAL,
            mCheckReverse.isChecked
        )
        if (mCheckSpan.isChecked) {
            setSpanLookup(layoutManager)
        }


        mRvContent.layoutManager = layoutManager
        mRvContent.removeItemDecorationAt(0)
        mRvContent.addItemDecoration(
            SuperOffsetDecoration.Builder(layoutManager, this)
                .setMainAxisSpace(UIUtils.dp2px(mEtMainAxis.text.toString().toInt()))
                .setCrossAxisSpace(UIUtils.dp2px(mEtCrossAxis.text.toString().toInt()))
                .setMainAxisEdgeSpace(UIUtils.dp2px(mEtMainAxisEdge.text.toString().toInt()))
                .setCrossAxisEdgeSpace(UIUtils.dp2px(mEtCrossAxisEdge.text.toString().toInt()))
                .build()
        )
        // 判断方向是否改变, 方向改变了需要更换item的布局文件
        if (oldLayoutManager is GridLayoutManager) {
            if (oldLayoutManager.orientation != layoutManager.orientation) {
                mRvContent.adapter = SimpleAdapter(
                    (mRvContent.adapter as SimpleAdapter).list,
                    layoutManager.orientation == RecyclerView.HORIZONTAL
                )
            } else {
                mRvContent.adapter?.notifyDataSetChanged()
            }
        }
    }

    private fun initData() {
        val layoutManager = GridLayoutManager(
            this,
            4,
            if (mGroup.checkedRadioButtonId == R.id.mBtnHorizontal) RecyclerView.HORIZONTAL else RecyclerView.VERTICAL,
            mCheckReverse.isChecked
        )
        if (mCheckSpan.isChecked) {
            setSpanLookup(layoutManager)
        }
        mRvContent.layoutManager = layoutManager
        mRvContent.addItemDecoration(
            SuperOffsetDecoration.Builder(layoutManager, this)
                .setMainAxisSpace(UIUtils.dp2px(mEtMainAxis.text.toString().toInt()))
                .setCrossAxisSpace(UIUtils.dp2px(mEtCrossAxis.text.toString().toInt()))
                .setMainAxisEdgeSpace(UIUtils.dp2px(mEtMainAxisEdge.text.toString().toInt()))
                .setCrossAxisEdgeSpace(UIUtils.dp2px(mEtCrossAxisEdge.text.toString().toInt()))
                .build()
        )
        val list = mutableListOf<Item>()
        for (i in 0..50) {
            val randomId = Random.Default.nextInt(1, 14)
            val id = resources.getIdentifier("tmp$randomId", "drawable", "cc.sadhu.sample")
            val item = Item(id, "desc$i")
            list.add(item)
        }
        mRvContent.adapter = SimpleAdapter(list)
    }


    private fun setSpanLookup(layoutManager: GridLayoutManager) {
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 1 || position == 4 || position == 20 || position == 10) {
                    4
                } else {
                    1
                }
            }
        }
    }
}