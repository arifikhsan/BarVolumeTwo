package com.example.barvolumetwo

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val STATE_RESULT = "state_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_calculate.setOnClickListener(this)
        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            tv_result.text = result
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.putString(STATE_RESULT, tv_result.text.toString())
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.btn_calculate) {
            val inputLength = et_length.text.toString()
            val inputWidth = et_width.text.toString()
            val inputHeight = et_height.text.toString()

            var isEmptyFields = false
            var isInvalidDouble = false

            if (inputLength.isEmpty()) {
                isEmptyFields = true
                et_length.error = "Field ini tidak boleh kosong"
            }

            if (inputWidth.isEmpty()) {
                isEmptyFields = true
                et_width.error = "Field ini tidak boleh kosong"
            }

            if (inputHeight.isEmpty()) {
                isEmptyFields = true
                et_height.error = "Field ini tidak boleh kosong"
            }

            val length = inputLength.toDouble()
            val width = inputWidth.toDouble()
            val height = inputHeight.toDouble()

            if (length.isNaN()) {
                isInvalidDouble = true
                et_length.error = "Field ini harus berupa nomor"
            }

            if (width.isNaN()) {
                isInvalidDouble = true
                et_width.error = "Field ini harus berupa nomor"
            }

            if (height.isNaN()) {
                isInvalidDouble = true
                et_height.error = "Field ini harus berupa nomor"
            }

            if (!isEmptyFields && !isInvalidDouble) {
                val volume = length * width * height
                tv_result.text = volume.toString()
            }
        }
    }

}
