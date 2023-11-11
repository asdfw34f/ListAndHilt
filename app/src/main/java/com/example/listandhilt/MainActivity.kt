package com.example.listandhilt

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.listandhilt.Data.RateViewModel
import com.example.listandhilt.ui.theme.ListAndHiltTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ListAndHiltTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    View()
                }
            }
        }
    }
}

@Composable
fun View(viewModel: RateViewModel = viewModel()) {

    Column {
        readDataFromDatabase(viewModel)
        Row {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "edit")
            }
        }

    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun readDataFromDatabase(vm: RateViewModel = viewModel()) {
    // on below line we are creating and initializing our array list


    // on below line we are creating a lazy column for displaying a list view.
    LazyColumn {
        // on below line we are setting data for each item of our listview.
        itemsIndexed(vm.rates.value.rates) { index, item ->
            // on below line we are creating a card for our list view item.
            Card(
                // on below line we are adding padding from our all sides.
                modifier = Modifier.padding(8.dp),
                // on below line we are adding elevation for the card.
            ) {
                // on below line we are creating a row for our list view item.
                Column(
                    // for our row we are adding modifier to set padding from all sides.
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    var expanded by remember { mutableStateOf(false) }

                    Box {
                        IconButton(onClick = { expanded = true }) {
                            Icon(Icons.Default.MoreVert, contentDescription = "Показать меню")
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            Text("Изменить", fontSize=18.sp, modifier = Modifier
                                .padding(10.dp)
                                .clickable(onClick = {}))
                            Text("Удалить", fontSize=18.sp, modifier = Modifier
                                .padding(10.dp)
                                .clickable(onClick = {}))
                            Divider()
                            Text("Настройки", fontSize=18.sp, modifier = Modifier
                                .padding(10.dp)
                                .clickable(onClick = {}))
                        }
                    }
                    // on the below line we are creating a text.
                    Text(
                        // inside the text on below line we are
                        // setting text as the language name
                        // from our model class.
                        text = vm.rates.value.rates[index].name,

                        // on below line we are adding padding
                        // for our text from all sides.
                        modifier = Modifier.padding(4.dp),

                        // on below line we are adding color for our text
                        color = Color.Black, textAlign = TextAlign.Center
                    )
                    // on below line inside row we are adding spacer
                    Spacer(modifier = Modifier.width(5.dp))

                    // on the below line we are creating a text.
                    Text(
                        // inside the text on below line we are
                        // setting text as the language name
                        // from our model class.
                        text = "BroadCast type : " + vm.rates.value.rates[index].type.toString(),

                        // on below line we are adding padding
                        // for our text from all sides.
                        modifier = Modifier.padding(4.dp),

                        // on below line we are adding color for our text
                        color = Color.Black, textAlign = TextAlign.Center
                    )
                    // on below line inside row we are adding spacer
                    Spacer(modifier = Modifier.width(5.dp))

                    // on the below line we are creating a text.
                    Text(
                        // inside the text on below line we are
                        // setting text as the language name
                        // from our model class.
                        text = "Universal access : " + vm.rates.value.rates[index].access,

                        // on below line we are adding padding
                        // for our text from all sides.
                        modifier = Modifier.padding(4.dp),

                        // on below line we are adding color for our text
                        color = Color.Black, textAlign = TextAlign.Center
                    )
                    // on below line inside row we are adding spacer
                    Spacer(modifier = Modifier.width(5.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ViewPreview() {
    ListAndHiltTheme {
        View()
    }
}