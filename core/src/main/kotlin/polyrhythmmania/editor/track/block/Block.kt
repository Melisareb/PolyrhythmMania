package polyrhythmmania.editor.track.block

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Align
import io.github.chrislo27.paintbox.binding.ReadOnlyVar
import io.github.chrislo27.paintbox.binding.Var
import io.github.chrislo27.paintbox.util.gdxutils.drawCompressed
import io.github.chrislo27.paintbox.util.gdxutils.drawRect
import io.github.chrislo27.paintbox.util.gdxutils.fillRect
import io.github.chrislo27.paintbox.util.gdxutils.scaleMul
import polyrhythmmania.editor.Editor
import polyrhythmmania.editor.TrackView
import polyrhythmmania.editor.pane.track.AllTracksPane
import polyrhythmmania.editor.pane.track.EditorTrackArea
import polyrhythmmania.editor.track.BlockType
import java.util.*


/**
 * A [Block] represents a timed event or series of events that is placed and manipulated in the editor.
 *
 * A [Block] has a temporal position, represented by [beat], and a visual non-zero [width]. It also has
 * a [trackIndex] which represents what track index the [Block] sits on.
 */
open class Block(val editor: Editor, blockTypes: EnumSet<BlockType>) {

    var beat: Float = 0f
    var width: Float = 1f
    var trackIndex: Int = 0
    val blockTypes: Set<BlockType> = blockTypes
    protected val defaultText: Var<String> = Var("")

    open fun render(batch: SpriteBatch, trackView: TrackView, editorTrackArea: EditorTrackArea,
                    offsetX: Float, offsetY: Float, trackHeight: Float, trackTint: Color) {
        defaultRender(batch, trackView, editorTrackArea, offsetX, offsetY, trackHeight, trackTint)
    }

    protected fun defaultRender(batch: SpriteBatch, trackView: TrackView, editorTrackArea: EditorTrackArea,
                                offsetX: Float, offsetY: Float, trackHeight: Float, trackTint: Color) {
        val renderX = editorTrackArea.beatToRenderX(offsetX, this.beat)
        batch.setColor(trackTint.r, trackTint.g, trackTint.b, 1f)
        batch.fillRect(renderX, editorTrackArea.trackToRenderY(offsetY, trackIndex) - trackHeight,
                editorTrackArea.beatToRenderX(offsetX, this.beat + width) - renderX,
                trackHeight)
        val border = 4f
        batch.setColor(trackTint.r * 0.7f, trackTint.g * 0.7f, trackTint.b * 0.7f, 1f)
        batch.drawRect(renderX, editorTrackArea.trackToRenderY(offsetY, trackIndex) - trackHeight,
                editorTrackArea.beatToRenderX(offsetX, this.beat + width) - renderX,
                trackHeight, border)
        
        val text = defaultText.getOrCompute()
        if (text.isNotEmpty()) {
            val textPadding = border + 2f
            editor.main.mainFontBoldBordered.useFont { font ->
                font.scaleMul(0.9f)
                font.drawCompressed(batch, text, renderX + textPadding,
                        editorTrackArea.trackToRenderY(offsetY, trackIndex) - textPadding - 1f,
                        editorTrackArea.beatToRenderX(offsetX, this.beat + width) - renderX - textPadding * 2, Align.left)
            }
        }
        
        if (editor.selectedBlocks[this] == true) {
            batch.setColor(0.1f, 1f, 1f, 0.333f)
            batch.fillRect(renderX, editorTrackArea.trackToRenderY(offsetY, trackIndex) - trackHeight,
                    editorTrackArea.beatToRenderX(offsetX, this.beat + width) - renderX,
                    trackHeight)
        }
    }

}