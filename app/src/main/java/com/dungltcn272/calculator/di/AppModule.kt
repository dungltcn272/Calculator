package com.dungltcn272.calculator.di

import android.content.Context
import android.content.SharedPreferences
import com.dungltcn272.calculator.caculator.utils.PrefKey
import com.dungltcn272.calculator.helper.PreferenceHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(PrefKey.KEY_PREF_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferenceHelper(sharedPreferences: SharedPreferences): PreferenceHelper {
        return PreferenceHelper(sharedPreferences)
    }
}