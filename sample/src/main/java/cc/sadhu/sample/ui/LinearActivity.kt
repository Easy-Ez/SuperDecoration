package cc.sadhu.sample.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cc.sadhu.sample.R
import cc.sadhu.sample.adapter.SimpleAdapter
import cc.sadhu.sample.bean.Item
import cc.sadhu.superdecoration.SuperOffsetDecoration
import cc.sadhu.superdecoration.UIUtils
import com.jaredrummler.android.colorpicker.ColorPickerDialog
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener
import kotlinx.android.synthetic.main.activity_linear.*
import kotlinx.android.synthetic.main.layout_control.*
import kotlin.random.Random

class LinearActivity : AppCompatActivity() {

    @ColorInt
    private var mDividerColor = Color.parseColor("#F6F6F6")
    @ColorInt
    private var mBackgroundColor = Color.parseColor("#D81B60")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear)
        mCheckSpan.visibility = View.GONE
        mBtnUpdate.setOnClickListener {
            update()
        }

        mViewChooseDividerColor.setOnClickListener {
            var dividerDialog = supportFragmentManager.findFragmentByTag("colorDialog")
            if (dividerDialog == null) {
                dividerDialog = ColorPickerDialog.newBuilder()
                    .setColor(mDividerColor).create()
                dividerDialog.setColorPickerDialogListener(object : ColorPickerDialogListener {
                    override fun onDialogDismissed(dialogId: Int) {
                    }

                    override fun onColorSelected(dialogId: Int, color: Int) {
                        mDividerColor = color
                        mViewChooseDividerColor.setBackgroundColor(color)
                    }
                })
            }
            if (dividerDialog is DialogFragment) {
                dividerDialog.show(supportFragmentManager, "colorDialog")

            }
        }

        mViewChooseBackgroundColor.setOnClickListener {
            var backgroundDialog = supportFragmentManager.findFragmentByTag("colorDialog")
            if (backgroundDialog == null) {
                backgroundDialog = ColorPickerDialog.newBuilder()
                    .setColor(mBackgroundColor).create()
                backgroundDialog.setColorPickerDialogListener(object : ColorPickerDialogListener {
                    override fun onDialogDismissed(dialogId: Int) {
                    }

                    override fun onColorSelected(dialogId: Int, color: Int) {
                        mBackgroundColor = color
                        mLlContainer.setBackgroundColor(color)
                        mViewChooseBackgroundColor.setBackgroundColor(color)
                    }
                })
            }
            if (backgroundDialog is DialogFragment) {
                backgroundDialog.show(supportFragmentManager, "colorDialog")

            }
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
        val builder = SuperOffsetDecoration.Builder(layoutManager, this)
            .setMainAxisSpace(UIUtils.dp2px(mEtMainAxis.text.toString().toInt()))
            .setCrossAxisSpace(UIUtils.dp2px(mEtCrossAxis.text.toString().toInt()))
            .setMainAxisEdgeSpace(UIUtils.dp2px(mEtMainAxisEdge.text.toString().toInt()))
            .setCrossAxisEdgeSpace(UIUtils.dp2px(mEtCrossAxisEdge.text.toString().toInt()))
            .toggleDividerMode(mCheckDivider.isChecked)
        if (mCheckDivider.isChecked) {
            builder.setPaddingLeft(UIUtils.dp2px(mEtDividePaddingLeft.text.toString().toInt()))
                .setPaddingRight(UIUtils.dp2px(mEtDividePaddingRight.text.toString().toInt()))
                .setDividerColor(mDividerColor)
                .setBackgroundColor(mBackgroundColor)
        }
        mRvContent.addItemDecoration(
            builder.build()
        )
        val adapter = mRvContent.adapter
        if (adapter is SimpleAdapter) {
            if ((mGroup.checkedRadioButtonId == R.id.mBtnHorizontal && adapter.isHorizontal)
                || (mGroup.checkedRadioButtonId == R.id.mBtnVertical && !adapter.isHorizontal)
            ) {
                adapter.notifyDataSetChanged()
            } else {
                mRvContent.adapter =
                    SimpleAdapter(adapter.list, mGroup.checkedRadioButtonId == R.id.mBtnHorizontal)
            }
        }

    }

    private fun initData() {
        val layoutManager = LinearLayoutManager(
            this,
            if (mGroup.checkedRadioButtonId == R.id.mBtnHorizontal) RecyclerView.HORIZONTAL else RecyclerView.VERTICAL,
            mCheckReverse.isChecked
        )
        mRvContent.layoutManager = layoutManager

        val builder = SuperOffsetDecoration.Builder(layoutManager, this)
            .setMainAxisSpace(UIUtils.dp2px(mEtMainAxis.text.toString().toInt()))
            .setCrossAxisSpace(UIUtils.dp2px(mEtCrossAxis.text.toString().toInt()))
            .setMainAxisEdgeSpace(UIUtils.dp2px(mEtMainAxisEdge.text.toString().toInt()))
            .setCrossAxisEdgeSpace(UIUtils.dp2px(mEtCrossAxisEdge.text.toString().toInt()))
            .toggleDividerMode(mCheckDivider.isChecked)
        if (mCheckDivider.isChecked) {
            builder.setPaddingLeft(UIUtils.dp2px(mEtDividePaddingLeft.text.toString().toInt()))
                .setPaddingRight(UIUtils.dp2px(mEtDividePaddingRight.text.toString().toInt()))
                .setDividerColor(mDividerColor)
                .setBackgroundColor(mBackgroundColor)
        }
        mRvContent.addItemDecoration(
            builder.build()
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