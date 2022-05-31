package com.example.queerfy.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.queerfy.databinding.FragmentHomeBinding
import com.example.queerfy.view.residenceRegister.ResidenceRegisterStepOneActivity
import com.example.queerfy.viewModel.HomeViewModel
import com.example.queerfy.viewModel.LoginViewModel
import com.esri.arcgisruntime.ArcGISRuntimeEnvironment
import com.esri.arcgisruntime.geometry.Point
import com.esri.arcgisruntime.geometry.SpatialReferences
import com.esri.arcgisruntime.mapping.ArcGISMap
import com.esri.arcgisruntime.mapping.BasemapStyle
import com.esri.arcgisruntime.mapping.Viewpoint
import com.esri.arcgisruntime.mapping.view.Graphic
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay
import com.esri.arcgisruntime.symbology.SimpleLineSymbol
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol
import com.example.queerfy.model.Property
import com.example.queerfy.services.Api
import retrofit2.Call
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel = HomeViewModel()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        this.binding.btnHost.setOnClickListener {
            val residenceRegisterPage = Intent(activity, ResidenceRegisterStepOneActivity::class.java)

            startActivity(residenceRegisterPage)
        }

        this.binding.btnRegister.setOnClickListener {
            val registerChoosePage = Intent(activity, RegisterChooseActivity::class.java)

            startActivity(registerChoosePage)
        }

        ArcGISRuntimeEnvironment.setApiKey("AAPKed018f593bae4100ad82d70ca5dc209982Jf6BtQdYma4z-gv2djY5FxqdrhcLJy98_WGe6SrkApXpxvPWKPWNPuOAbhKn1O")

        this.binding.mapView.map = ArcGISMap(BasemapStyle.ARCGIS_TOPOGRAPHIC)

        this.binding.mapView.setViewpoint(Viewpoint(-23.558170, -46.661550, 72000.0))

        val graphicsOverlay = GraphicsOverlay()
        this.binding.mapView.graphicsOverlays.add(graphicsOverlay)

        val getProperties = Api.create().getProperties()

        getProperties.enqueue(object: retrofit2.Callback<List<Property>> {
            override fun onResponse(
                call: Call<List<Property>>,
                response: Response<List<Property>>
            ) {

                if (response.isSuccessful) {
                    response.body()?.forEach { item ->
                        val point = Point(item.longitude.toString().toDouble(), item.latitude.toString().toDouble(), SpatialReferences.getWgs84())

                        val simpleMarkerSymbol = SimpleMarkerSymbol(SimpleMarkerSymbol.Style.X, -0xa8cd, 12f)

                        val pointGraphic = Graphic(point, simpleMarkerSymbol)
                        graphicsOverlay.graphics.add(pointGraphic)

                    }

                }
            }

            override fun onFailure(call: Call<List<Property>>, t: Throwable) {
                println(t.message)
            }

        })


        return root
    }

    // TODO
    fun setup() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}