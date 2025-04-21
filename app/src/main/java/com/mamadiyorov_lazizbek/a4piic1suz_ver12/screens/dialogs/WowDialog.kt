package com.mamadiyorov_lazizbek.a4piic1suz_ver12.screens.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.databinding.DialogWowBinding

class WowDialog(context: Context): Dialog(context) {
    private lateinit var binding: DialogWowBinding
    private var restartClicked: (() -> Unit) ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogWowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.restartBtn.setOnClickListener {
            restartClicked?.invoke()
            dismiss()
        }
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        setCancelable(false)
    }

    fun setRestartClicked(block: (() -> Unit)){
        restartClicked = block
    }
}