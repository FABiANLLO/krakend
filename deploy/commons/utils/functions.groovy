def defineValue(value, defaultValue) {
    return "${value ?: defaultValue}"
}

def validateDefinedValue(value, message) {
    if (!value) {
        echo message
    }
}

def padNumber(num, length) {
    return String.format("%0${length}d", num.toInteger())
}

return this
