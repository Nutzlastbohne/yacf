README for yacf
==========================

Chord implementation is not spot on:
    - RelativeChord looks like a ChordFormula implementation...
    - AbsoluteChord is used for ChordTab, even though RelativeChord should do the trick.
        Currently I use AbsoluteNotes, ignoring the octave. But this seems clumsy.
        Problem is, RelativeChord doesn't know jack about BasicNotes.
        Also, RelativeChord needs mixture of relative and absolute Note 
		-> Need to be able to track ranges larger than an octave, but don't need to track the whole note range.
        ... maybe something like a relativeOctave?

ChordTab has a problem(?):
    - Insertion of note positions currently assumes they are entered in a specific order.
        Problem: String-Indexes are usually counted bottom up, Chord-Notation is top-down...
        Maybe not a problem after all: String count seems arbitrary, because it goes against the direction of the respective Hz-Value.
        Sooo... just keep it as is and just retrofit the toString() Method?
        
ChordTab support to quickly identify duplicate notes?


---=== Ideas for next Iteration ===---

Seems like it has no value for a note to know its absolute position.
-> knowing amount of BasicNotes to calculate absolute position (with absolute octave) is superfluous

INote
- INote getNext()
- INote getPrevious()
- INote getNoteAtDistance(int x)

Model:
BasicNote<T ext BasicNote>
- String symbol
- int ordinal
- BasicNote next/previous

ChordNote ext BasicNote
- boolean isOptional
- int relativeOctave

AbsoluteNote ext ChordNote
- int absoluteOctave

PlacedNote ext ChordNote
- int String
- int Fret

BasicChord
- String Name
- int[] Pattern

RelativeChord
- ChordNote[]

AbsoluteChord
- AbsoluteNote[]

Tab
- PlacedNote[]
