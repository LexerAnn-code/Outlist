package com.ankit.mvvmtodo.Util

import android.content.Intent
import com.ankit.mvvmtodo.TodoRecord
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ankit.mvvmtodo.R
import com.ankit.mvvmtodo.TodoFolder
import com.ankit.mvvmtodo.TodoViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_folder_todo.*
import kotlinx.android.synthetic.main.dialog.*
import kotlinx.android.synthetic.main.dialog_v2.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.ArrayList

class BottomSheetFragementFolderMore(ini: TodoFolder): BottomSheetDialogFragment() {
private var fragmentView:View?=null
    private val ui=ini
    private val postViewM by viewModel<TodoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView=inflater.inflate(R.layout.dialog_v2,container,false)

        return  fragmentView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            initView(ui)
    }

    private fun initView(pass: TodoFolder) {
        debugger("FOLEDRS ALL->>${pass.folderName}")
foldername_dialog.text=pass.folderName
sheet_delete_dialog.setOnClickListener {
    val folder=TodoFolder(pass.folderId,pass.folderName)
    postViewM.deleteFoldersView(folder)
    Toast.makeText(this.requireContext(),"Deleted",Toast.LENGTH_LONG).show()
    folderTextInput_dialog.setText("")
    dismiss()
}

        sheet_rename_dialog.setOnClickListener {
            folder_options_dialog.visibility=View.VISIBLE

        }
        folderCancel_dialog.setOnClickListener {
            folder_options_dialog.visibility=View.GONE
        }
        folderSave_dialog.setOnClickListener {



            if(folderTextInput_dialog.text.isNullOrEmpty()){
                FolderInputLayout_dialog.error=getString(R.string.errorRename)
                folderTextInput_dialog.addTextChangedListener(object :TextWatcher{
                    override fun afterTextChanged(p0: Editable?) {

                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                      if(p0.isNullOrEmpty()){

                      }
                        else{
                          FolderInputLayout_dialog.error=null
                      }
                    }

                })
            }
            else {


                val folders_rename = TodoFolder(pass.folderId, folderName = folderTextInput_dialog.text.toString())
                postViewM.upDateFoldersView(folders_rename)
                Toast.makeText(this.requireContext(), "Renamed", Toast.LENGTH_LONG).show()
                folder_options_dialog.visibility = View.GONE
                folderTextInput_dialog.setText("")

                dismiss()
            }
        }





//        sheet_delete.setOnClickListener {
//            val message=
//                TodoRecord(
//                    pass.todoId,
//                    pass.userCreatedFolderId,
//                    pass.title,
//                    pass.content
//                )
//            postViewM.deleteTdo(message)
//
//            requireActivity().onBackPressed()
////        deleteFloat.setOnClickListener {
////            postViewM.deleteTdo(message)
////
////        }
//        }
//        sheet_send.setOnClickListener {
//            val dataToSend:ArrayList<String> = arrayListOf(
//                pass.title,pass.content
//            )
//val sendIntent=Intent().apply {
//action=Intent.ACTION_SEND
//  putExtra(Intent.EXTRA_TEXT,"$dataToSend")
//    type="text/plain"
//}
//            debugger("$dataToSend")
//            startActivity(sendIntent)
//        }
//        pinSave.setOnClickListener {
//            pinBoarder.visibility=View.GONE
//               val message= TodoRecord(
//                   pass.todoId,
//                   pass.userCreatedFolderId,
//                   pass.title,
//                   pass.content,
//                   pass.date,
//                   password = pinTextInput.text.toString()
//               )
//               postViewM.updateTodoWithPin(message)
//
//        }
//        pinCancel.setOnClickListener{
//            pinBoarder.visibility=View.GONE
//        }
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        pinCancel.setOnClickListener{
//            pinBoarder.visibility=View.GONE
//        }
//    }
//    fun goback(){
//
//    }


}