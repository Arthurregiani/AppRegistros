package br.edu.ifsp.dmo.appregistros.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.dmo.appregistros.data.model.Registro
import br.edu.ifsp.dmo.appregistros.data.repository.RegistroRepository
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: RegistroRepository) : ViewModel() {
    private val _saved = MutableLiveData<Boolean>()
    val saved: LiveData<Boolean> get() = _saved

    private val _isUpdate = MutableLiveData<Boolean>()
    val isUpdate: LiveData<Boolean> get() = _isUpdate

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> get() = _title

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> get() = _description

    private val _dateTime = MutableLiveData<String>()
    val dateTime: LiveData<String> get() = _dateTime

    private var currentId: Long = 0 // Adicionado para armazenar o ID atual

    fun showRecord(id: Long) {
        viewModelScope.launch {
            val record = repository.findById(id)
            _title.value = record?.title
            _description.value = record?.description
            _dateTime.value = record?.dateTime
            _isUpdate.value = true
            currentId = id // Armazena o ID atual
        }
    }

    fun saveRecord(title: String, description: String, dateTime: String) {
        viewModelScope.launch {
            val success = if (_isUpdate.value == true) {
                // Atualiza o registro existente
                repository.update(Registro(id = currentId, title = title, description = description, dateTime = dateTime))
            } else {
                // Insere um novo registro
                repository.insert(Registro(title = title, description = description, dateTime = dateTime))
            }
            _saved.value = success
        }
    }
}
