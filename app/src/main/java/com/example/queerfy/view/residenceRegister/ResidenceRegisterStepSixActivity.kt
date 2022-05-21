package com.example.queerfy.view.residenceRegister

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.queerfy.databinding.ActivityHouseRegisterStepSixBinding

class ResidenceRegisterStepSixActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHouseRegisterStepSixBinding
    lateinit var residenceRegister: SharedPreferences
    private val pickImage = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityHouseRegisterStepSixBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        residenceRegister = getSharedPreferences("residenceRegister", MODE_PRIVATE)
    }

    fun selectedImage(v: View) {
        var intent = Intent()
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(intent, "Choose Pictures")
            , 200
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Activity.RESULT_OK && requestCode == 200) {
            // if multiple images are selected
            if (data?.getClipData() !== null) {
                var count = data.clipData!!.itemCount

                for(i in 0..count) {
                    var imageUri: Uri = data.clipData!!.getItemAt(i).uri
                    println(imageUri)
                }
            }else if (data?.getData() !== null) {
                // if single image is selected

                var imageUri: Uri = data.data!!
                println(imageUri)
            }
        }

    }

    fun createResidence(v: View) {

    }
}