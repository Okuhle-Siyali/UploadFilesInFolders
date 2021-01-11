package com.example.uploadfilesinfolders

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uploadfilesinfolders.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), FolderViewAdapter.OnItemClickListener {
    private lateinit var binding : ActivityMainBinding
    private var newFolderName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addNewFolder()

    }

    private fun addNewFolder() {
        binding.addNewFolder.setOnClickListener {
            dialog()
        }
    }

    private fun dialog() {
        val folderNameDialog = AlertDialog.Builder(this)
        folderNameDialog.setTitle("Enter Folder Name")
        val folderName = EditText(this)
        folderName.inputType = InputType.TYPE_CLASS_TEXT
        folderNameDialog.setView(folderName)
        val mDialogPositive = DialogInterface.OnClickListener { p0, _ ->
            if (!folderName.text.isNullOrBlank()) {
                newFolderName = folderName.text.toString()
                ItemsInfo.numberOfFolders++
                ItemsInfo.folderIndex++
                ItemsInfo.folderNameList.add(ItemsInfo.folderIndex, newFolderName)
                binding.listItems.adapter = FolderViewAdapter(ItemsInfo.folderNameList, this)
                binding.listItems.layoutManager = LinearLayoutManager(this)
            }
            p0.cancel()
        }
        folderNameDialog.setPositiveButton("Create Folder", mDialogPositive )
        folderNameDialog.setNegativeButton("Cancel", null)
        folderNameDialog.show()
    }

    override fun onItemClick(position: Int) {

    }

}