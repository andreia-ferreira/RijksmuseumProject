package pt.penguin.data.cache

abstract class ValueMemoryDataSource<Type> {

    private var value: Type? = null

    fun set(value: Type) {
        this.value = value
    }

    fun get(): Type? = value

    fun remove() {
        value = null
    }

    fun hasValue() = value != null
}