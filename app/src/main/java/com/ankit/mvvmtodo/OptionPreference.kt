package com.ankit.mvvmtodo

import android.content.Context
import android.content.SharedPreferences
import com.ankit.mvvmtodo.Util.Constants

class OptionPreference(context: Context){
        private  val sharedPreferences=context.getSharedPreferences(Constants.PREFS_KEY ,Context.MODE_PRIVATE)

fun storeCheck(layoutKey:String){
with(sharedPreferences.edit()){
putString(Constants.PREFS_AUTH_VALUE,layoutKey)
    apply()
}


}

    fun getCheck(KEY_NAME:String):String?{
return sharedPreferences.getString(KEY_NAME,null)
    }




}