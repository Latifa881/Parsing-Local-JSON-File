package com.example.parsinglocaljsonfile

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_row.view.*
import java.security.MessageDigest


class RecyclerViewAdapter(private val details: ArrayList<details>, val context: Context) :
    RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = details[position]
        holder.itemView.apply {
            tvTitle.text = data.title
            tvDate.text = data.date
            tvCopyright.text = data.copyright
            Glide.with(context)
                .load(data.url)
                .into(ivImage)
            ivExplanation.setOnClickListener {
                showAlertDialog(data.explanation)
            }

        }
    }

    override fun getItemCount() = details.size
    fun showAlertDialog( message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Explanation")
        builder.setMessage(message)
        builder.setIcon(android.R.drawable.ic_dialog_info)
        builder.setNegativeButton("Close", DialogInterface.OnClickListener {
                dialog, id -> dialog.cancel()
        })
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(true)
        alertDialog.show()
    }
}
