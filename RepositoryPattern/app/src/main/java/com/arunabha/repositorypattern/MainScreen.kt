package com.arunabha.repositorypattern

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arunabha.repositorypattern.database.TaskDatabase
import com.arunabha.repositorypattern.repository.TaskRepository
import com.arunabha.repositorypattern.viewmodel.TaskViewModel
import com.arunabha.repositorypattern.viewmodel.TaskViewModelFactory
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {


    val context = LocalContext.current
    val dao = TaskDatabase.getDatabase(context)?.taskDao()
    val repository = dao?.let { TaskRepository(context, it) }
    val viewModel: TaskViewModel = viewModel(factory = repository?.let { TaskViewModelFactory(it) })
    val coroutineScope = rememberCoroutineScope()

    val allTasks = viewModel.allTasksFlow.collectAsState(initial = emptyList())
//    val isLoading = viewModel.isLoading.collectAsState()


    Scaffold(
        // Top Bar
        topBar = {
            CenterAlignedTopAppBar(
                // Title section which contains logo, text, search icon
                title = { Text(text = "Tasks") },
                modifier = Modifier.shadow(5.dp)
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // Go to add thread page
//                navController.navigate("create/null")
            }) {
                Icon(Icons.Default.Create, "Create Thread")
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            var list = remember {
                // cause the recomposition
                mutableStateListOf<String>().apply {
                    addAll(List(50) { "Item $it" })
                }
            }

            val myText =
                "In the first place we have granted to God, and by this our present charter confirmed for us and our heirs forever that the English Church shall be free, and shall have her rights entire."

//            when (isLoading.value) {
//                true -> {
//                    Box(
//                        contentAlignment = Alignment.Center,
//                        modifier = Modifier.fillMaxSize()
//                    ) {
//                        CircularProgressIndicator()
//                    }
//                }
//
//                false -> {
//
//                }
//            }

            LazyColumn(
                modifier = Modifier
                    .padding(3.dp)
                    .fillMaxSize()
            ) {
                items(allTasks.value, key = { it.id ?: 0 }) { item ->
                    ListItem(
                        headlineContent = { Text(item.title ?: "Task") },
                        supportingContent = { Text(item.desc ?: "Task Description") },
                        trailingContent = {
                            IconButton(onClick = {
                                coroutineScope.launch {
                                    viewModel.deleteTask(item)
                                }
                            }) {
                                Icon(Icons.Default.MoreVert, "More")
                            }
                        }
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}

