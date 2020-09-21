package com.ankit.mvvmtodo.util

import android.content.Intent
import com.ankit.mvvmtodo.model.TodoRecord
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ankit.mvvmtodo.R
import com.ankit.mvvmtodo.viewmodel.TodoViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog.*
import kotlinx.android.synthetic.main.dialog.lockText
import kotlinx.android.synthetic.main.dialog_v2.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.ArrayList

class BottomSheetFragement(ini: TodoRecord): BottomSheetDialogFragment() {
private var fragmentView:View?=null
    private val ui=ini
    private val postViewM by viewModel<TodoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView=inflater.inflate(R.layout.dialog,container,false)

        return  fragmentView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            initView(ui)
    }

    private fun initView(pass: TodoRecord) {
        if(pass.password!=null){
            lockText.text="Remove"
        }

        sheet_lock.setOnClickListener {


            if(pass.password!=null){
                pinBoarder.visibility=View.GONE

                val message= TodoRecord(
                        pass.todoId,
                        pass.userCreatedFolderId,
                        pass.title,
                        pass.content,
                        pass.date,
                        password = null
                )

                postViewM.updateTodoWithPin(message)
                requireActivity().onBackPressed()
            }
            else
            {
                pinBoarder.visibility=View.VISIBLE

            }

            debugger("New Data ${pass?.title}")
            lockText.text="Remove"
        }
        sheet_delete.setOnClickListener {
            val message=
                    TodoRecord(
                            pass.todoId,
                            pass.userCreatedFolderId,
                            pass.title,
                            pass.content

                    )
            postViewM.deleteTdo(message)

            requireActivity().onBackPressed()
//        deleteFloat.setOnClickListener {
//            postViewM.deleteTdo(message)
//
//        }
        }
        sheet_send.setOnClickListener {
            val dataToSend:ArrayList<String> = arrayListOf(
                pass.title,pass.content
            )
val sendIntent=Intent().apply {
action=Intent.ACTION_SEND
  putExtra(Intent.EXTRA_TEXT,"$dataToSend")
    type="text/plain"
}
            debugger("$dataToSend")
            startActivity(sendIntent)
        }

        pinSave.setOnClickListener {

            if (pinTextInput.text.toString().isNullOrEmpty()) {

                pinInputLayout.error = getString(R.string.emptyPassword)
                pinTextInput.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {

                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        if (p0.isNullOrEmpty()) {

                        } else {
                            pinInputLayout.error = null
                        }
                    }

                })
            } else {
                val message = TodoRecord(
                        pass.todoId,
                        pass.userCreatedFolderId,
                        pass.title,
                        pass.content,
                        pass.date,
                        password = pinTextInput.text.toString()
                )
                postViewM.updateTodoWithPin(message)
                pinBoarder.visibility = View.GONE

            }
        }
//        pinCancel.setOnClickListener{
//            pinBoarder.visibility=View.GONE
//        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        pinCancel.setOnClickListener{
            pinBoarder.visibility=View.GONE
        }
    }
    fun goback(){

    }


}