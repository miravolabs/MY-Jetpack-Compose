package agecalabir.android.myjetpackcompose.animation_learn

import agecalabir.android.myjetpackcompose.jetpackcompose.wineash
import agecalabir.android.myjetpackcompose.ui.theme.Poppins
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

@Serializable
sealed interface BottomNavRoutes{
    @Serializable
    data object Home : BottomNavRoutes
    @Serializable
    data object Search : BottomNavRoutes
    @Serializable
    data object Inbox : BottomNavRoutes
    @Serializable
    data object Profile : BottomNavRoutes
}
data class BottomNavItem(
    val title: String,
    val icon : ImageVector,
    val route : BottomNavRoutes
)
// Animation Helper Function
private fun getTabIndex(destination: androidx.navigation.NavDestination?): Int {
    return when {
        destination?.hasRoute<BottomNavRoutes.Home>() == true -> 0
        destination?.hasRoute<BottomNavRoutes.Search>() == true -> 1
        destination?.hasRoute<BottomNavRoutes.Inbox>() == true -> 2
        destination?.hasRoute<BottomNavRoutes.Profile>() == true -> 3
        else -> 0
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimationAppScreen(){
    val navController = rememberNavController()

    val navItems = listOf(
        BottomNavItem("Home", Icons.Default.Home, BottomNavRoutes.Home),
        BottomNavItem("Search",Icons.Default.Search, BottomNavRoutes.Search),
        BottomNavItem("Inbox",Icons.Default.Inbox, BottomNavRoutes.Inbox),
        BottomNavItem("Profile",Icons.Default.Person, BottomNavRoutes.Profile)
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                  Text(
                      text = "Android",
                      fontSize = 24.sp,
                      fontFamily = Poppins,
                      fontWeight = FontWeight.SemiBold
                  )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val selectedIndex = getTabIndex(currentDestination)

            SlidingNavigationBar(
                navItems = navItems,
                selectedIndex = selectedIndex,
                onItemClick = { item ->
                    navController.navigate(item.route) {
                        popUpTo<BottomNavRoutes.Home> {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavRoutes.Home,
            // modifier = Modifier.padding(innerPadding)
            modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),

            // --- Material 3 Smart Directional Shared-Axis Animation ---
            enterTransition = {
                val initialIndex = getTabIndex(initialState.destination)
                val targetIndex = getTabIndex(targetState.destination)
                val direction = if (targetIndex > initialIndex) {
                    AnimatedContentTransitionScope.SlideDirection.Left
                } else {
                    AnimatedContentTransitionScope.SlideDirection.Right
                }

                slideIntoContainer(
                    towards = direction,
                    animationSpec = tween(durationMillis = 350, easing = FastOutSlowInEasing),
                    initialOffset = { it / 5 }
                ) + fadeIn(
                    animationSpec = tween(durationMillis = 350, easing = LinearOutSlowInEasing)
                )
            },
            exitTransition = {
                val initialIndex = getTabIndex(initialState.destination)
                val targetIndex = getTabIndex(targetState.destination)
                val direction = if (targetIndex > initialIndex) {
                    AnimatedContentTransitionScope.SlideDirection.Left
                } else {
                    AnimatedContentTransitionScope.SlideDirection.Right
                }

                slideOutOfContainer(
                    towards = direction,
                    animationSpec = tween(durationMillis = 350, easing = FastOutSlowInEasing),
                    targetOffset = { it / 5 }
                ) + fadeOut(
                    animationSpec = tween(durationMillis = 150, easing = FastOutLinearInEasing)
                )
            }
        ) {
            composable<BottomNavRoutes.Home> {
                LearningAnimationsScreen()
            }
            composable<BottomNavRoutes.Search> {
                SearchScreen()
            }
            composable<BottomNavRoutes.Inbox> {
                InboxScreen()
            }
            composable<BottomNavRoutes.Profile> {
                ProfileScreen()
            }
        }
    }
}

// Home Screen / 1st screen
@Composable
fun LearningAnimationsScreen() {
    var inputText by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    var errorTrigger by remember { mutableIntStateOf(0) }
    var textToAnimate by remember { mutableStateOf("") }
    var isSwitchOn by remember { mutableStateOf(false) }
    var isLiked by remember { mutableStateOf(false) }

    var animationTrigger by remember { mutableIntStateOf(0) }
    val screenVerticalScroll = rememberScrollState()

    LaunchedEffect(screenVerticalScroll.maxValue) {
        if (screenVerticalScroll.maxValue > 0) {
            screenVerticalScroll.animateScrollTo(
                value = screenVerticalScroll.maxValue,
                animationSpec = tween(durationMillis = 150, easing = FastOutSlowInEasing)
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
            .verticalScroll(screenVerticalScroll),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Typing Animation Screen",
            fontSize = 24.sp,
            fontFamily = Poppins,
            color = wineash
        )
        Spacer(modifier = Modifier.height(30.dp))

        ShakeOutlinedTextField(
            value = inputText,
            onValueChange = { newText ->
                if (newText.length <= 100) {
                    inputText = newText
                    if (newText.length == 100 && !isError) {
                        isError = true
                        errorTrigger++
                    } else if (newText.length < 100) {
                        isError = false
                    }
                } else {
                    isError = true
                    errorTrigger++
                }
            },
            isError = isError,
            errorTrigger = errorTrigger,
            errorMessage = "Max character limit 100 reached",
            label = "Text", fontFamily = Poppins,
            placeholder = "Enter your text"
        )
        Spacer(modifier = Modifier.height(20.dp))
        BouncyButton(
            onClick = {
                textToAnimate = inputText
                animationTrigger++
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            containerColor = wineash,
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Click to view Typing Animation",
                fontWeight = FontWeight.SemiBold,
                fontFamily = Poppins,
                maxLines = 1
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        MorphingButton(
            onClick = { textToAnimate = "" },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            containerColor = Color.Red.copy(alpha = 0.85f),
            contentColor = Color.White
        ){
            Text(
                text = "Clear Card Content"
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Toggle Switch",
                fontSize = 18.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold
            )
            ElasticToggleSwitch(
                checked = isSwitchOn,
                onCheckedChange = { isSwitchOn = it }
            )
            Text(
                text = "Liked",
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
            PremiumHeartButton(
                isLiked = isLiked,
                onLikeChanged = { isLiked = it },
                iconSize = 44.dp,
                unlikedColor = Color(0xFF4A4A4A)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 120.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        )
            {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .animateContentSize()
            ) {
                TypewriterText(
                    text = textToAnimate,
                    trigger = animationTrigger,
                    cursorColor = wineash
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

// 2nd screen / Search Screen
@Composable
fun SearchScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
    verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            tint = wineash.copy(alpha = 0.1f),
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Search Screen",
            fontFamily = Poppins,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp
        )
    }
}


// 3nd screen / Inbox Screen
@Composable
fun InboxScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.Message,
            contentDescription = null,
            tint = wineash.copy(alpha = 0.1f),
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Inbox Screen",
            fontFamily = Poppins,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp
        )
    }
}

// 4nd screen / Profile Screen
@Composable
fun ProfileScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = null,
            tint = wineash.copy(alpha = 0.1f),
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Profile Screen",
            fontFamily = Poppins,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp
        )
    }
}

@Composable
fun TypewriterText(
    text: String,
    modifier: Modifier = Modifier,
    trigger: Int = 0,
    cursorColor: Color = Color.Black,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current
) {
    var charIndex by remember(text, trigger) { mutableIntStateOf(0) }
    var isTyping by remember(text, trigger) { mutableStateOf(false) }

    LaunchedEffect(text, trigger) {
        charIndex = 0
        if (text.isNotEmpty()) {
            isTyping = true
            while (charIndex < text.length) {
                delay(35L)
                charIndex++
            }
            isTyping = false
        }
    }

    val displayedText = remember(text, charIndex) {
        text.take(charIndex)
    }

    val infiniteTransition = rememberInfiniteTransition(label = "CursorBlink")
    val blinkingAlpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 900
                1f at 0
                1f at 450
                0f at 480
                0f at 900
            },
            repeatMode = RepeatMode.Restart
        ),
        label = "CursorAlpha"
    )

    val cursorAlpha = if (isTyping) 1f else blinkingAlpha
    val cursorId = "sharp_block_cursor"

    val actualFontSize = if (fontSize != TextUnit.Unspecified) fontSize else 16.sp

    val inlineContent = remember(cursorColor, cursorAlpha, actualFontSize) {
        mapOf(
            cursorId to InlineTextContent(
                Placeholder(
                    width = (actualFontSize.value * 0.5f).sp,
                    height = actualFontSize,
                    placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
                )
            ) {
                Canvas(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer { alpha = cursorAlpha }
                ) {
                    drawRect(
                        color = cursorColor,
                        topLeft = Offset(x = 1.dp.toPx(), y = 0f),

                        size = Size(width = size.width - 1.dp.toPx(), height = size.height)
                    )
                }
            }
        )
    }

    Text(
        text = buildAnnotatedString {
            append(displayedText)
            if (text.isNotEmpty()) {
                appendInlineContent(cursorId, "[cursor]")
            }
        },
        inlineContent = inlineContent,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout,
        style = style
    )
}
/// Bouncy Button
@Composable
fun BouncyButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    containerColor: Color = Color(0xFFCE7C7C),
    contentColor: Color = Color.White,
    shape: Shape = RoundedCornerShape(12.dp),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val scale = remember { Animatable(1f) }

    LaunchedEffect(interactionSource) {

        interactionSource.interactions.collectLatest { interaction ->
            when (interaction) {
                is PressInteraction.Press -> {
                    scale.animateTo(
                        targetValue = 0.92f,
                        animationSpec = tween(durationMillis = 70)
                    )
                }
                is PressInteraction.Release, is PressInteraction.Cancel -> {

                    if (scale.value > 0.95f) {
                        scale.animateTo(
                            targetValue = 0.92f,
                            animationSpec = tween(durationMillis = 50)
                        )
                    }

                    scale.animateTo(
                        targetValue = 1f,
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessMedium // দ্রুত রিকভারির জন্য StiffnessMedium সবচেয়ে প্রফেশনাল
                        )
                    )
                }
            }
        }
    }

    Button(
        onClick = onClick,
        modifier = modifier.graphicsLayer {
            scaleX = scale.value
            scaleY = scale.value
        },
        enabled = enabled,
        interactionSource = interactionSource,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        contentPadding = contentPadding,
        content = content
    )
}
// Morphing Button

