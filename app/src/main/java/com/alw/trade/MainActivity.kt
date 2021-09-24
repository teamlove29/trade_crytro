package com.alw.trade


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.alw.trade.UI.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()




        btn_crypto.setOnClickListener {
            val intent = Intent(this, CryptoActivity::class.java)
            startActivity(intent)
        }

        btn_deposit.setOnClickListener {
            val intent = Intent(this, DepositActivity::class.java)
            startActivity(intent)
        }

        btn_withdraw.setOnClickListener {
            val intent = Intent(this, WithdrawActivity::class.java)
            startActivity(intent)
        }


        btn_wallet.setOnClickListener {
            val intent = Intent(this, MyWalletActivity::class.java)
            startActivity(intent)
        }

        btn_history.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

    }
}