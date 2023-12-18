package com.jocmp.basilreader.ui.articles

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jocmp.basil.Folder

@Composable
fun FolderRow(
    folder: Folder,
    onFeedSelect: (feedID: String) -> Unit
) {
    Box(modifier = Modifier.padding(horizontal = 8.dp)) {
        Text(folder.title, fontWeight = FontWeight.Bold)
    }
    folder.feeds.forEach { feed ->
        Row {
            FeedRow(feed = feed, onSelect = onFeedSelect)
        }
    }
}