@Composable
fun MorphingButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    containerColor: Color = Color(0xFF425184),
    contentColor: Color = Color.White,
    defaultRadius: Dp = 50.dp,
    pressedRadius: Dp = 12.dp,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    val isPressed by interactionSource.collectIsPressedAsState()

    val cornerRadius by animateDpAsState(
        targetValue = if (isPressed) pressedRadius else defaultRadius,
        animationSpec = tween(durationMillis = 150, easing = FastOutSlowInEasing),
        label = "MorphingShape"
    )

    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        shape = RoundedCornerShape(cornerRadius),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        contentPadding = contentPadding,
        content = content
    )
}

// Animated Toggle Switch
@Composable
fun ElasticToggleSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    width: Dp = 72.dp,
    height: Dp = 40.dp,
    checkedTrackColor: Color = Color(0xFF2898FF),
    uncheckedTrackColor: Color = Color(0xFF2C3246),
    thumbColor: Color = Color.White,
    padding: Dp = 4.dp
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val progress by animateFloatAsState(
        targetValue = if (checked) 1f else 0f,
        animationSpec = spring(
            dampingRatio = 0.65f,
            stiffness = Spring.StiffnessMediumLow
        ),
        label = "ToggleProgress"
    )

    val pressFactor by animateFloatAsState(
        targetValue = if (isPressed) 1f else 0f,
        animationSpec = spring(stiffness = Spring.StiffnessMedium),
        label = "PressStretch"
    )

    val trackColor by animateColorAsState(
        targetValue = if (checked) checkedTrackColor else uncheckedTrackColor,
        animationSpec = tween(durationMillis = 250),
        label = "TrackColor"
    )

    val baseThumbSize = height - (padding * 2)
    val maxStretch = baseThumbSize * 0.45f

    val inTransitStretch = (4f * progress * (1f - progress)).coerceIn(0f, 1f)
    val stretchFactor = maxOf(inTransitStretch, pressFactor * 0.55f)

    val currentThumbWidth = baseThumbSize + (maxStretch * stretchFactor)
    val maxTravel = width - (padding * 2) - currentThumbWidth

    Box(
        modifier = modifier
            .width(width)
            .height(height)
            .clip(CircleShape)
            .background(trackColor)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { onCheckedChange(!checked) }
            )
            .padding(padding),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .offset {
                    IntOffset(
                        x = (maxTravel.toPx() * progress).roundToInt(),
                        y = 0
                    )
                }
                .size(width = currentThumbWidth, height = baseThumbSize)
                .shadow(elevation = 3.dp, shape = CircleShape)
                .clip(CircleShape)
                .background(thumbColor)
        )
    }
}

