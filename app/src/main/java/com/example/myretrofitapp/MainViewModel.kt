package com.example.myretrofitapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myretrofitapp.MODEL.JokeResponse
import com.example.myretrofitapp.MODEL.State
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {
    val joke = MutableLiveData<State<JokeResponse?>>()
    val repository = JokeRepository()
    init {
        getRandomJoke()
    }

     fun getRandomJoke(){
        viewModelScope.launch {
           repository.getRandomJoke().collect{
               joke.postValue(it)
           }
        }
    }
}