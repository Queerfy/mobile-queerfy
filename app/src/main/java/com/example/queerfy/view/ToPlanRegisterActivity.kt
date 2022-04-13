package com.example.queerfy.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.queerfy.databinding.ActivityToplanRegisterBinding
import com.example.queerfy.model.ToPlanRegisterModel
import com.example.queerfy.model.UrgencyRegisterModel
import com.example.queerfy.utils.*
import com.example.queerfy.viewModel.ToPlanRegisterViewModel
import java.lang.ref.WeakReference


class ToPlanRegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityToplanRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityToplanRegisterBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        applyMasks()
//        setupSpinner()
//        setupListeners()
    }

//    private fun setupSpinner() {
//        setupSexualOrientationSpinner()
//        setupGenderIdentitySpinner()
//    }

//    private fun setupSexualOrientationSpinner() {
//        val options = SexOrientationEnum.toList()
//        this.binding.spnSexOrientation.adapter = ArrayAdapter(
//            this,
//            android.R.layout.simple_list_item_1,
//            options
//        )
//
//        this.binding.spnSexOrientation.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//            override fun onNothingSelected(parent: AdapterView<*>?) {}
//
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                ToPlanRegisterViewModel.sexOrientationEnum =
//                    SexOrientationEnum.fromId(position) ?: SexOrientationEnum.SELECT
//            }
//        }
//    }
//
//    private fun setupGenderIdentitySpinner() {
//        val options = GenderIdentityEnum.toList()
//        this.binding.spnGenderIdentity.adapter = ArrayAdapter(
//            this,
//            android.R.layout.simple_list_item_1,
//            options
//        )
//
//        this.binding.spnGenderIdentity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//            override fun onNothingSelected(parent: AdapterView<*>?) {}
//
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                ToPlanRegisterViewModel.genderIdentityEnum =
//                GenderIdentityEnum.fromId(position) ?: GenderIdentityEnum.SELECT
//            }
//        }
//    }

    private fun applyMasks() {
        this.binding.edtRg.addTextChangedListener(RgMask.insert(this.binding.edtRg))

        this.binding.edtCpf.addTextChangedListener(CpfMask.insert(this.binding.edtCpf))

        val addLineNumberFormatter = PhoneMask(WeakReference<EditText?>(this.binding.edtPhone))
        this.binding.edtPhone.addTextChangedListener(addLineNumberFormatter);
    }

//    private fun setupListeners() {
//        this.binding.btnFinish.setOnClickListener {
//            // TODO Validar campos
//            if (validateFields()) {
//                setToPlanRegisterModel()
//                ToPlanRegisterViewModel.putIntoBd()
//            } else {
//                // TODO Mostrar que tem algo errado e dar foco
//            }
//        }
//    }

    private fun validateFields(): Boolean {
        // TODO Validar todos os campos (Botão Finalizar)
        return true
    }

//    private fun setToPlanRegisterModel() {
//        ToPlanRegisterViewModel.ToPlanRegisterModel =
//            ToPlanRegisterModel(
//                name = this.binding.edtName.text.toString(),
//                sexOrientation =  ToPlanRegisterViewModel.sexOrientationEnum,
//                genderIdentity =  ToPlanRegisterViewModel.sexOrientationEnum,
//                cpf = this.binding.edtCpf.text.toString()
//                    .replace(".","")
//                    .replace("-","")
//                    .trim(),
//                email = this.binding.edtEmail.text.toString(),
//                phone = this.binding.edtPhone.text.toString()
//                    .replace("(","")
//                    .replace(")","")
//                    .replace("-","")
//                    .replace(" ","")
//                    .trim()
//            )
//    }
}