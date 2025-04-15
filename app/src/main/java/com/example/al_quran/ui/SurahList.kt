package com.example.al_quran.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.al_quran.ViewModel.QuranViewModel
import com.example.al_quran.data.Surah
import com.example.al_quran.R

@Composable
fun SurahList(viewModel: QuranViewModel, onSurahClick: (Int) -> Unit) {
    val surahs = viewModel.surahs.value
    val lastRead = viewModel.lastReadSurah.value

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.ebg_islami),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 5.15f
        )

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)) {

            if (lastRead != null) {
                val surah = surahs.find { it.number == lastRead }
                if (surah != null) {
                    LastReadCard(surah = surah) {
                        onSurahClick(surah.number)
                    }
                }
            }

            LazyColumn {
                items(surahs) { surah ->
                    SurahItem(
                        surah = surah,
                        onClick = {
                            viewModel.setLastReadSurah(surah.number)
                            onSurahClick(surah.number)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun LastReadCard(surah: Surah, onClick: () -> Unit) {
    Card(
        backgroundColor = Color(0xFFFFC107),
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_bismillah),
                contentDescription = "Terakhir dibaca",
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text("Terakhir Dibaca", color = Color.Black, fontSize = 14.sp)
                Text("${surah.number}. ${surah.englishName}", color = Color.Black, fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun SurahItem(surah: Surah, onClick: () -> Unit) {
    Card(
        backgroundColor = Color(0xFF1A1A1A),
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_masjid),
                contentDescription = "Icon Surah",
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "${surah.number}. ${surah.englishName} (${surah.name})",
                    color = Color.White,
                    fontSize = 18.sp
                )
                Text(
                    text = "Jumlah Ayat: ${surah.numberOfAyahs}",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }
    }
}