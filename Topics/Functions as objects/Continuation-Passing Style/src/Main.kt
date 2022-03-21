fun square(value: Int, context: Any, continuation: (Int, Any) -> Unit) {
    val square = value * value
    return continuation(square, context)
}