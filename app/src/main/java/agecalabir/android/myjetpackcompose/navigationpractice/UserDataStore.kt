package agecalabir.android.myjetpackcompose.navigationpractice

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// 1 Create DataStore
val Context.datastore by preferencesDataStore(name = "user_preferences")
class UserDataStore(private val context: Context){
    companion object{
        // 2. create key ( like : user_name )
        val Name_KEY = stringPreferencesKey("saved_user_name")
    }
    // 2 Data Save (Work BackGround)
    suspend fun saveName(name : String){
        context.datastore.edit { preferences ->
            preferences[Name_KEY] = name
        }
    }
    // 4 Read Data
    val getName : Flow<String> = context.datastore.data.map{ preferences ->
        preferences[Name_KEY] ?: "No Data Found"
    }
}