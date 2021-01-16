package com.ricardo.aulamvvm.data.repository

import com.ricardo.aulamvvm.data.api.ApiHelper
import com.ricardo.aulamvvm.data.model.User
import io.reactivex.Single

class MainRepository(private val apiHelper: ApiHelper) {

    fun getUsers(): Single<List<User>> {
        return apiHelper.getUsers()
    }

}