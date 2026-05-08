package com.jlls.soundbrain.ui.screens.inicio

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.jlls.soundbrain.domain.model.Song
import com.jlls.soundbrain.ui.theme.Background

/**
 * Inicio (Home/Library) screen showing song library.
 * Redesigned with better visual hierarchy, shadows, and animations.
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun InicioScreen(
    viewModel: InicioViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val sheetState = rememberModalBottomSheetState()

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Background),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(bottom = 100.dp)
        ) {
            // Header
            item {
                Text(
                    text = "Library",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
                )
            }

            // Horizontal Album Covers Carousel - Larger with titles
            if (uiState.recentSongs.isNotEmpty()) {
                item {
                    Text(
                        text = "Recent",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
                    )

                    LazyRow(
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(uiState.recentSongs) { song ->
                            AlbumCoverCard(
                                song = song,
                                onClick = { viewModel.selectSong(song) }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }
            }

            // All Songs Section
            item {
                Text(
                    text = "All Songs",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
                )
            }

            items(
                items = uiState.songs,
                key = { it.id }
            ) { song ->
                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn(animationSpec = tween(300)) + slideInVertically(
                        initialOffsetY = { it / 2 },
                        animationSpec = tween(300)
                    ),
                    exit = fadeOut()
                ) {
                    SongCard(
                        song = song,
                        onLongClick = { viewModel.selectSong(song) },
                        onPlayClick = { viewModel.playSong(song) },
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)
                    )
                }
            }
        }

        // Bottom Sheet for song options
        if (uiState.selectedSong != null) {
            ModalBottomSheet(
                onDismissRequest = { viewModel.selectSong(uiState.selectedSong!!) },
                sheetState = sheetState
            ) {
                SongOptionsSheet(
                    song = uiState.selectedSong!!,
                    onPlay = {
                        viewModel.playSong(uiState.selectedSong!!)
                        viewModel.selectSong(uiState.selectedSong!!)
                    },
                    onShare = {
                        viewModel.shareSong(uiState.selectedSong!!)
                        viewModel.selectSong(uiState.selectedSong!!)
                    },
                    onDelete = {
                        viewModel.deleteSong(uiState.selectedSong!!)
                        viewModel.selectSong(uiState.selectedSong!!)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun AlbumCoverCard(
    song: Song,
    onClick: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = tween(150),
        label = "scale"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .size(140.dp)
                .scale(scale)
                .shadow(8.dp, RoundedCornerShape(24.dp))
                .clip(RoundedCornerShape(24.dp))
                .combinedClickable(
                    onClick = onClick,
                    onLongClick = {}
                ),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            AsyncImage(
                model = song.coverUrl,
                contentDescription = song.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = song.title,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(140.dp)
        )

        Text(
            text = song.promptStyle.displayName,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SongCard(
    song: Song,
    onLongClick: () -> Unit,
    onPlayClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .combinedClickable(
                onClick = onPlayClick,
                onLongClick = onLongClick
            ),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Album art
            AsyncImage(
                model = song.coverUrl,
                contentDescription = song.title,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(14.dp))

            // Song info
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = song.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "${song.artistName} • ${song.promptStyle.displayName}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = formatDate(song.createdAt),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                )
            }

            // Play indicator
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .shadow(2.dp, RoundedCornerShape(12.dp))
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = "Play",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
private fun SongOptionsSheet(
    song: Song,
    onPlay: () -> Unit,
    onShare: () -> Unit,
    onDelete: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp)
    ) {
        Text(
            text = song.title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .combinedClickable(onClick = onPlay)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Outlined.PlayArrow, contentDescription = null)
            Spacer(modifier = Modifier.width(12.dp))
            Text("Play")
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .combinedClickable(onClick = onShare)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Outlined.Share, contentDescription = null)
            Spacer(modifier = Modifier.width(12.dp))
            Text("Share")
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.errorContainer)
                .combinedClickable(onClick = onDelete)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Outlined.Delete,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text("Delete", color = MaterialTheme.colorScheme.error)
        }
    }
}

private fun formatDate(timestamp: Long): String {
    val sdf = java.text.SimpleDateFormat("MMM d, yyyy", java.util.Locale.getDefault())
    return sdf.format(java.util.Date(timestamp))
}
