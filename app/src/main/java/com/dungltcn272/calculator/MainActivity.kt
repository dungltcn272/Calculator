package com.dungltcn272.calculator

import com.dungltcn272.calculator.ui.theme.Background
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.dungltcn272.calculator.caculator.components.InputButton
import com.dungltcn272.calculator.caculator.viewmodel.MainViewModel
import com.dungltcn272.calculator.ui.theme.CalculatorTheme
import com.dungltcn272.calculator.ui.theme.EqualButton
import com.dungltcn272.calculator.ui.theme.Faint
import com.dungltcn272.calculator.ui.theme.FunctionButton
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                CalculatorScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding()
                        .background(Background)
                )
            }
        }
    }
}

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier, viewModel: MainViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = viewModel.chainOfCalculations) {
        viewModel.saveChain()
        viewModel.calculate()
    }

    val fontSizeTop =
        animateFloatAsState(if (viewModel.equalState) 24f else 40f, label = "fontSizeTop")
    val fontSizeBottom =
        animateFloatAsState(if (viewModel.equalState) 40f else 24f, label = "fontSizeBottom")

    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (grid, result) = createRefs()

        Box(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(grid) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                contentPadding = PaddingValues(10.dp),
                modifier = Modifier.wrapContentSize()
            ) {
                items(20) {
                    when (it) {
                        0 -> InputButton(
                            text = "C",
                            onClick = { viewModel.clear() },
                            color = FunctionButton
                        )

                        1 -> InputButton(
                            text = "%",
                            onClick = { viewModel.addCharacter('%') },
                            color = FunctionButton
                        )

                        2 -> InputButton(
                            icon = R.drawable.ic_backspace,
                            onClick = { viewModel.delete() },
                            color = FunctionButton
                        )

                        3 -> InputButton(
                            text = "/",
                            onClick = { viewModel.addCharacter('/') },
                            color = FunctionButton
                        )

                        4 -> InputButton(text = "7", onClick = { viewModel.addCharacter('7') })
                        5 -> InputButton(text = "8", onClick = { viewModel.addCharacter('8') })
                        6 -> InputButton(text = "9", onClick = { viewModel.addCharacter('9') })
                        7 -> InputButton(
                            icon = R.drawable.ic_multi,
                            onClick = { viewModel.addCharacter('x') },
                            color = FunctionButton
                        )

                        8 -> InputButton(text = "4", onClick = { viewModel.addCharacter('4') })
                        9 -> InputButton(text = "5", onClick = { viewModel.addCharacter('5') })
                        10 -> InputButton(text = "6", onClick = { viewModel.addCharacter('6') })
                        11 -> InputButton(
                            icon = R.drawable.ic_sub,
                            onClick = { viewModel.addCharacter('-') },
                            color = FunctionButton
                        )

                        12 -> InputButton(text = "1", onClick = { viewModel.addCharacter('1') })
                        13 -> InputButton(text = "2", onClick = { viewModel.addCharacter('2') })
                        14 -> InputButton(text = "3", onClick = { viewModel.addCharacter('3') })
                        15 -> InputButton(
                            icon = R.drawable.ic_add,
                            onClick = { viewModel.addCharacter('+') },
                            color = FunctionButton
                        )

                        16 -> InputButton(text = "00", onClick = { viewModel.addCharacter('@') })
                        17 -> InputButton(text = "0", onClick = { viewModel.addCharacter('0') })
                        18 -> InputButton(text = ".", onClick = { viewModel.addCharacter('.') })
                        19 -> InputButton(
                            text = "=",
                            onClick = { viewModel.equalClick() },
                            color = EqualButton
                        )
                    }
                }
            }
        }


        Column(modifier = Modifier
            .constrainAs(result) {
                end.linkTo(grid.end)
                bottom.linkTo(grid.top)
            }
            .padding(horizontal = 20.dp, vertical = 15.dp), horizontalAlignment = Alignment.End) {
            Text(
                text = viewModel.chainOfCalculations,
                fontSize = fontSizeTop.value.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = if (!viewModel.equalState) Color.Black else Faint
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = viewModel.result,
                fontSize = fontSizeBottom.value.sp,
                color = if (viewModel.equalState) Color.Black else Faint
            )
        }
    }
}

