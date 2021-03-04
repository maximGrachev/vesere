package ru.maxgrachev.vesere.utils

enum class MaintenanceTask(id: Int, name: String) {
    OIL_CHANGE(1, name="Oil change"),
    ANTIFREEZE_CHANGE(2, "Antifreeze change"),
    OTHER(3, "Other")
}