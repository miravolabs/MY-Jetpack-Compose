package agecalabir.android.myjetpackcompose.todoapp.ui

import agecalabir.android.myjetpackcompose.todoapp.data.TaskItem
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ToDoItem(
    item : TaskItem,
    onCheckChange: () -> Unit,
    onEditCheck: () -> Unit,
    onDeleteCheck: () -> Unit
){
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (item.isDone) Color(0xFFF0F0F0) else Color.White
        ),
        border = BorderStroke(1.dp,Color(0xFFE0E0E0))
    ){
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            IconButton(
                onClick = onCheckChange
            ) {
                Icon(
                    imageVector = if (item.isDone) Icons.Filled.CheckCircle else Icons.Outlined.Circle,
                    contentDescription = null,
                    tint = if (item.isDone) Color(0xFF4CAF50) else Color.Gray
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = item.taskName,
                fontSize = 16.sp,
                textDecoration = if (item.isDone) TextDecoration.LineThrough else TextDecoration.None,
                color = if (item.isDone) Color.Gray else Color.Black,
                modifier = Modifier.weight(1f)
            )
            IconButton(
                onEditCheck
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    tint = Color.DarkGray
                )
            }
            IconButton(
                onDeleteCheck
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = Color.Red
                )
            }
        }
    }
}