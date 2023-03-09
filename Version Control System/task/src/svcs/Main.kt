package svcs
import java.io.File
fun main(args: Array<String>) {
    val config: Map<String, String> = loadConfig()
    val command: String = if (args.isEmpty()) "--help" else args.first()
    val parameter: String = if (args.size > 1) args[1] else ""
    initVCS()
    when (command) {
        "config" -> {
            val filename: String = "vcs/config.txt"
            //if parameter is empty
            var content: String =
            if (parameter.isNullOrEmpty()) {
                var username: String = readFile(filename, "Please, tell me who you are.", "Please, tell me who you are.")
                if (username != "Please, tell me who you are.") {
                    username = "The username is $username."
                }
                username
            } else {
                 writeFile(filename, parameter)
                "The username is $parameter."
            }
            println(content)
        }
        "--help" -> println(config[command])
        "add" -> {
            val filename: String = "vcs/index.txt"
            val content: String =
            if (!parameter.isNullOrEmpty()) {
                var text: String ="Can't find '$parameter'."
                if (File(parameter).exists()) {
                    writeFile(filename,"$parameter\n",true)
                    text = "The file '$parameter' is tracked."
                }
                text
            } else {
                var text:String = readFile(filename, "Add a file to the index.", "Add a file to the index.")
                if (text != "Add a file to the index.") {
                   text = "Tracked files:\n$text"
                }
                text
            }
            print(content)
        }
        "log" -> println(config[command])
        "commit" -> println(config[command])
        "checkout" -> println(config[command])
        else -> print("'$command' is not a SVCS command.")
    }
}

fun loadConfig(): Map<String, String> {
    return mapOf(
        "config" to "Get and set a username.",
        "--help" to "These are SVCS commands:\n" +
                "config     Get and set a username.\n" +
                "add        Add a file to the index.\n" +
                "log        Show commit logs.\n" +
                "commit     Save changes.\n" +
                "checkout   Restore a file.",
        "add" to "Add a file to the index.",
        "log" to "Show commit logs.",
        "commit" to "Save changes.",
        "checkout" to "Restore a file."
    )
}

fun readFile(filename: String, notExistsText: String = "", emptyText: String = ""): String {
    if (File(filename).exists()){
        val file = File(filename)
        val content: String = file.readText()
        if (content.isNullOrEmpty()) {
            return emptyText
        }
        return content
    } else {
        return notExistsText
    }
}

fun writeFile(filename: String, content: String, append: Boolean = false): String {
    val file = File(filename)
    createFolder(file.parent.toString())
    if (append) {
        file.appendText(content)
    } else {
        file.writeText(content)
    }
    return file.readText()
}

fun createFolder(folderPath: String) {
    val directoryPath = File(folderPath)
    if (!directoryPath.exists()) {
        directoryPath.mkdir()
    }
}

fun initVCS() {
    createFolder("vcs")
    val filenameConfig: String = "vcs/config.txt"
    val filenameIndex: String = "vcs/index.txt"
    val config = readFile(filenameConfig,"0")
    if(config == "0") {
        writeFile(filenameConfig,"")
    }
    val index = readFile(filenameIndex,"0")
    if(config == "0") {
        writeFile(filenameIndex,"")
    }
}