// Premium Heart Button
@Composable
fun PremiumHeartButton(
    isLiked: Boolean,
    onLikeChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    iconSize: Dp = 48.dp,
    likedColor: Color = Color(0xFFFF4B6A),
    unlikedColor: Color = Color.White
) {
    val interactionSource = remember { MutableInteractionSource() }

    val heartScale = remember { Animatable(1f) }
    val burstProgress = remember { Animatable(0f) }
    val ringProgress = remember { Animatable(0f) }

    var isFirstRender by remember { mutableStateOf(true) }

    LaunchedEffect(isLiked) {
        if (isFirstRender) {
            isFirstRender = false
            return@LaunchedEffect
        }

        if (isLiked) {
            coroutineScope {
                launch {
                    heartScale.snapTo(0.6f)
                    heartScale.animateTo(
                        targetValue = 1f,
                        animationSpec = spring(
                            dampingRatio = 0.42f, // পারফেক্ট বাউন্স
                            stiffness = Spring.StiffnessMedium
                        )
                    )
                }
                launch {
                    ringProgress.snapTo(0f)
                    ringProgress.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
                    )
                }
                launch {
                    burstProgress.snapTo(0f)
                    burstProgress.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(durationMillis = 550, easing = LinearOutSlowInEasing)
                    )
                }
            }
        } else {
            burstProgress.snapTo(0f)
            ringProgress.snapTo(0f)
            heartScale.animateTo(0.8f, tween(80))
            heartScale.animateTo(1f, spring(dampingRatio = Spring.DampingRatioMediumBouncy))
        }
    }

    val currentColor by animateColorAsState(
        targetValue = if (isLiked) likedColor else unlikedColor,
        animationSpec = tween(150),
        label = "HeartColor"
    )

    Box(
        modifier = modifier
            .size(iconSize * 2f)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { onLikeChanged(!isLiked) }
            ),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val center = Offset(size.width / 2f, size.height / 2f)
            val baseRadius = iconSize.toPx() / 2f

            val rProg = ringProgress.value
            if (rProg > 0f && rProg < 1f) {
                val ringRadius = baseRadius * (0.6f + rProg * 1.15f)
                val ringAlpha = (1f - rProg).coerceIn(0f, 1f)
                val ringStroke = (3.5.dp.toPx() * (1f - rProg * 0.7f)).coerceAtLeast(1f)

                drawCircle(
                    color = likedColor.copy(alpha = ringAlpha * 0.65f),
                    radius = ringRadius,
                    center = center,
                    style = Stroke(width = ringStroke)
                )
            }

            val bProg = burstProgress.value
            if (bProg > 0f && bProg < 1f) {
                val particleCount = 8
                val maxRadius = baseRadius * 1.85f

                val headProgress = (bProg * 1.25f).coerceAtMost(1f)
                val tailProgress = ((bProg - 0.18f) * 1.25f).coerceIn(0f, 1f)

                val headDist = baseRadius * 0.7f + (maxRadius - baseRadius * 0.7f) * headProgress
                val tailDist = baseRadius * 0.7f + (maxRadius - baseRadius * 0.7f) * tailProgress

                val particleAlpha = if (bProg > 0.7f) (1f - (bProg - 0.7f) / 0.3f) else 1f

                for (i in 0 until particleCount) {
                    val angleRad = Math.toRadians((i * 45.0) - 90.0)
                    val cosA = cos(angleRad).toFloat()
                    val sinA = sin(angleRad).toFloat()

                    val startPoint = Offset(
                        x = center.x + tailDist * cosA,
                        y = center.y + tailDist * sinA
                    )
                    val endPoint = Offset(
                        x = center.x + headDist * cosA,
                        y = center.y + headDist * sinA
                    )

                    drawLine(
                        color = likedColor.copy(alpha = particleAlpha.coerceIn(0f, 1f)),
                        start = startPoint,
                        end = endPoint,
                        strokeWidth = 3.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                }
            }

            scale(scale = heartScale.value, pivot = center) {
                val hSize = iconSize.toPx() * 0.85f
                val left = center.x - hSize / 2f
                val top = center.y - hSize / 2f

                val heartPath = Path().apply {
                    val w = hSize
                    val h = hSize

                    moveTo(left + w * 0.5f, top + h * 0.86f)
                    cubicTo(
                        left + w * 0.12f, top + h * 0.60f,
                        left - w * 0.05f, top + h * 0.26f,
                        left + w * 0.24f, top + h * 0.12f
                    )
                    cubicTo(
                        left + w * 0.38f, top + h * 0.05f,
                        left + w * 0.5f, top + h * 0.18f,
                        left + w * 0.5f, top + h * 0.27f
                    )
                    cubicTo(
                        left + w * 0.5f, top + h * 0.18f,
                        left + w * 0.62f, top + h * 0.05f,
                        left + w * 0.76f, top + h * 0.12f
                    )
                    cubicTo(
                        left + w * 1.05f, top + h * 0.26f,
                        left + w * 0.88f, top + h * 0.60f,
                        left + w * 0.5f, top + h * 0.86f
                    )
                    close()
                }

                if (isLiked) {
                    drawPath(
                        path = heartPath,
                        color = currentColor,
                        style = Fill
                    )
                } else {
                    drawPath(
                        path = heartPath,
                        color = currentColor,
                        style = Stroke(
                            width = 2.8.dp.toPx(),
                            cap = StrokeCap.Round,
                            join = StrokeJoin.Round
                        )
                    )
                }
            }
        }
    }
}


