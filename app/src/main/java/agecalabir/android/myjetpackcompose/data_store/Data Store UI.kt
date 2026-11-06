package agecalabir.android.myjetpackcompose.data_store

// Data store preferences logic
/*
val Context.dataStore by preferencesDataStore(name = "is_dark_enable_storage")

// 2. key
val IS_DARK_KEY = booleanPreferencesKey("is_dark")


@Composable
fun DataStorePractice(
    isDark : Boolean,
    onToggle: (Boolean) -> Unit
){
    val names = listOf("Abir","Nihal","Shaki","Jisan","Thuhin")
    val options = listOf("System","Dark","Light")
    var selectedOption by remember { mutableIntStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainer
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ){
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = if (isDark) {"Enable Light"} else {"Enable Dark"},
                    fontSize = 20.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                ElasticToggleSwitch(
                    checked = isDark,
                    onCheckedChange = {onToggle(it)},
                    height = 30.dp
                )
            }
        }
        Spacer(modifier = Modifier.height(22.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(names) { names ->
                ListItemCard(name = names)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
        ){
            Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Text(
                    text = "Select Theme",
                    fontFamily = Poppins,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(10.dp))
                SingleChoiceSegmentedButtonRow(
                    modifier = Modifier.fillMaxWidth()
                ){
                    options.forEachIndexed { index, label ->
                        SegmentedButton(
                            shape = SegmentedButtonDefaults.itemShape(
                                index = index,
                                count = options.size,
                                baseShape = RoundedCornerShape(4.dp)
                            ),
                            onClick = {selectedOption = index},
                            selected = (index == selectedOption),
                            icon = {
                                if(index == selectedOption){
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        modifier = Modifier.size(SegmentedButtonDefaults.IconSize)
                                    )
                                }
                            },
                            colors = SegmentedButtonDefaults.colors(
                                activeContainerColor = wineash,
                                activeContentColor = Color.White,
                                inactiveContainerColor = Color(0xFFF5F5F5),
                                inactiveContentColor = Color.Gray,
                                activeBorderColor = wineash,
                                inactiveBorderColor = Color.LightGray
                            )
                        ) {
                            Text(
                                text = label,
                                fontFamily = Poppins,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

// Practice lazy row

@Composable
fun ListItemCard(name : String){
    OutlinedCard(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.outlinedCardColors(
           containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        border = BorderStroke(1.dp, wineash)
    ){
       Text(
           text = name,
           fontSize = 16.sp,
           fontFamily = Poppins,
           modifier = Modifier.padding(16.dp),
           color = MaterialTheme.colorScheme.onBackground
       )
    }

}*/