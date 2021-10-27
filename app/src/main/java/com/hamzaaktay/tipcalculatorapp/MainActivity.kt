package com.hamzaaktay.tipcalculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hamzaaktay.tipcalculatorapp.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//hesapla butonuna basildiginda yapilacaklar
        binding.hesaplaButton.setOnClickListener {
            bahsisHesapla()
        }
    }

    fun bahsisHesapla() {

        val hizmetBedeliTutar = binding.hizmetBedeliEditText.text.toString()

        //hizmet bedeli string oldugu icin double yapicaz. Ayrica eger o kismi bos birakirsak
        // diye null kismini ayarliyoruz ve if ile sonra ne olacagini duzenliyoruz
        val tutar = hizmetBedeliTutar.toDoubleOrNull()

        if (tutar == null){
            binding.bahsisTutariTextview.text = ""
            return
        }

        //hizmet bedelinden sonra bahsisi hesaplamak icin secilen radiolari check et
        val secilenRadioButton = binding.bahsisSecenekleriRadioGroup.checkedRadioButtonId

        //bahsis yuzde ile hesabi ayarlarak ne kadar bahsis odeyecegimizin hesaplanmasi icin
        val bahsisYuzdesi = when(secilenRadioButton){

            R.id.yuzde20RadioButton -> 0.20
            R.id.yuzde15RadioButton -> 0.18
            else -> 0.15
        }

        var bahsis = bahsisYuzdesi * tutar

        //bahsis switchlerini true false olarak dondurmek icin
        val yukariYuvarla = binding.bahsisYuvarlaSwitch.isChecked

        if(yukariYuvarla){
            bahsis = kotlin.math.ceil(bahsis)
        }
        //bu fonksiyon ile turk lirasi simgesi eklemeyi ayarliyoruz
        val formatlananBahsis = NumberFormat.getCurrencyInstance(Locale("tr","Tr")).format(bahsis)

        //bahsisimizin onunde bahsis tutari yazsin diye yaziyoruz.
        binding.bahsisTutariTextview.text = getString(R.string.bahsis_tutari, formatlananBahsis)
    }
}