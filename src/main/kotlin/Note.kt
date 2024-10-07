import java.util.Scanner

class Note(
    val title: String,
    val content: String
)

class NoteMenu(
    val archive: Archive,
    val onReturn: () -> Unit
) {
    private val scanner = Scanner(System.`in`)

    fun showMenu() {
        while (true) {
            val menu = Menu(
                listOf(
                    Pair("Назад", { returnToArchiveMenu() }),
                    Pair("Создать заметку", { createNote() }),
                    Pair("Список заметок", { listNotes() })
                )
            )
            menu.showMenu()
        }
    }

    private fun returnToArchiveMenu() {
        onReturn.invoke()
    }

    private fun createNote() {
        var title: String
        while (true) {
            println("Введите название заметки:")
            title = scanner.nextLine()
            if (title.isBlank()) {
                println("Название заметки не может быть пустым. Введите название.")
            } else {
                break
            }
        }

        var content: String
        while (true) {
            println("Введите содержимое заметки:")
            content = scanner.nextLine()
            if (content.isBlank()) {
                println("Содержимое заметки не может быть пустым.")
            } else {
                break
            }
        }

        archive.addNote(Note(title, content))
        println("Заметка '$title' создана.")
    }

    private fun listNotes() {
        while (true) {
            val notes = archive.listNotes()
            if (notes.isEmpty()) {
                println("В данном архиве еще нет заметок.")
                return
            }

            println("Список заметок:")
            val noteOptions = mutableListOf<Pair<String, () -> Unit>>()

            noteOptions.add(Pair("Назад", { showMenu() }))

            notes.forEachIndexed { index, note ->
                noteOptions.add(Pair(note.title) { viewNoteContent(note) })
            }

            val menu = Menu(noteOptions)
            menu.showMenu()
        }
    }

    private fun viewNoteContent(note: Note) {
        println("\"${note.title}\"")
        println()
        println(note.content)
    }
}