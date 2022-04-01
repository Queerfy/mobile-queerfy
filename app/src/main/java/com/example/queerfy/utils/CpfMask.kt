package com.example.queerfy.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText


object CpfMask {
    private const val CPFMask = "###.###.###-##"

    fun unmask(s: String): String {
        return s.replace("[^0-9]*".toRegex(), "")
    }

    fun insert(editText: EditText): TextWatcher {
        return object : TextWatcher {
            var isUpdating = false
            var oldValue = ""
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val value = unmask(s.toString())
                var maskAux = ""
                if (isUpdating) {
                    oldValue = value
                    isUpdating = false
                    return
                }
                var i = 0
                for (m in CPFMask.toCharArray()) {
                    if (m != '#' && value.length > oldValue.length || m != '#' && value.length < oldValue.length && value.length != i) {
                        maskAux += m
                        continue
                    }
                    maskAux += try {
                        value[i]
                    } catch (e: Exception) {
                        break
                    }
                    i++
                }
                isUpdating = true
                editText.setText(maskAux)
                editText.setSelection(maskAux.length)
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        }
    }
}