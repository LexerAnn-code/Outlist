package com.ankit.mvvmtodo.Util

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.ankit.mvvmtodo.R
import com.ankit.mvvmtodo.TodoRecord
import com.ankit.mvvmtodo.UI.EditTodoActivity
import kotlinx.android.synthetic.main.activity_password_verify.*

class PasswordVerify : AppCompatActivity() {
    companion object{
        const val EXTRA_POST="EXTRA_POST"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_verify)

        viewNote.setOnClickListener {
            lockLayput.visibility=View.VISIBLE
        }

        val passwordIntent=intent.getParcelableExtra<TodoRecord>(
            EXTRA_POST
        )
        PasswordButtonCancel.setOnClickListener {
            lockLayput.visibility=View.GONE
            PasswordInputText.setText("")
        }

        checkPasswordButton.setOnClickListener {
            if( passwordIntent.password!=PasswordInputText.text.toString()){

                PasswordInputLayout.error=getString(R.string.errorIncorrect)
                PasswordInputText.addTextChangedListener(object:TextWatcher{
                    override fun afterTextChanged(p0: Editable?) {

                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        if(p0.isNullOrEmpty()){

                        }
                        else{
                            PasswordInputLayout.error=null
                        }
                    }

                })
            }
            else{

                    val moveTo=Intent(this@PasswordVerify,
                        EditTodoActivity::class.java).apply {
                        putExtra(EditTodoActivity.EXTRA_POST,passwordIntent)
                    }
                    startActivity(moveTo)


            }
//            PasswordInputText.text?.isNotEmpty().apply {
//                if(passwordIntent.password==PasswordInputText.text.toString()){
//                    val moveTo=Intent(this@PasswordVerify,EditTodoActivity::class.java).apply {
//                        putExtra(EditTodoActivity.EXTRA_POST,passwordIntent)
//                    }
//                    startActivity(moveTo)
//                }
//                else{
//                    debugger("WRONG PASSWORD")
//                }
//            }
            PasswordInputText.setText("")
        }

}
}