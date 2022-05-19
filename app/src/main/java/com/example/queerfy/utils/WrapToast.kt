package com.example.queerfy.view

import android.app.Activity
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import com.example.queerfy.R

fun Toast.showCustomToast(message: String, activity: Activity) {
    val layout = activity.layoutInflater.inflate(
        R.layout.custom_toast,
        activity.findViewById(R.id.toast_container)
    )

    val textView = layout.findViewById<TextView>(R.id.toast_text)
    textView.text = message

    this.apply {
        setGravity(Gravity.TOP, 0,40)
        duration = Toast.LENGTH_LONG
        view = layout
        show()
    }
}