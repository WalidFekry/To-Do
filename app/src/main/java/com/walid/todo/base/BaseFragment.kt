package com.walid.todo.base

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.util.Log
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    var progressDialog: ProgressDialog? = null

    fun showLoadingDialog() {
        progressDialog = ProgressDialog(requireContext())
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
        negAction : DialogInterface.OnClickListener? = null, cancelLabel:Boolean = true)
    {
        var messageDialogBuilder = AlertDialog.Builder(requireContext())
        messageDialogBuilder.setMessage(message)
        if (posAction != null){
            messageDialogBuilder.setPositiveButton(posActionTitle,
                posAction)
        }

        if (negAction != null){
            messageDialogBuilder.setNegativeButton(
                negActionTitle,negAction)
        }
        messageDialogBuilder.setCancelable(cancelLabel)

        alertDialog = messageDialogBuilder.show()

    }

}