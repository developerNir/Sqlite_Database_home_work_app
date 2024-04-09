package com.example.improvement.ViewModel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.improvement.Service.Repository.todoRepositroy.todoRepositroy;

public class TodoViewModel extends ViewModel {


    private final todoRepositroy todoRepository;


    // ======================= todo view model and todo repo add ===================
    public TodoViewModel() {
        super();
        todoRepository = todoRepositroy.getInstance();
    }





}
