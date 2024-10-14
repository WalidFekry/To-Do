package com.walid.todo.base

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    var progressDialog: ProgressDialog? = null

    fun showLoadingDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage("Loading...")
        progressDialog?.show()
    }

    fun hideLoadingDialog() {
        progressDialog?.dismiss()
    }


    var alertDialog: AlertDialog? = null

    fun showMessage(
        message: String,
        posActionTitle: String? = null,
        posAction: DialogInterface.OnClickListener? = null,
        negActionTitle: String? = null,
        negAction : DialogInterface.OnClickListener? = null,cancelLabel:Boolean = true)
    {
        var messageDialogBuilder = AlertDialog.Builder(this)
        messageDialogBuilder.setMessage(message)
        if (posAction != null){
            messageDialogBuilder.setPositiveButton(
            posActionTitle
            ) { p0, p1 ->
                p0.dismiss()
            }
        }

        if (negAction != null){
            messageDialogBuilder.setNegativeButton(
                negActionTitle
            ) { p0, p1 ->
           p0.dismiss()
            }
        }
        messageDialogBuilder.setCancelable(cancelLabel)

        alertDialog = messageDialogBuilder.show()

    }

}