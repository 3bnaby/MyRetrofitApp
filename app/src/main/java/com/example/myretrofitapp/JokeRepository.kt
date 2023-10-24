package com.example.myretrofitapp

import com.example.myretrofitapp.MODEL.JokeResponse
import com.example.myretrofitapp.MODEL.State
import com.example.myretrofitapp.networking.API
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class JokeRepository {
    suspend fun getRandomJoke() : Flow<State<JokeResponse?>>{
        return flow {
            emit(State.Loading)
            try {
                val result = API.apiService.getRandomJoke()
                if (result.isSuccessful){
                    emit(State.Success(result.body()))
                }else{
                    emit(State.Error(result.message()))
                }
            }catch (e:Exception){
                emit(State.Error(e.message.toString()))
            }

        }
    }
}