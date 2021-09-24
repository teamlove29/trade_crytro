package com.alw.trade.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import com.alw.trade.R
import kotlinx.android.synthetic.main.activity_deposit.*
import kotlinx.android.synthetic.main.test_dialog.view.*

class DepositActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deposit)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = "ฝาก THB"



        btn_submit_deposit.setOnClickListener {
            if(edit_text_amount.text.toString() != ""){
                showdialog(edit_text_amount.text.toString().toInt())
            }
        }


        edit_text_amount.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s!!.isNotEmpty()){
                    btn_submit_deposit.isEnabled = true
                    btn_submit_deposit.setBackgroundColor(resources.getColor(R.color.green))
                }else{
                    btn_submit_deposit.isEnabled = false
                    btn_submit_deposit.setBackgroundColor(resources.getColor(R.color.gray))
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

    }


    fun showdialog(amount:Int) {

        val alertDialog = AlertDialog.Builder(this)
            .setTitle("ยืนยันการฝากเงิน")
            .setMessage("ยืนยันการฝากเงิน $amount THB ?")
        alertDialog.setPositiveButton(android.R.string.yes) { dialog, which ->
            Toast.makeText(applicationContext,
                android.R.string.yes, Toast.LENGTH_SHORT).show()
        }

        alertDialog.setNegativeButton(android.R.string.no) { dialog, which ->
            Toast.makeText(applicationContext,
                android.R.string.no, Toast.LENGTH_SHORT).show()
        }
        .show()


    }

}