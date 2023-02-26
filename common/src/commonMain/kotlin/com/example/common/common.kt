package com.example.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.staticCompositionLocalOf
import kotlin.reflect.KProperty

@Composable
fun ProvideConfig(config: ProvideConfig.() -> Unit, content: @Composable () -> Unit) {
    CompositionLocalProvider(
        *buildList {
            ProvideConfig { add(it) }.config()
        }.toTypedArray()
    ) {
        content()
    }
}

@Composable
fun configure(conf: ProvideScope.() -> Unit, content: @Composable () -> Unit) {
    CompositionLocalProvider(
        *buildList {
            ProvideScope { add(it) }.conf()
        }.toTypedArray(),
        content = content
    )
}

fun interface ProvideConfig {
    fun provide(value: ProvidedValue<*>)
}

fun interface ProvideScope {
    fun provide(value: ProvidedValue<*>)
    operator fun <T, R:WithConfig> WithConfig.ConfigProperty<T, R>.invoke(value:T):R {
        provide(provider provides value)
        return this.parent as R
    }
}

abstract class WithConfig {
    interface GetValue<T> {
        operator fun <R:WithConfig>getValue(obj: R, property: KProperty<*>): ConfigProperty<T, R>
    }
    val map: MutableMap<KProperty<*>, ConfigProperty<*, *>> = mutableMapOf()

    inner class ConfigProperty<T, R:WithConfig>(defaultFactory: () -> T) {
        val provider = staticCompositionLocalOf(defaultFactory)
        operator fun ProvideScope.invoke(value: T) {
            provide(provider provides value)
        }
        infix fun ProvideScope.set(value: T) {
            provide(provider provides value)
        }
        val parent: R = this@WithConfig as R
    }

    fun <T, R:WithConfig> property(defaultFactory:()->T):GetValue<T> = object:GetValue<T> {
        override fun <R : WithConfig> getValue(obj: R, property: KProperty<*>): ConfigProperty<T, R> {
            return map.getOrPut(property) { ConfigProperty<T, R>(defaultFactory) } as ConfigProperty<T, R>
        }

    }
}

