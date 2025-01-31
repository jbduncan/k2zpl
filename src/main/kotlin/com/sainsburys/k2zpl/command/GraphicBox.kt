package com.sainsburys.k2zpl.command

import com.sainsburys.k2zpl.builder.ZplBuilder
import com.sainsburys.k2zpl.command.options.ZplLineColor

internal data class GraphicBox(
    val width: Int,
    val height: Int,
    val thickness: Int = 1,
    val color: ZplLineColor = ZplLineColor.BLACK,
    val rounding: Int = 0
) : ZplCommand {
    init {
        require(width in 1..32000) { "Width must be between 1 and 32000" }
        require(height in 1..32000) { "Height must be between 1 and 32000" }
        require(thickness in 1..32000) { "Thickness must be between 1 and 32000" }
        require(rounding in 0..8) { "Rounding must be between 0 and 8" }
    }

    override val command: CharSequence = "^GB"
    override val parameters: Map<CharSequence, Any?> =
        addParameters(
            "w" to width,
            "h" to height,
            "t" to thickness, "c" to color.code,
            "r" to rounding
        )
}

/**
 * Draws a graphic box.
 * @param width The width of the box.
 * @param height The height of the box.
 * @param thickness The thickness of the box border.
 * @param color The color of the box border (optional).
 * @param rounding The corner rounding of the box (optional).
 */
fun ZplBuilder.graphicBox(
    width: Int,
    height: Int,
    thickness: Int = 1,
    color: ZplLineColor = ZplLineColor.BLACK,
    rounding: Int = 0
) {
    command(
        GraphicBox(
            width = width,
            height = height,
            thickness = thickness,
            color = color,
            rounding = rounding
        )
    )
}

/**
 * Draws a line using GraphicBox
 * @param width The width of the line
 * @param thickness The thickness of the line
 * @param color The color of the line
 */
fun ZplBuilder.line(width: Int, thickness: Int = 1, color: ZplLineColor = ZplLineColor.BLACK) {
    graphicBox(
        width = width,
        height = thickness,
        thickness = thickness,
        color = color
    )
}

/**
 * Draws a line using GraphicBox
 * @param x horizontal position of line
 * @param y vertical position of line
 * @param width The width of the line
 * @param thickness The thickness of the line
 * @param color The color of the line
 */
fun ZplBuilder.line(x: Int, y: Int, width: Int, thickness: Int = 1, color: ZplLineColor = ZplLineColor.BLACK) {
    fieldOrigin(x = x, y = y)
    graphicBox(
        width = width,
        height = thickness,
        thickness = thickness,
        color = color
    )
    fieldSeparator()
}