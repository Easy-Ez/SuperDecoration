package cc.sadhu.sample.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cc.sadhu.sample.R
import cc.sadhu.sample.adapter.SimpleAdapter
import cc.sadhu.sample.bean.Item
import cc.sadhu.superdecoration.SuperOffsetDecoration
import kotlinx.android.synthetic.main.activity_linear.*
import kotlinx.android.synthetic.main.layout_control.*
import kotlin.random.Random

class LinearActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear)
        mCheckSpan.visibility = View.GONE
        mBtnUpdate.setOnClickListener {
            update()
        }
        initData()
    }

    private fun update() {
        val layoutManager = LinearLayoutManager(
            this,
            if (mGroup.checkedRadioButtonId == R.id.mBtnHorizontal) RecyclerView.HORIZONTAL else RecyclerView.VERTICAL,
            mCheckReverse.isChecked
        )
        mRvContent.layoutManager = layoutManager
        mRvContent.removeItemDecorationAt(0)
        mRvContent.addItemDecoration(
            SuperOffsetDecoration.Builder(layoutManager, this)
                .setPrimarySpace(mEtPrimary.text.toString().toFloat())
                .setSecondarySpace(mEtSecondary.text.toString().toFloat())
                .setPrimaryEdgeSpace(mEtPrimaryEdge.text.toString().toFloat())
                .setSecondaryEdgeSpace(mEtSecondaryEdge.text.toString().toFloat())
                .build()
        )
        mRvContent.adapter?.notifyDataSetChanged()
    }

    private fun initData() {
        val layoutManager = LinearLayoutManager(
            this,
            if (mGroup.checkedRadioButtonId == R.id.mBtnHorizontal) RecyclerView.HORIZONTAL else RecyclerView.VERTICAL,
            mCheckReverse.isChecked
        )
        mRvContent.layoutManager = layoutManager
        mRvContent.addItemDecoration(
            SuperOffsetDecoration.Builder(layoutManager, this)
                .setPrimarySpace(mEtPrimary.text.toString().toFloat())
                .setSecondarySpace(mEtSecondary.text.toString().toFloat())
                .setPrimaryEdgeSpace(mEtPrimaryEdge.text.toString().toFloat())
                .setSecondaryEdgeSpace(mEtSecondaryEdge.text.toString().toFloat())
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
}