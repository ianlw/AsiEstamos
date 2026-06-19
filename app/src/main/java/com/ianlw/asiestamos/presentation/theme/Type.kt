package com.ianlw.asiestamos.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.ianlw.asiestamos.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

// Font families
val InterFont = FontFamily(
    Font(googleFont = GoogleFont("Inter"), fontProvider = provider, weight = FontWeight.Light),
    Font(googleFont = GoogleFont("Inter"), fontProvider = provider, weight = FontWeight.Normal),
    Font(googleFont = GoogleFont("Inter"), fontProvider = provider, weight = FontWeight.Medium),
    Font(googleFont = GoogleFont("Inter"), fontProvider = provider, weight = FontWeight.SemiBold),
    Font(googleFont = GoogleFont("Inter"), fontProvider = provider, weight = FontWeight.Bold),
    Font(googleFont = GoogleFont("Inter"), fontProvider = provider, weight = FontWeight.ExtraBold),
)

val RobotoFont = FontFamily(
    Font(googleFont = GoogleFont("Roboto"), fontProvider = provider, weight = FontWeight.Light),
    Font(googleFont = GoogleFont("Roboto"), fontProvider = provider, weight = FontWeight.Normal),
    Font(googleFont = GoogleFont("Roboto"), fontProvider = provider, weight = FontWeight.Medium),
    Font(googleFont = GoogleFont("Roboto"), fontProvider = provider, weight = FontWeight.Bold),
)

val JetBrainsMonoFont = FontFamily(
    Font(googleFont = GoogleFont("JetBrains Mono"), fontProvider = provider, weight = FontWeight.Normal),
    Font(googleFont = GoogleFont("JetBrains Mono"), fontProvider = provider, weight = FontWeight.Medium),
    Font(googleFont = GoogleFont("JetBrains Mono"), fontProvider = provider, weight = FontWeight.Bold),
)

val OutfitFont = FontFamily(
    Font(googleFont = GoogleFont("Outfit"), fontProvider = provider, weight = FontWeight.Light),
    Font(googleFont = GoogleFont("Outfit"), fontProvider = provider, weight = FontWeight.Normal),
    Font(googleFont = GoogleFont("Outfit"), fontProvider = provider, weight = FontWeight.Medium),
    Font(googleFont = GoogleFont("Outfit"), fontProvider = provider, weight = FontWeight.SemiBold),
    Font(googleFont = GoogleFont("Outfit"), fontProvider = provider, weight = FontWeight.Bold),
)

// === LIQUID GLASS TYPOGRAPHY (Inter) ===
val LiquidGlassTypography = Typography(
    displayLarge = TextStyle(fontFamily = InterFont, fontWeight = FontWeight.Bold, fontSize = 48.sp, letterSpacing = (-1.5).sp),
    displayMedium = TextStyle(fontFamily = InterFont, fontWeight = FontWeight.Bold, fontSize = 36.sp, letterSpacing = (-0.5).sp),
    displaySmall = TextStyle(fontFamily = InterFont, fontWeight = FontWeight.SemiBold, fontSize = 28.sp),
    headlineLarge = TextStyle(fontFamily = InterFont, fontWeight = FontWeight.Bold, fontSize = 24.sp),
    headlineMedium = TextStyle(fontFamily = InterFont, fontWeight = FontWeight.SemiBold, fontSize = 20.sp),
    headlineSmall = TextStyle(fontFamily = InterFont, fontWeight = FontWeight.SemiBold, fontSize = 18.sp),
    titleLarge = TextStyle(fontFamily = InterFont, fontWeight = FontWeight.SemiBold, fontSize = 20.sp),
    titleMedium = TextStyle(fontFamily = InterFont, fontWeight = FontWeight.Medium, fontSize = 16.sp, letterSpacing = 0.15.sp),
    titleSmall = TextStyle(fontFamily = InterFont, fontWeight = FontWeight.Medium, fontSize = 14.sp, letterSpacing = 0.1.sp),
    bodyLarge = TextStyle(fontFamily = InterFont, fontWeight = FontWeight.Normal, fontSize = 16.sp, letterSpacing = 0.5.sp),
    bodyMedium = TextStyle(fontFamily = InterFont, fontWeight = FontWeight.Normal, fontSize = 14.sp, letterSpacing = 0.25.sp),
    bodySmall = TextStyle(fontFamily = InterFont, fontWeight = FontWeight.Normal, fontSize = 12.sp, letterSpacing = 0.4.sp),
    labelLarge = TextStyle(fontFamily = InterFont, fontWeight = FontWeight.Medium, fontSize = 14.sp, letterSpacing = 0.1.sp),
    labelMedium = TextStyle(fontFamily = InterFont, fontWeight = FontWeight.Medium, fontSize = 12.sp, letterSpacing = 0.5.sp),
    labelSmall = TextStyle(fontFamily = InterFont, fontWeight = FontWeight.Medium, fontSize = 10.sp, letterSpacing = 0.5.sp),
)

