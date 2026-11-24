package agecalabir.android.myjetpackcompose.todoapp.ui

/*
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

 */