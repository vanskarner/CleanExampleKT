package com.vanskarner.cleanexamplekt.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanskarner.cleanexamplekt.ui.errors.ErrorFilter
import com.vanskarner.user.UserComponent
import com.vanskarner.user.businesslogic.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userComponent: UserComponent,
    private val errorFilter: ErrorFilter
) : ViewModel() {
    private val _progress = MutableLiveData<Boolean>()
    private val _user = MutableLiveData<UserModel>()
    private val _userList = MutableLiveData<List<UserModel>>()
    private val _msgUserAdded = MutableLiveData<Unit>()
    private val _msgError = MutableLiveData<String>()

    val progress: LiveData<Boolean> = _progress
    val user: LiveData<UserModel> = _user
    val userList: LiveData<List<UserModel>> = _userList
    val msgUserAdded: LiveData<Unit> = _msgUserAdded
    val msgError: LiveData<String> = _msgError

    fun saveUser(name: String, age: Int) {
        viewModelScope.launch {
            _progress.value = true
            val userData = UserData(
                name = name,
                age = age
            )
            userComponent.save(userData)
                .apply { _progress.value = false }
                .onSuccess { _msgUserAdded.value = it }
                .onFailure { _msgError.value = errorFilter.filter(it) }
        }
    }

    fun userList() {
        viewModelScope.launch {
            _progress.value = true
            userComponent.getList()
                .map { list -> list.map { it.toModel() } }
                .apply { _progress.value = false }
                .onSuccess { _userList.value = it }
                .onFailure { _msgError.value = errorFilter.filter(it) }
        }
    }

    fun findUser(id: Int) {
        viewModelScope.launch {
            _progress.value = true
            userComponent.find(id)
                .map { it.toModel() }
                .apply { _progress.value = false }
                .onSuccess { _user.value = it }
                .onFailure { _msgError.value = errorFilter.filter(it) }
        }
    }

}