package polyrhythmmania.discord

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.roundToLong


object DefaultPresences {
    
    fun idle(): Presence = Presence(state = "")
    fun inEditor(): Presence = Presence(state = "Editing a level")
    fun playingLevel(): Presence = Presence(state = "Playing a level")
    fun playingPractice(): Presence = Presence(state = "Playing a practice mode")
    fun playingEndlessMode(): Presence = Presence(state = "Playing Endless Mode", startTimestamp = System.currentTimeMillis() / 1000L)
    fun playingDailyChallenge(date: LocalDate): Presence = Presence(state = "Playing the Daily Challenge (${date.format(DateTimeFormatter.ISO_DATE)})", startTimestamp = System.currentTimeMillis() / 1000L)
    fun playingDunk(): Presence = Presence(state = "Playing Polyrhythm: Dunk", startTimestamp = System.currentTimeMillis() / 1000L)
    fun playingAssemble(): Presence = Presence(state = "Playing Polyrhythm: Assemble")

}