package com.example.queerfy.view.residenceRegister

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.queerfy.databinding.ActivityHouseRegisterStepSixBinding
import com.example.queerfy.model.NewPropertyModel
import com.example.queerfy.viewModel.ResidenceRegisterViewModel
import com.google.android.gms.maps.model.LatLng
import java.io.IOException

class ResidenceRegisterStepSixActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHouseRegisterStepSixBinding
    lateinit var residenceRegister: SharedPreferences
    private val residenceRegisterViewModel = ResidenceRegisterViewModel()

    val REQUEST_CODE = 200

    lateinit var imagesUri: ArrayList<Uri>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityHouseRegisterStepSixBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        residenceRegister = getSharedPreferences("residenceRegister", MODE_PRIVATE)

        imagesUri = ArrayList()
    }

    fun selectedImage(v: View) {
        if (Build.VERSION.SDK_INT < 19) {
            var intent = Intent()
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Choose Pictures")
                , REQUEST_CODE
            )
        }else {
            var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            // if multiple images are selected

            if (data?.clipData != null) {
                var count = data.clipData?.itemCount

                for (i in 0..count!! - 1) {
                    imagesUri.add(data.clipData?.getItemAt(i)!!.uri)

                    // println(imagesUri.size)
                }
            }else if(data?.data != null) {
                // if single image is selected
                imagesUri.add(data.data!!)

                // println(imageUri)
            }
        }
    }

    fun getLocationFromAddress(street: String): LatLng? {
        var coder = Geocoder(this)
        var address: List<Address>
        var p1: LatLng? = null

        try {
            address = coder.getFromLocationName(street, 5)

            if (address == null) {
                return null
            }

            val location = address?.get(0)
            p1 = location?.let { LatLng(it.latitude, location.longitude) }

        }catch (ex: IOException) {
            ex.printStackTrace();
        }

        return p1
    }

    fun createResidence(v: View) {
        val preferences = getSharedPreferences("userPreferences", MODE_PRIVATE)

        val idUser = preferences.getInt("idUser", 0)

        val propertyType = residenceRegister.getString("propertyType", "")
        val spaceType = residenceRegister.getString("spaceType", "")
        val street = residenceRegister.getString("street", "")
        val city = residenceRegister.getString("city", "").toString().lowercase()
        val uf = residenceRegister.getString("uf", "").toString().uppercase()
        val cep = residenceRegister.getString("cep", "")
        val number = residenceRegister.getString("number", "")
        val district = residenceRegister.getString("district", "")
        val complement = residenceRegister.getString("complement", "")
        val referencePoint = residenceRegister.getString("referencePoint", "")
        val guestsQuantity = residenceRegister.getString("guestsQuantity", "")
        val roomQuantity = residenceRegister.getString("roomQuantity", "")
        val bedsQuantity = residenceRegister.getString("bedsQuantity", "")
        val bathroomQuantity = residenceRegister.getString("bathroomQuantity", "")
        val haveWifi = residenceRegister.getBoolean("haveWifi", false)
        val haveChicken = residenceRegister.getBoolean("haveChicken", false)
        val haveGarage = residenceRegister.getBoolean("haveGarage", false)
        val haveAnimals = residenceRegister.getBoolean("haveAnimals", false)
        val haveSuite = residenceRegister.getBoolean("haveSuite", false)
        val titleResidence = residenceRegister.getString("titleResidence", "")
        val describeResidence = residenceRegister.getString("describeResidence", "")
        val dailyPrice = residenceRegister.getFloat("dailyPrice", 0F).toDouble()

        val address = "${number} ${street}, ${city}"

        val latitude = getLocationFromAddress(address)?.latitude.toString()
        val longitude = getLocationFromAddress(address)?.latitude.toString()

        residenceRegisterViewModel.newPropertyModel = NewPropertyModel(
            name = titleResidence,
            active = true,
            dailyPrice = dailyPrice,
            filterDate = "weeeked",
            idUser = idUser,
            description = describeResidence,
            likes = 0,
            state = null,
            cep = cep,
            uf = uf,
            street = street,
            houseNumber = number,
            addressComplement = complement,
            city = city,
            propertyType = propertyType,
            spaceType = spaceType,
            guestsQuantity = guestsQuantity,
            bedsQuantity = bedsQuantity,
            roomQuantity = roomQuantity,
            bathroomQuantity = bathroomQuantity,
            haveWifi = haveWifi,
            haveKitchen = haveChicken,
            haveSuite = haveSuite,
            haveGarage = haveGarage,
            haveAnimals = haveAnimals,
            referencePoint = referencePoint,
            latitude = latitude,
            longitude = longitude,
        )

        residenceRegisterViewModel.newPropertyModel?.let { it1 ->
            residenceRegisterViewModel.putIntoBd(it1, imagesUri, this)
        }


    }
}