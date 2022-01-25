package io.zextech.authenticationapp.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.zextech.authenticationapp.R

class RecomendedCardAdapter(var movies: ArrayList<Int>, var moviesTitles: ArrayList<String>) :
    RecyclerView.Adapter<RecomendedCardAdapter.ViewHolder>() {
    lateinit var context: Context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecomendedCardAdapter.ViewHolder {
        context = parent.context!!
        val view = LayoutInflater.from(context).inflate(R.layout.recomended_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecomendedCardAdapter.ViewHolder, position: Int) {
        holder.cardimage.setImageResource(movies[position])
        holder.cardtitle.text = moviesTitles[position]
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        override fun onClick(p0: View?) {

        }

        val cardimage: com.rishabhharit.roundedimageview.RoundedImageView =
            v.findViewById(R.id.cardimage)
        val cardtitle: TextView = v.findViewById(R.id.cardtitle)
        val cardlocation: TextView = v.findViewById(R.id.cardlocation)
    }

}