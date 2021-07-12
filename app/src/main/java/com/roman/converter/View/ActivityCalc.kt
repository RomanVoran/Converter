package com.roman.converter.View

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.roman.converter.Contracts.CalcContract
import com.roman.converter.Model.ValuteSet
import com.roman.converter.Presenter.CalcPresenter
import com.roman.converter.R
import com.roman.converter.ValuteList
import kotlinx.android.synthetic.main.activity_calc.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class ActivityCalc : AppCompatActivity(), CalcContract.View{

    private var readyToAutoChange = false
    private val REQUEST_CODE = 0
    private var targetPos = ValuteSet.Pos.UP // target button to set new valute char code
    var presenter: CalcContract.Presenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)

        // start parameters
        name_view1.text = ValuteList.getName("RUB")
        name_view2.text = ValuteList.getName("USD")
        value_view1.setText("1")
        value_view2.setText("1")
        button_view1.setText("RUB")
        button_view2.setText("USD")

        button_view1.setOnClickListener {
            targetPos = ValuteSet.Pos.UP
            val intent = Intent(this,ActivityList::class.java)
            intent.putExtra("tapped",button_view1.text)
            intent.putExtra("second",button_view2.text)
            startActivityForResult(intent,REQUEST_CODE)
        }
        button_view2.setOnClickListener {
            targetPos = ValuteSet.Pos.DOWN
            val intent = Intent(this,ActivityList::class.java)
            intent.putExtra("tapped",button_view2.text)
            intent.putExtra("second",button_view1.text)
            startActivityForResult(intent,REQUEST_CODE)
        }

        var timer1 = Timer()
        var timer2 = Timer()
        var DELAY:Long = 1000

        value_view1.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (readyToAutoChange) {

                    timer1 = Timer()
                    timer1.schedule(object : TimerTask() {
                        override fun run() {
                            //do something
                            presenter!!.recalculate(getSet(ValuteSet.Pos.DOWN))
                        }
                    }, DELAY)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                timer1.cancel() //Terminates this timer,discarding any currently scheduled tasks.
                timer1.purge() //Removes all cancelled tasks from this timer's task queue.
            }
        })


        value_view2.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (readyToAutoChange) {
                    timer2 = Timer()
                    timer2.schedule(object : TimerTask() {
                        override fun run() {
                            //do something
                            presenter!!.recalculate(getSet(ValuteSet.Pos.UP))
                        }
                    }, DELAY)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                timer2.cancel() //Terminates this timer,discarding any currently scheduled tasks.
                timer2.purge() //Removes all cancelled tasks from this timer's task queue.
            }
        })

        presenter = CalcPresenter(this)

        GlobalScope.launch {
            presenter?.recalculate(getSet(ValuteSet.Pos.DOWN))
        }
    }

    private fun getSet(pos: ValuteSet.Pos):ValuteSet{
        val value:String
        if (pos == ValuteSet.Pos.UP)
            value = value_view2.text.toString()
        else
            value = value_view1.text.toString()
        return ValuteSet(value, button_view1.text.toString(), button_view2.text.toString(),pos)
    }

    override fun changeValues(set: ValuteSet) {
        runOnUiThread {
            readyToAutoChange = false
            if (set.getPos() == ValuteSet.Pos.UP)
                value_view1.setText(set.getVal().toString())
            else
                value_view2.setText(set.getVal().toString())
            readyToAutoChange = true
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((data != null) && (requestCode == REQUEST_CODE) && (resultCode == Activity.RESULT_OK)) {
            val code: String = data.getStringExtra("code")
            if (targetPos == ValuteSet.Pos.UP) {
                button_view1.setText(code)
                name_view1.text = ValuteList.getName(code)
                presenter?.recalculate(getSet(ValuteSet.Pos.UP))
            }else {
                button_view2.setText(code)
                name_view2.text = ValuteList.getName(code)
                presenter?.recalculate(getSet(ValuteSet.Pos.DOWN))
            }
        }
    }
}
