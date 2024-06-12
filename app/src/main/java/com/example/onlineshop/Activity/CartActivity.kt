package com.example.onlineshop.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlineshop.Adapter.CartAdapter
import com.example.onlineshop.R
import com.example.onlineshop.databinding.ActivityCartBinding
import com.example.project1762.Helper.ChangeNumberItemsListener
import com.example.project1762.Helper.ManagmentCart

class CartActivity : BaseActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var managmentCart: ManagmentCart
    private var tax: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        setVariable()
        caculateCart()
        initCartList()

    }

    private fun initCartList() {
        with(binding){
            emptytxt.visibility =
                if(managmentCart.getListCart().isEmpty()) View.VISIBLE else View.GONE
            scrollView3.visibility =
                if(managmentCart.getListCart().isEmpty()) View.GONE else View.VISIBLE
        }

        binding.viewCart.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        binding.viewCart.adapter = CartAdapter(managmentCart.getListCart(), this, object :ChangeNumberItemsListener{
            override fun onChanged() {
                caculateCart()
            }

        })
    }

    private fun caculateCart() {
        val percentTax = 0.02
        val delivery = 10.0
        tax = Math.round((managmentCart.getTotalFee()*percentTax)*100)/100.0
        val total = Math.round((managmentCart.getTotalFee()+tax+ delivery) * 100) /100
        val itemTotal = Math.round(managmentCart.getTotalFee()*100)/100

        with(binding){
            totalFeeTxt.text = "$$itemTotal"
            taxTxt.text= "$$tax"
            deliveryTxt.text = "$$delivery"
            totalTxt.text = "$$total"
        }
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener{finish()}
    }
}