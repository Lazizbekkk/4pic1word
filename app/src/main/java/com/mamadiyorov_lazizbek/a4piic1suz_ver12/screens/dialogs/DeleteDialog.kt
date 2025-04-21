package com.mamadiyorov_lazizbek.a4piic1suz_ver12.screens.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.databinding.DialogDeleteBinding
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.databinding.DialogHelpBinding

class DeleteDialog(context: Context): Dialog(context) {
    private lateinit var binding: DialogDeleteBinding

    private var deleteButton: (() -> Unit) ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.canselBtn.setOnClickListener {
            dismiss()
        }

        binding.deleteBtn.setOnClickListener {
            deleteButton?.invoke()
            dismiss()
        }

        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.MATCH_PARENT)
        window?.setBackgroundDrawableResource(android.R.color.transparent)

    }

    fun setDeleteBtnClicked(block: (() -> Unit)){
        deleteButton = block
    }
}