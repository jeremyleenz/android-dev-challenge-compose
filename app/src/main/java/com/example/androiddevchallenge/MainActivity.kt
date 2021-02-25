/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp(puppies = PUPPIES_LIST)
            }
        }
    }

    companion object {
        private val PUPPIES_LIST = listOf(
            Puppy("Bella", "Beagle", 7, "Brown/White", R.drawable.beagle),
            Puppy("Duke", "Labrador", 3, "Golden", R.drawable.lab),
            Puppy("Snowy", "German Shepherd", 12, "Black/Brown", R.drawable.german_shepherd),
            Puppy("Triggs", "Dalmatian", 15, "Black/White", R.drawable.dalmation),
            Puppy("Ariel", "Belgian Shepherd", 12, "Brown", R.drawable.belgian_shepherd),
            Puppy("Max", "Yorkshire Terrier", 10, "Brown/Black", R.drawable.yorkshire_terrier),
            Puppy("Eve", "Corgi", 24, "Light Brown", R.drawable.corgi),
            Puppy("Pumpkin", "Lurcher", 12, "Light Brown", R.drawable.lurcher),
            Puppy("Milo", "Bull Mastiff", 3, "Brown", R.drawable.bullmastiff)
        )
    }
}

// Start building your app here!
@Composable
fun MyApp(puppies: List<Puppy>) {
    val navController = rememberNavController()

    Surface(color = MaterialTheme.colors.background) {
        NavHost(navController = navController, startDestination = "puppylist") {
            composable("puppylist") {
                LazyColumn {
                    items(puppies) { puppy ->
                        PuppyCard(puppy) {
                            navController.navigate("puppydetail/${puppy.id}")
                        }
                    }
                }
            }
            composable("puppydetail/{id}") { backStackEntry ->
                val puppy = puppies.find { it.id == backStackEntry.arguments?.getString("id") }
                if (puppy != null) {
                    PuppyDetail(puppy)
                }
            }
        }
    }
}

@Composable
fun PuppyCard(puppy: Puppy, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painterResource(puppy.photo),
                contentDescription = "Image of a ${puppy.breed}",
                modifier = Modifier
                    .size(50.dp),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )
            Spacer(Modifier.size(16.dp))
            Column {
                Text(
                    text = puppy.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(puppy.breed)
            }
        }
    }
}

@Composable
fun PuppyDetail(puppy: Puppy) {
    Column {
        Image(
            painterResource(puppy.photo),
            contentDescription = "Image of a ${puppy.breed}",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = puppy.name,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Text(stringResource(R.string.breed_label, puppy.breed), fontSize = 20.sp)
            Text(stringResource(R.string.age_label, puppy.ageInMonths), fontSize = 20.sp)
            Text(stringResource(R.string.color_label, puppy.color), fontSize = 20.sp)
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp(emptyList())
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp(emptyList())
    }
}
