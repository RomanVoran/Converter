package com.roman.converter.View

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.roman.converter.R
import com.roman.converter.Contracts.LoadContract
import com.roman.converter.Presenter.LoadPresenter

import kotlinx.android.synthetic.main.activity_load.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ActivityLoad : AppCompatActivity(),LoadContract.LoadView {

    var presenter: LoadContract.LoadPresenter? = null
    var readyToRefresh:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)
        setSupportActionBar(toolbar)

        presenter = LoadPresenter(this)

        progress_bar.isVisible= true
        GlobalScope.launch {
            presenter!!.load()
        }

        imageView.setOnClickListener{
            if(readyToRefresh) {
                progress_bar.isVisible = true
                imageView.isVisible = false
                readyToRefresh = false
                GlobalScope.launch {
                    presenter!!.load()

                }
            }
        }
    }

    override fun showErrorMessage() {
        Toast.makeText(this,R.string.loadError, Toast.LENGTH_LONG).show()
        readyToRefresh = true
        progress_bar.isVisible = false
        imageView.isVisible = true
    }

    override fun nextActivity() {
        progress_bar.isVisible = false
        val intent = Intent(this,ActivityCalc::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter!!.detachView()
        presenter = null
    }


}
