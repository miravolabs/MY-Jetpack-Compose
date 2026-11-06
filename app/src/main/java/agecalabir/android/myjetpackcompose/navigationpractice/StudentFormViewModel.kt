package agecalabir.android.myjetpackcompose.navigationpractice

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class StudentUIState(
    val name : String = "",
    val id : String = "",
    val roll : String = "",
    val address : String = "",
    val section : String = "",
    val favSubject : String = ""
)

class StudentFormViewModel(application: Application) : AndroidViewModel(application) {
    // data store instanse
    private var dataStore = UserDataStore(application)

    // Live name preview form datastore
    val savedPersistentName: StateFlow<String> = dataStore.getName
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = "Loading...."
        )
    private var _uiState = MutableStateFlow(StudentUIState())
    val uiState : StateFlow<StudentUIState> = _uiState.asStateFlow()

    // Screen 1 name update and save name permanently

    fun updateName(name : String){
        _uiState.update { it.copy(name = name) }
        viewModelScope.launch {
            dataStore.saveName(name)
        }
    }
    // screen 2
    fun updateIdAndRoll(id: String,roll: String){
        _uiState.update { it.copy(id = id, roll = roll) }
    }
    // screen 3
    fun updateAddress(address: String){
        _uiState.update { it.copy(address = address) }
    }
    // screen 4
    fun updateSectionAndSubject(section: String, subject : String){
        _uiState.update { it.copy(section= section, favSubject = subject ) }
    }
    // to reset to form
    fun resetForm(){
        _uiState.value= StudentUIState()
    }
}