package com.example.hashdashassignment.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hashdashassignment.BuildConfig
import com.example.hashdashassignment.R
import com.razorpay.Checkout
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import org.json.JSONObject

class PaymentActivity : AppCompatActivity(), PaymentResultWithDataListener {

    private lateinit var btnPay: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        btnPay = findViewById(R.id.btnPay)
        Checkout.preload(applicationContext)
        btnPay.setOnClickListener { pay() }

    }

    private fun pay() {
        val checkout = Checkout()
        checkout.setKeyID(BuildConfig.API_KEY)
        try {
            val options = JSONObject()
            options.put("name", "Arunabha Mandal")
            options.put("description", "Books")
            options.put("image", "https://d6xcmfyh68wv8.cloudfront.net/newsroom-content/uploads/2022/07/Razorpay_payments.png")
            options.put("theme.color", "#e03570")
            options.put("amount", "80000")
            options.put("currency", "INR")

            val prefill = JSONObject()
            prefill.put("email", "arunabha.mandal@example.com")
            prefill.put("contact", "9999988888")

            options.put("prefill", prefill)
            checkout.open(this, options)
        } catch (e: Exception) {
            Toast.makeText(this, "Error in payment: " + e.message, Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

    override fun onPaymentSuccess(p0: String?, p1: PaymentData?) {
        Toast.makeText(this, "Payment successful!", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java))
        Checkout.clearUserData(this) // clearing user data after successful transaction
        finishAffinity()
    }

    override fun onPaymentError(p0: Int, p1: String?, p2: PaymentData?) {
        Toast.makeText(this, "Payment error!", Toast.LENGTH_SHORT).show()
        Checkout.clearUserData(this)
    }
}