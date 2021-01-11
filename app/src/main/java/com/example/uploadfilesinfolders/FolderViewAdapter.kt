package com.example.uploadfilesinfolders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.folder_item.view.*

class FolderViewAdapter(
    private val folderNameList : MutableList<String>,
    private val listener : OnItemClickListener
    ) : RecyclerView.Adapter<FolderViewAdapter.FilesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.folder_item, parent, false)
        return FilesViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilesViewHolder, position: Int) {
        if (ItemsInfo.isItFolders) {
            holder.itemImage.setImageResource(R.drawable.folder)
            holder.itemTitle.text = folderNameList[position]
        } else holder.itemImage.setImageResource(R.drawable.pdf)
    }

    override fun getItemCount(): Int {
        return if (ItemsInfo.isItFolders) ItemsInfo.numberOfFolders
        else ItemsInfo.numberOfFilesInEachFolder[ItemsInfo.folderIndex]
    }

    inner class FilesViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var itemImage : ImageView = itemView.item_image
        var itemTitle : TextView = itemView.image_title

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position : Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }

        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}