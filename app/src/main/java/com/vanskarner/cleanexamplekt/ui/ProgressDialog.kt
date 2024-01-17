package com.vanskarner.cleanexamplekt.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.vanskarner.cleanexamplekt.databinding.DialogProgressBinding
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProgressDialog @Inject constructor() : DialogFragment() {
    private lateinit var binding: DialogProgressBinding

    companion object {
        private const val TAG = "ProgressDialogFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DialogProgressBinding.inflate(inflater, container, false)
        isCancelable = false
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root
    }

    fun showProgress(fragmentManager: FragmentManager) = show(fragmentManager, TAG)

    fun hideProgress() = dismiss()

}