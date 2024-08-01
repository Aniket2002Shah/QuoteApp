package com.example.quote_app.response_handling

sealed class Response<T>(private val data:T?= null,private val message:String?= null){
    val r_data = data
    val r_message = message
    class Processing<T>:Response<T>(){}
    class Success<T>(private val data: T?):Response<T>(data=data){}
    class Error<T>(private val message: String?= null):Response<T>(message=message){}

}
