package com.example.common

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import kotlin.reflect.KProperty

val MppTextImplementation: ProvidableCompositionLocal<@Composable (text: String, size: Int) -> Unit> =
    staticCompositionLocalOf { { _, _ -> TODO("provide MppTextImplementation") } }

val MppTextColor = staticCompositionLocalOf { Color.Green }

@Composable
fun MppText(text: String, size: Int = 24, config: MppTextConfig.() -> Unit = {}) {
    ProvideConfig(config = {
        config(object : MppTextConfig, ProvideConfig by this {})
    }) {
        MppTextImplementation.current(text, size)
    }
}

interface MppTextConfig : ProvideConfig

fun MppTextConfig.color(color: Color) {
    provide(MppTextColor provides color)
}

fun interface ProvideConfig {
    fun provide(value: ProvidedValue<*>)
}

fun interface ProvideScope {
    fun provide(value: ProvidedValue<*>)
    operator fun <T> WithConfig.ConfigProperty<T>.invoke(value: T) {
        provide(compositionLocalProvider provides value)
    }
}

abstract class WithConfig {
    interface GetValue<T> {
        operator fun getValue(obj: Any?, property: KProperty<*>): ConfigProperty<T>
    }
    val map: MutableMap<KProperty<*>, ConfigProperty<*>> = mutableMapOf()

    class ConfigProperty<T>(defaultFactory: () -> T = { error("not provided") }) {
        val compositionLocalProvider = staticCompositionLocalOf(defaultFactory)
        operator fun ProvideScope.invoke(value: T) {
            provide(compositionLocalProvider provides value)
        }
        infix fun ProvideScope.set(value: T) {
            provide(compositionLocalProvider provides value)
        }
    }

    fun <T> registerProperty() = ConfigProperty<T>()

    fun <T> property():GetValue<T> = object:GetValue<T> {
        override fun getValue(obj: Any?, property: KProperty<*>): ConfigProperty<T> {
            return map.getOrPut(property) { ConfigProperty<T>() } as ConfigProperty<T>
        }
    }
}

fun interface Configure<T : WithConfig> {
    fun provide(value: ProvidedValue<*>)
    operator fun WithConfig.ConfigProperty<T>.invoke(value: T) {

    }
}

object MppTextField : WithConfig() {
    @Composable
    operator fun invoke(text: String, size: Int = 24) {

    }
}

val MppTextField.color by MppTextField.property<Color>()

@Composable
fun configure(conf: ProvideScope.() -> Unit, content: @Composable () -> Unit) {
    CompositionLocalProvider(
        *buildList {
            ProvideScope { add(it) }.conf()
        }.toTypedArray(),
        content = content
    )
}

@Composable
fun todo() {
    configure({
        MppTextField.color(Color.Red)
    }) {
        MppTextField("Hello")
    }
}
