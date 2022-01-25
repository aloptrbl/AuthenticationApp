package io.zextech.authenticationapp.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.zextech.authenticationapp.R
import io.zextech.authenticationapp.views.adapters.PopularCardAdapter
import io.zextech.authenticationapp.views.adapters.RecomendedCardAdapter


class HomeFragment : Fragment() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val popularimages =
        arrayListOf(R.drawable.zilahomes, R.drawable.motel, R.drawable.beach, R.drawable.forest)
    private val populartitles = arrayListOf("Zila Homes", "Su Hotel", "Sauna Hotel", "D Forest")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var carouselView1 = view.findViewById<RecyclerView>(R.id.carouselView1)
        var carouselView2 = view.findViewById<RecyclerView>(R.id.carouselView2)
        val layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        carouselView1.layoutManager = layoutManager
        carouselView1.apply {
            adapter = PopularCardAdapter(popularimages, populartitles)
        }
        val layoutManager2 =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        carouselView2.layoutManager = layoutManager2
        carouselView2.apply {
            adapter = RecomendedCardAdapter(popularimages, populartitles)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_home, container, false)
    }
}
