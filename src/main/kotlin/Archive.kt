import java.util.Scanner

class Archive(val title: String) {
    private val notes = mutableListOf<Note>()

    fun addNote(note: Note) {
        notes.add(note)
    }

    fun listNotes(): List<Note> {
        return notes
    }
}

class ArchiveMenu {
    private val archives = mutableListOf<Archive>()
    private val scanner = Scanner(System.`in`)

    fun showMenu() {
        while (true) {
            val menu = Menu(
                listOf(
                    Pair("Выход", { System.exit(0) }),
                    Pair("Создать архив", { createArchive() }),
                    Pair("Список архивов", { listArchives() })
                )
            )
            menu.showMenu()
        }
    }

    private fun createArchive() {
        while (true) {
            println("Введите название архива:")
            val title = scanner.nextLine()
            if (title.isBlank()) {
                println("Название архива не может быть пустым.")
                continue
            }
            archives.add(Archive(title))
            println("Архив '$title' создан.")
            return
        }
    }

    private fun listArchives() {
        while (true) {
            if (archives.isEmpty()) {
                println("Еще пока не создано ни одного архива.")
                return
            }

            val archiveOptions = mutableListOf<Pair<String, () -> Unit>>()
            archiveOptions.add(Pair("Назад", { showMenu() }))

            archives.forEach { archive ->
                archiveOptions.add(Pair(archive.title) {
                    val noteMenu = NoteMenu(archive) { showMenu() }
                    noteMenu.showMenu()
                })
            }

            val menu = Menu(archiveOptions)
            menu.showMenu()
        }
    }
}