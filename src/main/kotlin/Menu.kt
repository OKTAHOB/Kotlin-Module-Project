class Menu(private val options: List<Pair<String, () -> Unit>>) {
    fun showMenu() {
        options.forEachIndexed { index, option ->
            println("$index. ${option.first}")
        }
        val selectedIndex = readLine()?.toIntOrNull()
        if (selectedIndex == null || selectedIndex !in options.indices) {
            println("Неправильный ввод или отсутствует пункт меню, проверьте ввод.")
            return
        }
        options[selectedIndex].second.invoke()
    }
}