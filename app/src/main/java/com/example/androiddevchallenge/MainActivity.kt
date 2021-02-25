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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            Puppy("Bella","Beagle", 7),
            Puppy("Duke","Staffordshire Bull Terrier", 3),
            Puppy("Snowy","German Shepherd", 12),
            Puppy("Triggs","Staffordshire Bull Terrier", 15),
            Puppy("Ariel","Belgian Shepherd", 12),
            Puppy("Max","Yorkshire Terrier", 10),
            Puppy("Eve","Saluki", 24),
            Puppy("Pumpkin","Lurcher", 12),
            Puppy("Milo","Bull Mastiff", 3)
        )
    }
}

// Start building your app here!
@Composable
fun MyApp(puppies: List<Puppy>) {
    Surface(color = MaterialTheme.colors.background) {
        LazyColumn {
            items(puppies) { puppy ->
                PuppyCard(puppy) {
                    // on click
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
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
//        Image(imageVector = , contentDescription = )
            Column {
                Text(
                    text = puppy.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text("Breed: ${puppy.breed}")
                Text("Age: ${puppy.ageInMonths} months")
            }
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
