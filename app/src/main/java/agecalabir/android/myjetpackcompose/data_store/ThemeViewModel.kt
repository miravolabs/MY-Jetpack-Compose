package agecalabir.android.myjetpackcompose.data_store

/*
@HiltViewModel
class ThemeViewModel @Inject constructor(
    @ApplicationContext private val context: Context
): ViewModel() {
    val isDark = context.dataStore.data.map {
            it[IS_DARK_KEY]
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000)
        , null
    )

    fun toggleTheme(isDarkEnable : Boolean){
        viewModelScope.launch {
            context.dataStore.edit { preferences ->
                preferences[IS_DARK_KEY] = isDarkEnable
            }
        }
    }
}

// MainActivity Code for remember (04-11-2026)


/*
 @AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
       val splashScreen =   installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val activity = LocalActivity.current as Activity

            val viewModel : ThemeViewModel = hiltViewModel()
            val isDark by viewModel.isDark.collectAsState()

            splashScreen.setKeepOnScreenCondition {
                viewModel.isDark.value == null
            }

            if(isDark != null) {

                SideEffect {
                    val window = activity.window
                    val controller = WindowCompat.getInsetsController(window, window.decorView)
                    controller.isAppearanceLightStatusBars = !isDark!!
                    controller.isAppearanceLightNavigationBars = !isDark!!
                }

                MyJetpackComposeTheme(darkTheme = isDark!!) {
                    DataStorePractice(
                        isDark = isDark!!,
                        onToggle = { viewModel.toggleTheme(it) }
                    )
                    //  MultiStepNavigationApp(sharedViewModel = viewModel())
                    //TaskScreen()
                    //Notification_Screen()
                    //LearningAnimationsScreen()
                    //AnimationAppScreen()
                    //   UsageScreen()

                }
            }
        }
    }
}
*/