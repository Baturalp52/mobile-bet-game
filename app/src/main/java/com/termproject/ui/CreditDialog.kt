package com.termproject.ui


import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.termproject.R
import com.termproject.db.user.User
import com.termproject.db.user.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreditDialog(context: Context, userViewModel: UserViewModel, creditBtn: Button) :
    MaterialAlertDialogBuilder(context) {
    private var userViewModel: UserViewModel
    private var creditBtn: Button

    private lateinit var existingUser: User

    init {
        this.userViewModel = userViewModel



        CoroutineScope(Dispatchers.Main).launch {
            existingUser = userViewModel.getUser()

            if (existingUser != null) {

                setMessage(
                    existingUser.credit.toString()
                )

            }


        }

        this.creditBtn = creditBtn
        setTitle("Your Credits:")







        setNegativeButton(
            "Close"
        ) { dialog, _ ->
            dialog.dismiss()
        }

        setPositiveButton(
            "Reset Credits"
        ) { dialog, _ ->
            CoroutineScope(Dispatchers.Main).launch {
                existingUser.credit = 10000
                creditBtn.text = "10000"

                userViewModel.updateUser(existingUser)
                Toast.makeText(context, "Your credit is resetted successfully", Toast.LENGTH_SHORT)
                    .show()
                dialog.dismiss()
            }
        }


    }


}