// ShakeOutlinedTextField
@Composable
fun ShakeOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Text",
    placeholder: String = "Enter your text",
    isError: Boolean = false,
    errorTrigger: Int = 0,
    errorMessage: String = "Max character limit 100 reached",
    maxLines: Int = 4,
    fontFamily: FontFamily = Poppins,
    errorColor: Color = Color(0xFFFF4B6A),

    shape: Shape = RoundedCornerShape(12.dp)
) {
    val shakeOffset = remember { Animatable(0f) }

    LaunchedEffect(errorTrigger, isError) {
        if (isError && errorTrigger > 0) {
            shakeOffset.snapTo(0f)
            shakeOffset.animateTo(
                targetValue = 0f,
                animationSpec = keyframes {
                    durationMillis = 420
                    -16f at 50 using FastOutLinearInEasing
                    16f at 110 using FastOutLinearInEasing
                    -12f at 170 using FastOutLinearInEasing
                    12f at 230 using FastOutLinearInEasing
                    -6f at 290 using FastOutLinearInEasing
                    6f at 350 using FastOutLinearInEasing
                    0f at 420
                }
            )
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer {
                translationX = shakeOffset.value * density
            }
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = label, fontFamily = fontFamily) },
            placeholder = { Text(text = placeholder, fontFamily = fontFamily) },
            modifier = Modifier.fillMaxWidth(),
            maxLines = maxLines,
            isError = isError,
            shape = shape,
            colors = OutlinedTextFieldDefaults.colors(
                errorBorderColor = errorColor,
                errorLabelColor = errorColor,
                errorCursorColor = errorColor
            )
        )

        AnimatedVisibility(
            visible = isError,
            enter = fadeIn(tween(200)) + expandVertically(tween(200)),
            exit = fadeOut(tween(150)) + shrinkVertically(tween(150))
        ) {
            Text(
                text = errorMessage,
                fontSize = 13.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                color = errorColor,
                modifier = Modifier.padding(start = 12.dp, top = 6.dp)
            )
        }
    }
}

