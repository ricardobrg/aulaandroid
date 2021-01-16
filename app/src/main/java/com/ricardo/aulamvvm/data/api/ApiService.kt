package com.ricardo.aulamvvm.data.api

import com.ricardo.aulamvvm.data.model.User
import io.reactivex.Single

interface ApiService {

    fun getUsers(): Single<List<User>>

}