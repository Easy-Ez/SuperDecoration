package cc.sadhu.sample.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cc.sadhu.sample.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mBtnLinear.setOnClickListener {
            startActivity(Intent(this, LinearActivity::class.java))
        }
        mBtnGrid.setOnClickListener {
            startActivity(Intent(this, GridActivity::class.java))

        }
    }
}