// === FINTECH DARK TYPOGRAPHY (Roboto body + JetBrains Mono for numbers) ===
val FintechDarkTypography = Typography(
    displayLarge = TextStyle(fontFamily = JetBrainsMonoFont, fontWeight = FontWeight.Bold, fontSize = 48.sp, letterSpacing = (-2).sp),
    displayMedium = TextStyle(fontFamily = JetBrainsMonoFont, fontWeight = FontWeight.Bold, fontSize = 36.sp, letterSpacing = (-1).sp),
    displaySmall = TextStyle(fontFamily = JetBrainsMonoFont, fontWeight = FontWeight.Bold, fontSize = 28.sp),
    headlineLarge = TextStyle(fontFamily = RobotoFont, fontWeight = FontWeight.Bold, fontSize = 24.sp),
    headlineMedium = TextStyle(fontFamily = RobotoFont, fontWeight = FontWeight.Bold, fontSize = 20.sp),
    headlineSmall = TextStyle(fontFamily = RobotoFont, fontWeight = FontWeight.Medium, fontSize = 18.sp),
    titleLarge = TextStyle(fontFamily = RobotoFont, fontWeight = FontWeight.Bold, fontSize = 20.sp),
    titleMedium = TextStyle(fontFamily = RobotoFont, fontWeight = FontWeight.Medium, fontSize = 16.sp),
    titleSmall = TextStyle(fontFamily = RobotoFont, fontWeight = FontWeight.Medium, fontSize = 14.sp),
    bodyLarge = TextStyle(fontFamily = RobotoFont, fontWeight = FontWeight.Normal, fontSize = 16.sp),
    bodyMedium = TextStyle(fontFamily = RobotoFont, fontWeight = FontWeight.Normal, fontSize = 14.sp),
    bodySmall = TextStyle(fontFamily = RobotoFont, fontWeight = FontWeight.Normal, fontSize = 12.sp),
    labelLarge = TextStyle(fontFamily = RobotoFont, fontWeight = FontWeight.Medium, fontSize = 14.sp),
    labelMedium = TextStyle(fontFamily = RobotoFont, fontWeight = FontWeight.Medium, fontSize = 12.sp),
    labelSmall = TextStyle(fontFamily = RobotoFont, fontWeight = FontWeight.Medium, fontSize = 10.sp),
)

// === MINIMAL PAPER TYPOGRAPHY (Outfit - clean, bold) ===
val MinimalPaperTypography = Typography(
    displayLarge = TextStyle(fontFamily = OutfitFont, fontWeight = FontWeight.Bold, fontSize = 48.sp, letterSpacing = (-1.5).sp),
    displayMedium = TextStyle(fontFamily = OutfitFont, fontWeight = FontWeight.Bold, fontSize = 36.sp, letterSpacing = (-0.5).sp),
    displaySmall = TextStyle(fontFamily = OutfitFont, fontWeight = FontWeight.SemiBold, fontSize = 28.sp),
    headlineLarge = TextStyle(fontFamily = OutfitFont, fontWeight = FontWeight.Bold, fontSize = 24.sp),
    headlineMedium = TextStyle(fontFamily = OutfitFont, fontWeight = FontWeight.SemiBold, fontSize = 20.sp),
    headlineSmall = TextStyle(fontFamily = OutfitFont, fontWeight = FontWeight.SemiBold, fontSize = 18.sp),
    titleLarge = TextStyle(fontFamily = OutfitFont, fontWeight = FontWeight.SemiBold, fontSize = 20.sp),
    titleMedium = TextStyle(fontFamily = OutfitFont, fontWeight = FontWeight.Medium, fontSize = 16.sp),
    titleSmall = TextStyle(fontFamily = OutfitFont, fontWeight = FontWeight.Medium, fontSize = 14.sp),
    bodyLarge = TextStyle(fontFamily = OutfitFont, fontWeight = FontWeight.Normal, fontSize = 16.sp),
    bodyMedium = TextStyle(fontFamily = OutfitFont, fontWeight = FontWeight.Normal, fontSize = 14.sp),
    bodySmall = TextStyle(fontFamily = OutfitFont, fontWeight = FontWeight.Normal, fontSize = 12.sp),
    labelLarge = TextStyle(fontFamily = OutfitFont, fontWeight = FontWeight.Medium, fontSize = 14.sp),
    labelMedium = TextStyle(fontFamily = OutfitFont, fontWeight = FontWeight.Medium, fontSize = 12.sp),
    labelSmall = TextStyle(fontFamily = OutfitFont, fontWeight = FontWeight.Medium, fontSize = 10.sp),
)

// Material You uses default M3 typography (system Google Sans)
val MaterialYouTypography = Typography() // defaults
