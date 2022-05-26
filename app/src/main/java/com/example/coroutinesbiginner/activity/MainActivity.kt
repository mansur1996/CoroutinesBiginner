package com.example.coroutinesbiginner.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coroutinesbiginner.databinding.ActivityMainBinding
import com.example.coroutinesbiginner.model.User
import com.example.coroutinesbiginner.retrofit.ApiClient
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var binding: ActivityMainBinding
    private val TAG = "MainActivity"

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        job = Job()
        initViews()
    }

    private fun initViews() {

        launch{
            val user1 = fetchUserAsync(1)
            val user2 = fetchUserWithContext(2)
            binding.tv1.text = "${user1.name} ${user2.name}"
        }

        fetchUserLaunch()

    }

    private fun fetchUserLaunch() {
        GlobalScope.launch(Dispatchers.Main) {
            val userById = ApiClient.apiService.getUserById(2)
            binding.tv2.text = userById.name
        }
    }

    private suspend fun fetchUserAsync(id: Int): User {
        return GlobalScope.async(Dispatchers.IO) {
            ApiClient.apiService.getUserById(id)
        }.await()
        /**
         * async -> Parellel muhitda ishlaydi
         * async -> bir nechata natijalar parallel muhitda olinadi
         */
    }

    private suspend fun fetchUserWithContext(id: Int): User {
        return withContext(Dispatchers.IO) {
            ApiClient.apiService.getUserById(id)
        }
        /**
         * withcontext -> ketma-ket muhitda ishlaydi.
         * withcontext -> bitta natija olinadi
         */
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }

}