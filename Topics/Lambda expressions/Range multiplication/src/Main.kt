val lambda: (Long, Long) -> Long = { a, b ->
    var product = 1L
    for (i in a..b)
        product *= i
    product
}