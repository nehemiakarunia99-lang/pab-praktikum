package com.nehem.pabweek3.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nehem.pabweek3.data.TodoItem
import com.nehem.pabweek3.data.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    private val _todos = MutableStateFlow<List<TodoItem>>(emptyList())
    val todos: StateFlow<List<TodoItem>> = _todos.asStateFlow()

    private var nextId = 1

    init {
        viewModelScope.launch {
            repository.todosFlow.collect { savedTodos ->
                _todos.value = savedTodos
                nextId = (savedTodos.maxOfOrNull { it.id } ?: 0) + 1
            }
        }
    }

    fun addTodo(title: String) {
        val newTodo = TodoItem(id = nextId, title = title)
        nextId += 1
        val updated = _todos.value + newTodo
        _todos.value = updated
        persist(updated)
    }

    fun toggleDone(id: Int, checked: Boolean) {
        val updated = _todos.value.map {
            if (it.id == id) it.copy(isDone = checked) else it
        }
        _todos.value = updated
        persist(updated)
    }

    fun deleteTodo(id: Int) {
        val updated = _todos.value.filter { it.id != id }
        _todos.value = updated
        persist(updated)
    }

    private fun persist(todos: List<TodoItem>) {
        viewModelScope.launch {
            repository.saveTodos(todos)
        }
    }
}