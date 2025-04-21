package com.mamadiyorov_lazizbek.a4piic1suz_ver12.screens.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.databinding.DialogWinBinding

class WinDialog(context: Context) : Dialog(context) {
    private var nextLevel: (() -> Unit)? = null
    private lateinit var binding: DialogWinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogWinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextLevel.setOnClickListener {
            nextLevel?.invoke()
            dismiss()
        }
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        setCancelable(false)
    }

    fun setNextLevel(block: (() -> Unit)) {
        nextLevel = block
    }
}