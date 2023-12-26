package com.termproject.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.termproject.databinding.DialogCoinBinding
import com.termproject.db.user.User
import com.termproject.db.user.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreditDialog(context: Context, userViewModel: UserViewModel, creditBtn: Button) :
    Dialog(context) {
    private var userViewModel: UserViewModel
    private var creditBtn: Button
    lateinit var binding: DialogCoinBinding

    init {
        this.userViewModel = userViewModel
        this.creditBtn = creditBtn
    }


    private lateinit var existingUser: User

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        CoroutineScope(Dispatchers.Main).launch {
            existingUser = userViewModel.getUser()

            if (existingUser != null) {


                binding.tvCoinAmount.setText(existingUser.credit.toString())
            }


        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DialogCoinBinding.inflate(layoutInflater)

        setContentView(binding.root)





        binding.btnClose.setOnClickListener {
            dismiss()
        }

        binding.btnResetCoin.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                existingUser.credit = 10000
                creditBtn.text = "10000"
                userViewModel.updateUser(existingUser)
                Toast.makeText(context, "Your credit is resetted successfully", Toast.LENGTH_SHORT)
                    .show()
                dismiss()
            }
        }
    }
}