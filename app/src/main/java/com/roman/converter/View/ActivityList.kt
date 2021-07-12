package com.roman.converter.View

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.roman.converter.R
import kotlinx.android.synthetic.main.activity_list.*

class ActivityList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val recyclerView:RecyclerView = recycler_view
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = ValuteRecyclerAdapter(callback,
            intent.extras?.getString("second").toString(),
            intent.extras?.getString("tapped").toString())
    }


    val callback = fun (code:String){
        val intent = Intent()
        intent.putExtra("code",code)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }


}
