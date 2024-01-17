package com.vanskarner.cleanexamplekt.ui.user

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.vanskarner.cleanexamplekt.R
import com.vanskarner.cleanexamplekt.databinding.DialogErrorBinding
import com.vanskarner.cleanexamplekt.databinding.DialogUserBinding
import com.vanskarner.cleanexamplekt.databinding.DialogUsersBinding
import com.vanskarner.cleanexamplekt.databinding.FragmentUserBinding
import com.vanskarner.cleanexamplekt.ui.ProgressDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserFragment : Fragment() {

    @Inject
    lateinit var progressDialog: ProgressDialog
    private lateinit var binding: FragmentUserBinding
    private val viewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupView()
        setupViewModel()
    }

    private fun setupView() {
        binding.btnSave.setOnClickListener { viewModel.saveUser(getName(), getAge()) }
        binding.btnList.setOnClickListener { viewModel.userList() }
        binding.btnFind.setOnClickListener { viewModel.findUser(getUserId()) }
    }

    private fun setupViewModel() {
        viewModel.progress.observe(viewLifecycleOwner) { setProgressVisibility(it) }
        viewModel.user.observe(viewLifecycleOwner) { showUser(it) }
        viewModel.userList.observe(viewLifecycleOwner) { showUserList(it) }
        viewModel.msgUserAdded.observe(viewLifecycleOwner) { showAddedUserMsg() }
        viewModel.msgError.observe(viewLifecycleOwner) { showError(it) }
    }

    private fun showUserList(userList: List<UserModel>) {
        val usersBinding = DialogUsersBinding.inflate(layoutInflater)
        val adapter = UserAdapter()
        usersBinding.rcvItems.adapter = adapter
        adapter.updateList(userList.toMutableList())
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.label_users)
            .setView(usersBinding.root)
            .create().show()
    }

    private fun showAddedUserMsg() {
        binding.edtName.text.clear()
        binding.edtAge.text.clear()
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.label_info)
            .setMessage(R.string.msg_user_added)
            .create().show()
    }

    private fun showUser(userModel: UserModel) {
        binding.edtIdUser.text.clear()
        val userBinding = DialogUserBinding.inflate(layoutInflater)
        userBinding.txtId.text = userModel.id.toString()
        userBinding.txtName.text = userModel.name
        userBinding.txtAge.text = userModel.age.toString()
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.label_user)
            .setView(userBinding.root)
            .create().show()
    }

    private fun showError(msg: String) {
        hideKeyBoard()
        val errorBinding = DialogErrorBinding.inflate(layoutInflater)
        errorBinding.txtErrorMsg.text = msg
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.label_error)
            .setView(errorBinding.root)
            .create().show()
    }

    private fun hideKeyBoard() {
        val view: View = binding.root
        val activity = requireActivity()
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun setProgressVisibility(isVisible: Boolean) {
        if (isVisible) progressDialog.showProgress(parentFragmentManager)
        else progressDialog.hideProgress()
    }

    private fun getName() = binding.edtName.text.toString()
    private fun getAge() = binding.edtAge.text.toString().toIntOrNull() ?: 0
    private fun getUserId() = binding.edtIdUser.text.toString().toIntOrNull() ?: 0

}