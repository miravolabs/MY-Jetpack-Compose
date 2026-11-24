package agecalabir.android.myjetpackcompose.room_learning

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

// room practice 3 /// 24/11/2026


// Entity
@Entity(tableName = "expense_table")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val title : String,
    val amount : Double,
    val isExpense : Boolean
)

// DAO

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expense_table ORDER BY id DESC")
    fun getAllExpenses() : Flow<List<ExpenseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expanse : ExpenseEntity)

    @Delete
    suspend fun deleteExpense(expanse : ExpenseEntity)

    @Query("DELETE FROM expense_table")
    suspend fun clearAllExpenses()

}

// Database
@Database(entities = [ExpenseEntity::class] , version = 1 , exportSchema = false)
abstract class ExpenseDatabase : RoomDatabase(){
    abstract val expenseDao : ExpenseDao
}

// UI

@Composable
fun SimpleRoomExpenseScreen(){

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val database = remember {
        Room.databaseBuilder(
            context.applicationContext,
            ExpenseDatabase::class.java,
            "expanse_manager.db"
        ).build()
    }

    val expenseList by database.expenseDao.getAllExpenses().collectAsState(initial = emptyList())

    var inputTitle by remember { mutableStateOf("") }
    var inputAmount by remember { mutableStateOf("") }
    var isExpenseType by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(16.dp)
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column {
                Text(
                    text = "Expense Tracker 💰",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Room SQLite Table with Double & Boolean",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            if(expenseList.isNotEmpty()){
                TextButton(
                    onClick = {
                        scope.launch {
                            database.expenseDao.clearAllExpenses()
                        }
                    }
                ) {
                    Text("Clear All", color = MaterialTheme.colorScheme.error)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = inputTitle,
            onValueChange = { inputTitle = it },
            label = { Text("Expense Title / Reason") },
            placeholder = { Text("e.g. Hosting Bill or Server Cost") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = inputAmount,
            onValueChange = { inputAmount = it },
            label = { Text("Amount ($)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(10.dp))
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isExpenseType) "Type: Expense (-)" else "Type: Income (+)",
                fontWeight = FontWeight.SemiBold,
                color = if (isExpenseType) Color(0xFFE11D48) else Color(0xFF16A34A)
            )
            Switch(
                checked = isExpenseType,
                onCheckedChange = { isExpenseType = it }
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Button(
                onClick = {
                    val amountDouble = inputAmount.toDoubleOrNull() ?: 0.0
                    if(inputTitle.isNotBlank() && inputAmount.isNotBlank()){
                        val newExpense = ExpenseEntity(
                            title = inputTitle,
                            amount = amountDouble,
                            isExpense = isExpenseType
                        )
                        scope.launch {
                            database.expenseDao.insertExpense(newExpense)
                        }
                        inputTitle = ""
                        inputAmount = ""
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2563EB))
        ) {
                Text("Add Record to Room", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = expenseList,key = { it.id }){
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Column {
                            Text(
                                text = it.title,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = if(it.isExpense) "Debit Expense" else "Credit Income",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "${if (it.isExpense) "-" else "+"}$${it.amount}",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (it.isExpense) Color(0xFFE11D48) else Color(0xFF16A34A)
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            IconButton(
                                onClick = {
                                    scope.launch {
                                        database.expenseDao.deleteExpense(it)
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete",
                                    tint = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}