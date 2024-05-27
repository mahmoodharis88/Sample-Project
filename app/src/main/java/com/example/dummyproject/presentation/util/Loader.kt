package com.example.dummyproject.presentation.util

import android.app.ProgressDialog
import androidx.fragment.app.FragmentActivity

class Loader {
    companion object {
        var progressDialog: ProgressDialog? = null

        fun showLoader(activity: FragmentActivity?) {
            // hideLoader();
            if (progressDialog == null) {
                progressDialog = ProgressDialog(activity)
                progressDialog?.setCanceledOnTouchOutside(false)
                progressDialog?.setCancelable(false)
                progressDialog!!.show()
            }
        }

        fun hideLoader() {
            if (progressDialog != null) {
                progressDialog!!.dismiss()
                progressDialog = null
            }
        }
    }

}