package agecalabir.android.myjetpackcompose.data_store

import agecalabir.android.myjetpackcompose.animation_learn.ElasticToggleSwitch
import agecalabir.android.myjetpackcompose.jetpackcompose.ListItemCard
import agecalabir.android.myjetpackcompose.jetpackcompose.wineash
import agecalabir.android.myjetpackcompose.ui.theme.Poppins
import android.content.Context
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

// Data store preferences logic
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
}