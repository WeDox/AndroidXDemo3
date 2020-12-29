package com.onedream.androidxdemo3.common.vm.random_poetry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @author jdallen
 * @since 2020/12/24
 */
class RandomPoetryViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RandomPoetryViewModel() as T
    }
}
