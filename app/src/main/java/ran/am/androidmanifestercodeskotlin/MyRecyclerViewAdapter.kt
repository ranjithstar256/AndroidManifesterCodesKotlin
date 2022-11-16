package ran.am.androidmanifestercodeskotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ran.am.androidmanifestercodeskotlin.MyRecyclerViewAdapter.ViewHolderClass

class MyRecyclerViewAdapter internal constructor(context: Context?, data: List<String>) :
    RecyclerView.Adapter<ViewHolderClass>() {
    private val mData: List<String>
    private val mInflater: LayoutInflater

    // data is passed into the constructor
    init {
        mInflater = LayoutInflater.from(context)
        mData = data
    }

    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val view = mInflater.inflate(R.layout.recyclerview_row, parent, false)
        return ViewHolderClass(view)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val animal = mData[position]
        holder.myTextView.text = animal
    }

    // total number of rows
    override fun getItemCount(): Int {
        return mData.size
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolderClass internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var myTextView: TextView

        init {
            myTextView = itemView.findViewById(R.id.tvAnimalName)
        }
    }
}