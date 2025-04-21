package com.mamadiyorov_lazizbek.a4piic1suz_ver12.screens.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.databinding.DialogDeleteBinding
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.databinding.DialogExitBinding
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.databinding.DialogHelpBinding

class ExitDialog(context: Context): Dialog(context) {
    private lateinit var binding: DialogExitBinding

    private var openMenuScreen: (() -> Unit) ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogExitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.noBtn.setOnClickListener {
            dismiss()
        }

        binding.yesBtn.setOnClickListener {
            openMenuScreen?.invoke()
           // deleteButton?.invoke()
            dismiss()
        }

        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.MATCH_PARENT)
        window?.setBackgroundDrawableResource(android.R.color.transparent)

    }

    fun setOpenMenuScreen(block: (() -> Unit)){
        openMenuScreen = block
    }
}