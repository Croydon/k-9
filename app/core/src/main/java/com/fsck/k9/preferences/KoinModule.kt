package com.fsck.k9.preferences

import com.fsck.k9.Preferences
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val preferencesModule = module {
    factory {
        SettingsExporter(
            contentResolver = get(),
            preferences = get(),
            folderSettingsProvider = get(),
            folderRepositoryManager = get()
        )
    }
    factory { FolderSettingsProvider(folderRepositoryManager = get()) }
    factory<AccountManager> { get<Preferences>() }
    single {
        RealGeneralSettingsManager(
            preferences = get(),
            coroutineScope = get(named("AppCoroutineScope"))
        )
    } bind GeneralSettingsManager::class
}
