package com.example.lektion7externaapi2moshi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.lektion7externaapi2moshi.network.APIClient
import com.example.lektion7externaapi2moshi.network.CharacterResponse
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = APIClient.apiService.fetchCharacters("1")

        client.enqueue(object : retrofit2.Callback<CharacterResponse> {
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                if(response.isSuccessful) {
                    Log.d("characters", ""+response.body())

                    val result = response.body()?.result
                    result?.let {
                        val rv_list = findViewById<RecyclerView>(R.id.rv_list)
                        val listAdapter = ListAdapter(result)
                        rv_list.adapter = listAdapter
                        rv_list.layoutManager = StaggeredGridLayoutManager(
                            3, StaggeredGridLayoutManager.VERTICAL)
                    }
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.e("MainActivity", "This went wrong: " + t )
            }

        })
    }
}