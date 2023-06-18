package com.example.testtaskweelorum.ui

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testtaskweelorum.R
import com.example.testtaskweelorum.model.WorkoutData
import com.example.testtaskweelorum.viewmodel.MainViewModel

@Composable
fun WorkoutDetailsScreen() {
    val mainViewModel = viewModel<MainViewModel>()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White,
            )

    ) {
        Column {
            DayAndExerciseSection()
            WorkoutInfoSection()
            WorkoutDescriptionSection()
            WorkoutDetailsItem(
                mainViewModel.exerciseItem.collectAsState().value,
                expand = {
                    mainViewModel.expandTraining(it)
                },
                addSet = {
                    mainViewModel.addSet(it)
                },
                finishSet = { workoutId, setId ->
                    mainViewModel.finishSet(workoutId, setId)
                }
            )
        }
    }
    StartTrainingButton()
}

@Composable
fun DayAndExerciseSection() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Column {
            Text(
                text = stringResource(R.string.day_number).uppercase(),
                fontSize = 12.sp,
                style = MaterialTheme.typography.titleSmall,
                color = colorResource(R.color.new_red)
            )
            Text(
                text = stringResource(R.string.exercise),
                color = colorResource(R.color.new_black),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}

@Composable
fun WorkoutInfoSection() {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        Text(
            text = stringResource(R.string.exercise_number),
            fontSize = 12.sp,
            style = MaterialTheme.typography.titleLarge,
            color = colorResource(R.color.main_gray),
            modifier = Modifier
                .padding(end = 4.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.ellipse_icon),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 4.dp)
        )
        Text(
            text = stringResource(R.string.set_number),
            fontSize = 12.sp,
            style = MaterialTheme.typography.titleLarge,
            color = colorResource(R.color.main_gray),
            modifier = Modifier
                .padding(end = 4.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.ellipse_icon),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 4.dp)
        )
        Text(
            text = stringResource(R.string.training_duration),
            fontSize = 12.sp,
            style = MaterialTheme.typography.titleLarge,
            color = colorResource(R.color.main_gray)
        )
    }
}

@Composable
fun WorkoutDescriptionSection() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 20.dp, start = 12.dp, end = 12.dp)
    ) {
        Text(
            text = stringResource(R.string.training_description),
            fontSize = 12.sp,
            lineHeight = 18.sp,
            style = MaterialTheme.typography.titleLarge,
            color = colorResource(R.color.new_black)
        )
    }
}

@Composable
fun WorkoutDetailsItem(exerciseItem: List<WorkoutData>, expand: (String) -> Unit, addSet: (String) -> Unit, finishSet: (String, String) -> Unit) {

    LazyColumn() {
        items(exerciseItem) { exerciseItem ->
            ExpandableTrainingItem(exerciseItem, expand, addSet, finishSet)
        }
    }
}

@Composable
fun ExpandableTrainingItem(exerciseItem: WorkoutData, expand: (String) -> Unit, addSet: (String) -> Unit, finishSet: (String, String) -> Unit) {
    Column {
        TrainingItem(exerciseItem, expand)
        ExpandedTrainingView(exerciseItem.sets, isExpanded = exerciseItem.isExpanded, addSet = {
            addSet(exerciseItem.id)
        }, finishSet = {
            finishSet(exerciseItem.id, it)
        })
    }
}

