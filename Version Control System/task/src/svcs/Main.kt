package svcs

fun main(args: Array<String>) {
    val config: Map<String, String> = loadConfig()
    val command: String = if (args.isEmpty()) "--help" else args.first()
    if (config.containsKey(command)) {
        println(config[command])
    } else {
        print("'$command' is not a SVCS command.")
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