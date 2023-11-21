package com.example.listandhilt

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.twotone.ArrowDropDown
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.listandhilt.Data.RateViewModel
import com.example.listandhilt.Data.Types.BroadCast.HD
import com.example.listandhilt.Data.Types.BroadCast.Normal
import com.example.listandhilt.Data.Types.TypeEdit
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    View()
                }
            }
        }
    }
}

@Composable
fun View(vm: RateViewModel = viewModel()) {
    Column {
        Box{
            ReadDataFromDatabase(vm)
        }

        Button(
            onClick = {
                vm.add("New", Normal, false)
            },
        ) {
            Text(
                text = stringResource(R.string.add), fontSize = 18.sp, modifier = Modifier
                    .padding(10.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadDataFromDatabase(vm: RateViewModel = viewModel()) {
    val listState = rememberLazyListState(0, 0)

    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        LazyColumn(modifier = Modifier.fillMaxWidth(), state = listState) {
            var index = 0

            items(vm.rates.rates.value) {
                androidx.compose.material3.ListItem(
                    headlineText = { ViewItem(vm = vm, index = index) },
                    leadingContent = {
                        Icon(
                            imageVector = Icons.TwoTone.Favorite,
                            contentDescription = "",
                            tint = Color(0xFF6650a4)
                        )
                    }
                )
                //  ViewItem(vm = vm, index = index )
                index++
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ViewItem(vm:RateViewModel, index: Int ){
    Card(
        modifier = Modifier.padding(6.dp),
    ) {
        val isSavable = remember {
            mutableStateOf(false)
        }

        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            var expChoice by remember { mutableStateOf(false) }
            val expBroadCast = remember {
                mutableStateOf(false)
            }
            val expType = remember {
                mutableStateOf(false)
            }

            Box {
                IconButton(onClick = { expChoice = true }) {
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = stringResource(R.string.show_menu)
                    )
                }
                DropdownMenu(
                    expanded = expChoice,
                    onDismissRequest = { expChoice = false }
                ) {
                    Text(
                        stringResource(R.string.edit), fontSize = 18.sp, modifier = Modifier
                            .padding(10.dp)
                            .clickable(onClick = {
                                isSavable.value = true
                                expChoice = false
                            })
                    )
                    Text(
                        stringResource(R.string.delete),
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable(onClick = {
                                vm.remove(vm.rates.rates.value[index].name)
                                expChoice = false

                            })
                    )

                    Divider()
                    Text(
                        stringResource(R.string.settings),
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable(onClick = {})
                    )
                }
            }

            if (isSavable.value) {
                OutlinedTextField(
                    value = vm.rates.rates.value[index].name,
                    onValueChange = {
                        vm.rates.rates.value[index].name = it
                    },
                    modifier = Modifier.padding(4.dp),
                    label = {
                        Text(stringResource(R.string.name))
                    }
                )

                Spacer(modifier = Modifier.width(5.dp))

                Row {
                    Text(
                        text = stringResource(
                            R.string.broadcast_type,
                            vm.rates.rates.value[index].type
                        ),
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black, textAlign = TextAlign.Center
                    )

                    Box {
                        IconButton(
                            onClick = { expBroadCast.value = true },
                            enabled = isSavable.value
                        ) {
                            Icon(
                                Icons.TwoTone.ArrowDropDown,
                                contentDescription = stringResource(R.string.show_menu)
                            )
                        }
                    }
                    DropdownMenu(
                        expanded = expBroadCast.value,
                        onDismissRequest = {
                            expBroadCast.value = false
                        }
                    ) {
                        Text(
                            "$HD", fontSize = 18.sp,
                            modifier = Modifier
                                .padding(10.dp)
                                .clickable(onClick = {
                                    vm.edit(index, TypeEdit.BroadCast, newBroadCast = HD)
                                    expBroadCast.value = false
                                })
                        )
                        Text(
                            "$Normal", fontSize = 18.sp,
                            modifier = Modifier
                                .padding(10.dp)
                                .clickable(onClick = {
                                    vm.edit(
                                        index,
                                        TypeEdit.BroadCast,
                                        newBroadCast = Normal
                                    )
                                    expBroadCast.value = false

                                })
                        )
                    }
                }

                Spacer(modifier = Modifier.width(5.dp))

                Row {
                    Text(
                        text = stringResource(
                            R.string.universal_access,
                            vm.rates.rates.value[index].access
                        ),
                        modifier = Modifier.padding(4.dp),
                        textAlign = TextAlign.Center
                    )

                    Box {
                        IconButton(
                            onClick = { expType.value = true },
                        ) {
                            Icon(
                                Icons.TwoTone.ArrowDropDown,
                                contentDescription = stringResource(R.string.show_menu)
                            )
                        }
                    }
                    DropdownMenu(
                        expanded = expType.value,
                        onDismissRequest = {
                            expType.value = false
                        }
                    ) {
                        Text(
                            stringResource(R.string.no),
                            fontSize = 18.sp,
                            modifier = Modifier
                                .padding(10.dp)
                                .clickable(onClick = {
                                    vm.edit(index, TypeEdit.Access, newAccess = false)
                                    expType.value = false

                                }),
                        )
                        Text(
                            stringResource(R.string.yes), fontSize = 18.sp,
                            modifier = Modifier
                                .padding(10.dp)
                                .clickable(onClick = {
                                    vm.edit(index, TypeEdit.Access, newAccess = true)
                                    expType.value = false

                                }),
                        )
                    }
                }

                Spacer(modifier = Modifier.width(5.dp))
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Box(modifier = Modifier.padding(4.dp)) {
                        IconButton(onClick = { isSavable.value = false }) {
                            Icon(
                                Icons.Default.Done,
                                contentDescription = stringResource(R.string.show_menu)
                            )
                        }
                    }
                }

            } else {
                Text(
                    text = vm.rates.rates.value[index].name,
                    modifier = Modifier.padding(4.dp),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = stringResource(
                        R.string.broadcast_type,
                        vm.rates.rates.value[index].type
                    ),
                    modifier = Modifier.padding(4.dp),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = stringResource(
                        R.string.universal_access,
                        vm.rates.rates.value[index].access
                    ),
                    modifier = Modifier.padding(4.dp),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.width(5.dp))
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