/*
// Custom SlidingNavigationBar Function
@Composable
fun SlidingNavigationBar(
    navItems: List<BottomNavItem>,
    selectedIndex: Int,
    onItemClick: (BottomNavItem) -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = Color.White,
    indicatorColor: Color = wineash,
    selectedIconColor: Color = Color.White,
    selectedTextColor: Color = wineash,
    unselectedColor: Color = Color.Gray
) {
    val animatedIndex by animateFloatAsState(
        targetValue = selectedIndex.toFloat(),
        animationSpec = spring(
            dampingRatio = 0.8f,
            stiffness = Spring.StiffnessLow + 100f
        ),
        label = "SlidingPillIndex"
    )

    Surface(
        color = containerColor,
        shadowElevation = 8.dp,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.navigationBars)
                .height(80.dp)
                .drawBehind {

                    val tabWidth = size.width / navItems.size
                    val pillWidth = 64.dp.toPx()
                    val pillHeight = 32.dp.toPx()

                    val currentTabCenter = (animatedIndex * tabWidth) + (tabWidth / 2f)
                    val pillLeft = currentTabCenter - (pillWidth / 2f)
                    val pillTop = 12.dp.toPx()

                    drawRoundRect(
                        color = indicatorColor,
                        topLeft = Offset(x = pillLeft, y = pillTop),
                        size = Size(width = pillWidth, height = pillHeight),
                        cornerRadius = CornerRadius(x = pillHeight / 2f, y = pillHeight / 2f)
                    )
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            navItems.forEachIndexed { index, item ->
                val isSelected = selectedIndex == index
                val interactionSource = remember { MutableInteractionSource() }

                val iconTint by animateColorAsState(
                    targetValue = if (isSelected) selectedIconColor else unselectedColor,
                    animationSpec = tween(durationMillis = 200),
                    label = "IconTint"
                )
                val textColor by animateColorAsState(
                    targetValue = if (isSelected) selectedTextColor else unselectedColor,
                    animationSpec = tween(durationMillis = 200),
                    label = "TextColor"
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null,
                            onClick = { onItemClick(item) }
                        )
                        .padding(top = 12.dp, bottom = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Box(
                        modifier = Modifier.size(width = 64.dp, height = 32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            tint = iconTint
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = item.title,
                        fontFamily = Poppins,
                        fontSize = 12.sp,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                        color = textColor,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

*/


