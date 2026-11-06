package agecalabir.android.myjetpackcompose.jetpackcompose

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CounterViewModel : ViewModel(){
    private var _count = MutableStateFlow(0)
    val count : StateFlow<Int> = _count.asStateFlow()
    fun incrementCount(){
        _count.value++
    }
    fun decrementCount(){
        if (_count.value > 0){
            _count.value--
        }
    }
}
/*class CounterViewModel : ViewModel() {

    var count by mutableIntStateOf(0)
        private set

    fun incrementCount() {
        count++
    }

    fun decrementCount() {
        if (count > 0) {
            count--
        }
    }
}*/