package polyrhythmmania.editor

import paintbox.binding.FloatVar
import paintbox.binding.ReadOnlyFloatVar
import paintbox.binding.ReadOnlyVar


class TrackView {
    val beat: FloatVar = FloatVar(0f)
    val renderScale: FloatVar = FloatVar(1f)
    val pxPerBeat: ReadOnlyFloatVar = FloatVar {
        72f * renderScale.useF()
    }
    
    fun translateBeatToX(beat: Float): Float {
        return (beat - this.beat.get()) * this.pxPerBeat.get()
    }
    
    fun translateXToBeat(x: Float): Float {
        return (x / this.pxPerBeat.get()) + this.beat.get()
    }
}