@Composable
fun TrainingItem(exerciseItem: WorkoutData, expand: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(12.dp)
            .clickable {
                expand(exerciseItem.id)
            }
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.training_photo),
            contentDescription = "Training photo",
            modifier = Modifier
                .padding(end = 16.dp)
                .size(60.dp)
        )
        Column(
            modifier =
            Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
                .fillMaxHeight()
        ) {
            Text(
                text = exerciseItem.name,
                fontSize = 16.sp,
                style = MaterialTheme.typography.headlineSmall,
                color = colorResource(R.color.new_black),
                modifier =
                Modifier.align(Alignment.Start)
            )
            Row {
                Text(
                    text = exerciseItem.reps,
                    fontSize = 12.sp,
                    color = colorResource(R.color.new_black),
                    style = MaterialTheme.typography.titleMedium,
                )
                Icon(
                    painter = painterResource(id = R.drawable.red_cross_icon),
                    tint = colorResource(R.color.new_red),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(4.dp)
                )
                Text(
                    text = "${exerciseItem.sets.size} підходи",
                    fontSize = 12.sp,
                    color = colorResource(R.color.new_black),
                    style = MaterialTheme.typography.titleMedium
                )
                Icon(
                    painter = painterResource(id = R.drawable.red_cross_icon),
                    contentDescription = "",
                    tint = colorResource(R.color.new_red),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(4.dp)
                )
                Text(
                    text = stringResource(R.string.kilos),
                    fontSize = 12.sp,
                    color = colorResource(R.color.new_black),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
        IconButton(
            onClick = {
                expand(exerciseItem.id)
            }
        )
        {
            Icon(
                painter = painterResource(id = R.drawable.dots_menu_icon),
                contentDescription = "Dots menu",
                modifier = Modifier
                    .padding(start = 12.dp, end = 12.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun ExpandedTrainingView(sets: List<WorkoutData.WorkoutSet>, isExpanded: Boolean, addSet: () -> Unit, finishSet: (String) -> Unit) {
    val expandTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(300)
        ) + fadeIn(
            animationSpec = tween(300)
        )
    }

    val collapseTransition = remember {
        shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(300)
        ) + fadeOut(
            animationSpec = tween(300)
        )
    }

    AnimatedVisibility(
        visible = isExpanded,
        enter = expandTransition,
        exit = collapseTransition
    ) {
        Column() {
            ExpandedTrainingHeader()
            sets.forEachIndexed { index, item ->
                ExpandedTrainingDetails(item, index, finishSet)
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(
                    onClick = { addSet() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    modifier = Modifier
                        .width(202.dp)
                        .height(40.dp)
                ) {
                    Text(
                        text = stringResource(R.string.set_add),
                        fontSize = 12.sp,
                        color = colorResource(R.color.new_red),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}

@Composable
fun ExpandedTrainingDetails(workoutSet: WorkoutData.WorkoutSet, index: Int, finishSet: (String) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = if (workoutSet.isDone) R.color.new_green_10 else android.R.color.transparent))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = index.toString(),
            fontSize = 12.sp,
            color = colorResource(R.color.new_black),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = if (workoutSet.isDone) "—" else workoutSet.previous,
            fontSize = 12.sp,
            color = colorResource(R.color.new_black),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = workoutSet.weight.toString(),
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            color = colorResource(id = if (!workoutSet.isDone) R.color.main_gray else R.color.new_black),
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(colorResource(id = if (!workoutSet.isDone) R.color.new_gray else android.R.color.transparent))
                .size(48.dp, 28.dp)
                .wrapContentHeight()
                .weight(0.5f)
        )
        Text(
            text = workoutSet.reps.toString(),
            fontSize = 12.sp,
            color = colorResource(R.color.new_black),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(colorResource(id = if (!workoutSet.isDone) R.color.new_gray else android.R.color.transparent))
                .size(48.dp, 28.dp)
                .wrapContentHeight()
                .weight(0.5f)
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(colorResource(id = if (workoutSet.isDone) R.color.new_green else R.color.new_gray))
                .weight(0.5f)
                .height(28.dp)
                .clickable {
                    finishSet(workoutSet.id)
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.check_mark),
                contentDescription = "Check icon",
                colorFilter = if (workoutSet.isDone) ColorFilter.tint(Color.White) else null
            )
        }
    }
}

@Composable
fun ExpandedTrainingHeader() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = stringResource(R.string.set),
            fontSize = 12.sp,
            color = colorResource(R.color.main_gray),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f),
        )
        Text(
            text = stringResource(R.string.previous),
            fontSize = 12.sp,
            color = colorResource(R.color.main_gray),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f),
        )
        Text(
            text = stringResource(R.string.weight),
            fontSize = 12.sp,
            color = colorResource(R.color.main_gray),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(0.5f),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(R.string.rep_short),
            fontSize = 12.sp,
            color = colorResource(R.color.main_gray),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(0.5f),
            textAlign = TextAlign.Center
        )
        Image(
            painter = painterResource(id = R.drawable.check_mark),
            contentDescription = "Check icon",
            modifier = Modifier
                .weight(0.5f)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun StartTrainingButton() {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 12.dp, end = 12.dp, bottom = 42.dp)
    ) {
        val context = LocalContext.current
        TextButton(
            onClick = {
                Toast.makeText(context, "Починаємо тренування", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(
                    color = colorResource(R.color.new_red),
                )
        ) {
            Text(
                text = stringResource(R.string.start_training),
                fontSize = 16.sp,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
@Preview
fun WorkoutDetailsScreenPreview() {
    WorkoutDetailsScreen()
}

/*

@Preview(showBackground = false)
@Composable
fun ExpandableViewPreview() {
    ExpandedTrainingView(listOf(WorkoutData.WorkoutSet("1", "222", 10, 10, true)), true, {

    }, {})
}

 */