// Custom SlidingNavigationBar Function(Custom Glass Degsin)
@Composable
fun SlidingNavigationBar(
    navItems: List<BottomNavItem>,
    selectedIndex: Int,
    onItemClick: (BottomNavItem) -> Unit,
    modifier: Modifier = Modifier,
    indicatorColor: Color = wineash,
    selectedIconColor: Color = Color.White,
    unselectedIconColor: Color = Color(0xFF75757A)
) {
    val animatedIndex by animateFloatAsState(
        targetValue = selectedIndex.toFloat(),
        animationSpec = spring(
            dampingRatio = 0.82f,
            stiffness = Spring.StiffnessMediumLow
        ),
        label = "SlidingPillIndex"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.navigationBars)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(62.dp)
                .shadow(
                    elevation = 6.dp,
                    shape = CircleShape,
                    ambientColor = Color.Black.copy(alpha = 0.25f),
                    spotColor = Color.Black.copy(alpha = 0.25f)
                ),
            shape = CircleShape,
            color = Color.White.copy(alpha = 0.94f),
            tonalElevation = 0.dp,
            shadowElevation = 0.dp,
            border = BorderStroke(
                width = 1.dp,
                color = Color(0xFFEEEEEE)
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 5.dp)
                    .drawBehind {

                        val tabWidth = size.width / navItems.size
                        val pillPaddingH = 3.dp.toPx()
                        val pillPaddingV = 5.dp.toPx()

                        val pillWidth = tabWidth - (pillPaddingH * 2)
                        val pillHeight = size.height - (pillPaddingV * 2)

                        val pillLeft = (animatedIndex * tabWidth) + pillPaddingH
                        val pillTop = pillPaddingV

                        drawRoundRect(
                            color = indicatorColor,
                            topLeft = Offset(x = pillLeft, y = pillTop),
                            size = Size(width = pillWidth, height = pillHeight),
                            cornerRadius = CornerRadius(x = pillHeight / 2f, y = pillHeight / 2f)
                        )
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                navItems.forEachIndexed { index, item ->
                    val isSelected = selectedIndex == index
                    val interactionSource = remember { MutableInteractionSource() }

                    val iconTint by animateColorAsState(
                        targetValue = if (isSelected) selectedIconColor else unselectedIconColor,
                        animationSpec = tween(durationMillis = 200),
                        label = "IconTint"
                    )

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null,
                                onClick = { onItemClick(item) }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            tint = iconTint,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}