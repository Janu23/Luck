package com.blackcoders.luck

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.blackcoders.luck.Util.Util
import com.blackcoders.luck.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        MobileAds.initialize(this){}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        binding.luckButton.setOnClickListener {
            binding.msg.visibility = View.INVISIBLE

            seeNumbers()

            val luckList : MutableList<Int> = generateSeq()

            Handler().postDelayed({
                binding.num1.text = luckList[0].toString().padStart(2, '0')
                binding.num2.text = luckList[1].toString().padStart(2, '0')
                binding.num3.text = luckList[2].toString().padStart(2, '0')
                binding.num4.text = luckList[3].toString().padStart(2, '0')
                binding.num5.text = luckList[4].toString().padStart(2, '0')
                binding.num6.text = luckList[5].toString().padStart(2, '0')
            }, 500)

        }
    }

    private fun seeNumbers() {
        binding.num1.visibility = View.VISIBLE
        binding.num2.visibility = View.VISIBLE
        binding.num3.visibility = View.VISIBLE
        binding.num4.visibility = View.VISIBLE
        binding.num5.visibility = View.VISIBLE
        binding.num6.visibility = View.VISIBLE
    }

    private fun generateSeq() : MutableList<Int> {
        val sizeSeq = 6
        val lowLimit = 1
        val highLimit = 60
        var num: Int
        var list : MutableList<Int> = arrayListOf()
        (0..5).forEach { _ ->
            num = Util.rand(lowLimit,highLimit)
            list.add(num)
        }
        if (list.distinct().size < sizeSeq){
            list = generateSeq()
        }
        return list.sorted() as MutableList<Int>
    }
}