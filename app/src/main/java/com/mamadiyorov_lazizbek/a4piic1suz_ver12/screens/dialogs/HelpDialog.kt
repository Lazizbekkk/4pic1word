package com.mamadiyorov_lazizbek.a4piic1suz_ver12.screens.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.databinding.DialogHelpBinding

class HelpDialog(context: Context): Dialog(context) {
    private lateinit var binding: DialogHelpBinding

    private var helpButton: (() -> Unit) ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogHelpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.canselBtn.setOnClickListener {
            dismiss()
        }

        binding.helpBtn.setOnClickListener {
            helpButton?.invoke()
            dismiss()
        }

        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.MATCH_PARENT)
        window?.setBackgroundDrawableResource(android.R.color.transparent)

    }

    fun setHelpBtnClicked(block: (() -> Unit)){
        helpButton = block
    }
}