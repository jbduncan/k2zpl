package info.mking.k2zpl.command

import info.mking.k2zpl.builder.ZplBuilder
import info.mking.k2zpl.command.options.ZplJustification

internal data class FieldOrigin(
    val x: Int,
    val y: Int,
    val justification: ZplJustification
) : ZplCommand {
    init {
        require(x in 0..32000) { "x must be within 0 to 32000" }
        require(y in 0..32000) { "y must be within 0 to 32000" }
    }

    override val command: CharSequence = "^FO"
    override val parameters: LinkedHashMap<CharSequence, Any?> = buildLinkedMap {
        put("x", x)
        put("y", y)
        put("j", justification)
    }
}

/**
 * Sets the origin for a field.
 * @param x The x-coordinate of the field origin.
 * @param y The y-coordinate of the field origin.
 * @param justification The alignment of the field
 */
fun ZplBuilder.fieldOrigin(
    x: Int,
    y: Int,
    justification: ZplJustification = ZplJustification.LEFT
) {
    command(
        FieldOrigin(
            x = x,
            y = y,
            justification = justification
        )
    )
}