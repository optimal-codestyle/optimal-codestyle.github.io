![epam](https://habrastorage.org/webt/hq/rw/d-/hqrwd-extkiwaucd-id2punkdjk.jpeg)

_At a coarse level, it’s clear that some forms of layout are better than others._
_— Steve McConnell, Code Complete._

This article is about human vision and how knowing these features can help us improve the objective readability of our programs.
<cut />

# Содержание

* [Introduction](#intro)
* [Features of Human Vision](#visionspecifics)
  * [Field of View](#fieldofview)
  * [Ambient and Focal Vision](#ambientfocal)
  * [The Laws of Perceptual Organization](#laws)
  * [Asymmetry of the Visual Field](asymmetry)
* [How We Read Texts](#textreading)
* [Program Comprehension](#comprehension)
  * [Cognitive Models of Program Comprehension](#cognitivemodels)
    * [Concepts and terminology](#concepts)
    * [Top-Down Model](#topdownmodel)
    * [Bottom-Up Model](#downtopmodel)
    * [Opportunistic and Systematic Strategies](#strategies)
  * [Specifics of Reading Program Texts](#codereading)
  * [Role of Identifiers](#identifiers)
* [Basic Principles of Formatting](#analysis)
  * [Building the Visual Structure](#visualrepresentation)
  * [Line Length](#linelength)
  * [Names](#names)
  * [Spaces](#spaces)
  * [Arranging Curly Braces](#braces)
* [Conclusion](#resume)

<anchor>intro</anchor>
# Introduction
_At the risk of giving my fellow scientists good reason for displeasure, I am applying the principles in which I believe with a somewhat reckless one-sidedness, … partly because in certain cases it is useful to state a point of view with crude simplicity and leave the refinements to the ensuing play of thrust and counterthrust._
_— Rudolph Arnheim, Art and Visual Perception_

_First, we want to establish the idea that a computer language is not just a way of getting a computer to perform operations but rather that it is a novel formal medium for expressing ideas about methodology. Thus, programs must be written for people to read, and only incidentally for machines to execute._
_— Harold Abelson and Gerald Jay Sussman, Structure and Interpretation of Computer Programs._

_Indeed, the ratio of time spent reading vs. writing is well over 10:1… Because this ratio is so high, we want the reading of code to be easy, even if it makes the writing harder._
_— Robert C. Martin, Clean Code: A Handbook of Agile Software Craftsmanship._

Probably no one needs to prove that the _readability_ of the program text is one of the decisive factors that determine the success of its maintenance and development.

Usually, when evaluating the text of a program in terms of its _ease of perception_, the term _readability_ is used. Strictly speaking, they are not exactly the same thing, because, as will be shown later, the process of perceiving a program is more than just reading. However, since we are talking about text, and the term _readability_ is fairly well-established, I will also use it in that sense.

In order to maintain readability of the code, during program development it is common to agree on some common set of formatting rules (style) for the source code. The very existence of a set of such rules can have a positive effect on its readability and quality, since, firstly, it forms certain habits among programmers regarding the language constructions that they expect to see in the program text, and, secondly, it forces them to be more attentive to what they have written (unless, of course, the formatting of the code is completely transferred to the auto-formatting tools).

However, individual rules are often questionable because the criteria for their selection are unclear and they often contradict similar rules in other similar styles.

The rules provide specific details for how the code is formatted to maintain readability, but there is no explanation of how the rules help achieve it. Without understanding this, the solution to the engineering problem of forming a readable (i.e optimal from the point of view of the ease of perception of the program text) is replaced by thoughtless adherence to formal and often arbitrarily chosen rules, which also change when moving from project to project, from language to language. As a result, a false idea is formed that the what the rules say is not so important, and the choice of one or another style is just a matter of taste and habit.

Indeed, our habits largely determine how comfortable we feel in a given situation and, in particular, how we perceive a particular formatting style. But the feeling of comfort due to habit cannot be a measure of how objectively good this style is. It is obvious that the habit of a certain style can only mean that we simply stopped noticing the specific features of this style, which in fact can be counterproductive in the sense of forming objectively readable code.

When I speak of _objective readability_, I mean that the full readability of a text consists of a subjective component, caused by developed habits and skills, which we talked about above, and an objective one. This second component is determined by the capabilities and limitations of the mechanisms of perception and processing of visual information common to all people in the normal mental and physical state.

Thus, the subjective component is associated with some private habits that can be changed, and the objective – with the general psychophysical features of a human's vision, which we do not assume is possible to change. Therefore, when talking about optimizing the text of a program, it makes sense to talk only about the objective component of readability, and therefore further in this article the term _readability_ will always refer to this component of it.

Let's take a closer look at what we know about the mechanisms of human perception of visual information, reading plain texts, and reading and perceiving program texts.

<anchor>visionspecifics</anchor>
# Features of Human Vision[⁹](#9)
<anchor>fieldofview</anchor>
## Field of View
The human [field of view](https://www.ncbi.nlm.nih.gov/books/NBK220/) is relatively large: 50° superiorly, 60° inferiorly, 90° temporally (towards an ear) and 50° nasally. Situated in the temporal hemifield is the normal blind spot approximately 12 to 17 degrees from fixation and 1.5 degrees below the horizontal meridian. Within this field, visual acuity and color perception are unevenly distributed: visual acuity of the order of 1' is achieved in the area of ​​_fovea_, which forms ~2° of central (_foveal_) vision, but it is not so good in the _parafoveal_ area (which covers 5° in both directions from the fixation point) and even worse at the periphery.[¹](#1)

Likewise, the ability to distinguish colors decreases from the center to the edge, and this change is different for different color components. We can say that moving from the center of the human retina to the periphery, we seem to find ourselves in earlier stages of evolution, moving from the most highly organized structures to the primitive eye, which distinguishes only the simple movement of shadows.

**Figure 1. Field of view of the human right eye. Orange spot - the place of projection of the fundus blind spot.([оригинал](https://ru.wikipedia.org/wiki/%D0%9F%D0%BE%D0%BB%D0%B5_%D0%B7%D1%80%D0%B5%D0%BD%D0%B8%D1%8F#/media/%D0%A4%D0%B0%D0%B9%D0%BB:Goldmann_visual_field_record_sheet.svg))**
<img src="https://habrastorage.org/webt/7v/mo/qz/7vmoqzost4u5nzaeluqofvn3we4.png" style="zoom:20%;" />

<anchor>ambientfocal</anchor>
## Ambient and Focal Vision
In modern neuropsychology, there is a concept of _ambiant_ (from the French **ambiant** ‘surrounding’) and _focal_ visual systems. While the first, evolutionarily more ancient, is responsible for dynamic spatial localization, the second deals with the identification of objects.

**Table. 1. Comparative features of focal and ambient systems**
| Visual System                | Focal     | Ambient                |
| ---------------------------- | --------- | ---------------------- |
| Function                     | What      | Where / How            |
| Engagement in movement       | Less      | More                   |
| Awareness / Memory           | More      | Less or missing        |
| Temporary properties         | Slow      | Fast                   |
| Light Sensitivity            | High      | Low                    |
| Spatial resolution           | High      | Low                    |

The objects that represent the source of the necessary information are far unevenly distributed. They are usually localized in small areas of the visual field. At the same time, with the help of ambient vision, a potentially interesting object or element of the object is detected, and with the help of focal vision aimed at the object, this information is perceived and analyzed in more detail. When faced with a new situation or with a new object, we, as a rule, first look "wide field" and only then concentrate our attention on details.

Examination of the environment and selection of objects for detailed processing is carried out using head and body movements, which are superimposed on a subtle pattern of eye movements. The most famous of their varieties are _saccades_ - extremely fast (~ 500°/sec) jumps of a ballistic type, changing the position of the eyes in orbit and making it possible to highlight fragments of the scene for subsequent _fixation_.

**Figure 2. Reproduction of Ilya Repin's painting and recording of the subject's eye movements.**[¹¹](#11)
<img src="https://habrastorage.org/webt/vi/-m/vw/vi-mvwhun4tp2e2h1sk-cuob0mu.jpeg" />
<!-- <img src="https://habrastorage.org/webt/pi/m-/sa/pim-sa30htkaqalojvuubbjxrbo.jpeg" /> -->
<!-- <img src="Yarbus_ne_zhdaly.jpg" style="zoom:40%;" /> -->
Studies of the relationship between ambient (global) and focal (local) visual processing began in the experiments of David Navon in 1977. He presented subjects with large letters consisting of small letters. Some of these compound stimuli were "homogeneous" – the global form and local elements were the same letter. Others were "heterogeneous" – the global and local letters were different (say, "E" and "S"). The subjects had to identify the global or local letter as quickly as possible.

**Figure 3. Homogeneous and heterogeneous super letters from David Navon's experiments.**
<img src="https://habrastorage.org/webt/yu/ha/z3/yuhaz3cdetforzujr9xsmssmyac.png" />
<!-- <img src="Velichkovskiy_FF.png" style="zoom:40%;" /> -->

It turned out that when set to the global form, it is identified quickly and without any interference with matching or non-matching letters of the local level. When set to identify parts (i.e. small letters), the picture was different. First, the responses were slower. Second, in the case of heterogeneous stimuli, responses were further slowed down and become less accurate. Obviously, _when we set ourselves up for granularity, we cannot always ignore global information_.

Research on the relationship of global and local processing, tested with the Navona super letters, revealed a possible differential role for the posterior left and right hemispheres. In this case, the left hemisphere turned out to be more of a tuning regulator for details, and the right one for global outlines. The influence of emotions turned out to be extremely interesting: negative emotions, in contrast to positive ones, strengthened the attitude towards the perception of details.

<anchor>laws</anchor>
## The Laws of Perceptual Organization
An interesting feature of our vision is the ability to perceive a group of objects as a whole. So in the image below we see a dog, and not just a chaotic set of spots:

**Figure 5. Dalmatian.**
<img src="https://habrastorage.org/webt/nm/8m/-7/nm8m-7vtm8sly2bjy1_vmilhnu0.jpeg" />
<!-- <img src="dog.jpg" style="zoom:70%;" /> -->
And here we clearly see a square and a circle[¹⁰](#10):

**Figure 6. Shapes from points.**
<img src="https://habrastorage.org/webt/lo/sp/oh/lospoh3jfddv6dkgtotqvuepjv0.png" />
<!-- <img src="https://habrastorage.org/webt/na/qt/b5/naqtb5hym6odlk2ytcijlg6lvwe.png" /> -->
<!-- ![](Arnheim_1.png) -->
There are many explanations for these illusions. In terms of cognitive bionomy, the need to see shapes, edges, and movements (as well as faces) was dictated by the need for survival. Thus, even in the absence of real lines or shapes, our sensory-cognitive system used partial information to create these shapes in an attempt to make the seemingly chaotic world intelligible.

Take a look at the picture below and you will see how, over time, the orientation of the triangles changes from one direction to another, a third.

**Figure 7. Triangles changing orientation.[¹⁶](#16)**
<img src="https://habrastorage.org/webt/0a/og/y_/0aogy_yxzq8bnzxf-unhccm8d-w.png" />
<!-- <img src="triangles.png" style="zoom:50%;" /> -->
Thus, we can say that in the absence of a clearly expressed dominant structure, our brain is constantly spending resources in search of such a structure.

Gestalt psychologists were the first to study this phenomenon of perceptual organization. They formulated the basic law of visual perception, according to which _any stimulating model is perceived in such a way that the resulting structure will be, as far as the given conditions allow, the simplest_. Therefore, we perceive the square exactly as it is depicted on the left, and not in some other way: [¹⁰](#10)

**Figure 8. Options for organizing points into a shape.**
<img src="https://habrastorage.org/webt/hm/j3/0m/hmj30mmpg6_b_cu3rk0bgrdds1w.png" />
<!-- ![](Arnheim_2.png) -->

Gestalt psychologists have also formulated 6 principles of perceptual organization. In accordance with these principles, _objects that_

- _located close to each other ("the law of proximity"),_
- _have similar brightness and color characteristics ("similarities"),_
- _restrict a small, closed ("closed")_
- _and a symmetric area ("symmetry"),_
- _naturally continue each other ("good continuation"),_
- _move at approximately the same speed in one direction ("common destiny"),_

_will sooner be perceived as a whole, or a figure, and not as disparate elements of the environment, or background_.

**Figure 9. Examples of similarity in proximity, color and size. [¹⁰](#10)**
<img src="https://habrastorage.org/webt/0-/6r/qu/0-6rqup1wemlrmemrzbf5vpui8u.png" />
<!-- <img src="https://habrastorage.org/webt/8g/b-/ba/8gb-bax1kdttiv1seb_eqaheviq.png" /> -->
<!-- <img src="Arnheim_3.png" style="zoom:45%;" /> -->

In the case of competition of several factors of perceptual organization, the priority is usually given to the factor of _proximity_, and then the factor of _similarity in color, _orientation_ or _size_.

Taking these principles into account turns out to be important in the case of perceptual search or perception, because, _if information is organized in accordance with these principles, the solution of the problem posed requires less effort due to the fact that the perceptual field is subjected to grouping, and a smaller proportion of common resources are successively allocated to the formed groups of elements_. The distribution of resources within each group is approximately equal.

The tasks of visual search are usually complicated by the addition of irrelevant objects (_distractors_). However, _in the case when distractors form visually compact groups, allowing them to be ignored as a whole, their addition, on the contrary, can greatly facilitate the search_.

<anchor>asymmetry</anchor>
## Asymmetry of the Visual Field
Similar to the asymmetry between the left and right hand, there is some asymmetry in how we perceive the left and right visual fields. Perhaps this is also due to asymmetries in the left and right hemispheres of the brain, which are responsible for processing sensory information from the right and left visual fields, respectively.

Thus, in the scenic arts it is known that there is a difference between the left and right halves of the stage: when the curtain rises in the theater, the audience is inclined to look to its left first and to identify with the characters appearing on that side. Therefore, among the so-called stage areas the left side (from the audience's viewpoint) is considered stronger. In a group of actors, the one farthest left dominates the scene. The audience identifies with him and sees the others, from his position, as opponents. Likewise the observer experiences a picture as though he were facing its left side. He subjectively identifies with the left, and whatever appears there assumes greatest importance.[¹⁰](#10) Thus, in addition to the natural balance point in the center of the visual scene, an additional center is formed in its left part.

<anchor>textreading</anchor>
# How We Read Texts[¹](#1)
When we read, our eyes incessantly make rapid mechanical (i.e., not controlled by consciousness) movements, _saccades_. On average, their length is 7-9 letter spaces. At this time we do not receive new information. 

The primary function of a saccade is to bring a new region of text into foveal vision (~2° central field of view) for detailed analysis, because reading on the basis of only parafoveal or peripheral information is difficult to impossible.

Between saccades, our eyes remain relatively motionless for the duration of _fixations_ (about 200 – 300 ms). During this period, we recognize the visible part of the text and plan where to make the next jump.

Whereas a majority of the words in a text are fixated during reading, many words are skipped so that foveal processing of each word is not necessary.

**Figure 10. Typical pattern of eye movements while reading.[⁹](#9)**
<img src="https://habrastorage.org/webt/kv/rt/ln/kvrtlnwdjge8ouyfzzaulxu7dyo.jpeg" />
<!-- <img src="https://habrastorage.org/webt/hj/ig/oq/hjigoqx9elszvy_18dgb7xtuuio.png" /> -->
<!-- <img src="Velichkovskiy_reading.png" style="zoom:40%;" /> -->

Letter spaces are the appropriate metric to use, because the number of letters traversed by saccades is relatively invariant when the same text is read at different distances, even though the letter spaces subtend different visual angles.[¹⁵](#15)

About 10-15% of the time, readers move their gaze back in the text (_regressions_) in order to re-read what has already been read. As the difficulty of the text increases, the duration of fixations and the frequency of regressions increase, and the length of saccades decreases.

During fixation, we get information from the _perceptual span_. The size of this area is relatively small, in the case of alphabetic orthographies (for example, in European languages) it starts from the beginning of the fixed word, but no more than 3-4 letter spaces to the left of the fixation point, and extends to about 14-15 letter spaces to the right of this point (in total 17-19 spaces).

The _identification span_, that is, the scope required to identify a fixed word, is less than the perceptual span and, as a rule, does not exceed 7-8 letter spaces to the right of the fixation (in total, about 10-12 spaces).

The availability of the first three letters of a word during the previous commit leads to a decrease in the fixation time on that word. Some researches have also shown that the letter information to the right of the fixation can be used to determine whether the next word should be skipped.

Most of the research suggests that boundary information (conveyed by the spaces between words) is the major determinant used in deciding where to move to next; saccade length is influenced by both the length of the fixated word and the word
to the right of fixation.

Most readers are slowed down (on average by about 30%) by the absence of space information, and experiments demonstrated that _both word identification processes and eye guidance are disrupted by the lack of space information_.

It was found that, when space information is provided for readers of Thai (who are not used to reading with spaces between words), they read more effectively than normal. There are reports that the reading of long German compound words is
facilitated by the insertion of spaces, even though this format is ungrammatical and never encountered in normal reading.

_Thus, it appears safe to conclude that space information is used for guiding the eyes in reading._

Word-length information may be used not only in determining where to fixate next but also in how parafoveal information is used. That is, enough parafoveal letter information may be extracted from short words so that they can be identified and skipped, whereas partial-word information (the first 3 letters) extracted from longer parafoveal words may rarely allow full identification of them but facilitate subsequent foveal processing.

Word-length information also plays a clear role in where in the word a reader fixates. Although there is variability in where the eyes land on a word, _readers tend to make their first fixation on a word about halfway between the
beginning and the middle of a word_.

Word-length information helps to guide the eye toward *the preferred viewing location*, i.e a location between the beginning and the middle of the word. When the space indicating the location of word n+1 was visible in the parafovea,
the first fixation on that word was closer to the preferred viewing location than when the parafoveal preview did not contain that space information.

Although the average landing position in a word lies between the beginning and middle of a word, this position varies as a function of the distance from the prior launch site. For example, if the distance to a target word is large (8-10
letter spaces), the landing position is shifted to the left. Likewise, if the distance is small (2-3 letter spaces), the landing position is shifted to the right.

The location of the first fixation is between the beginning and the middle of a word for words that are 4-10 letters long (either for the first fixation in a word or when only a single fixation is made). However, with longer words, the
effect breaks down, and readers tend to fixate near the beginning of the word and then make a second fixation toward the end of the word.

Informational density (or morphological structure) of the word influences how long the fixations are on each half of the word. For example, it was found that if the word was predictable from the first 6 letters (the words were typically
about 12 letters), readers generally made a fixation in the first half of the word and then moved their eyes to the next word; if they made a second fixation on the word it tended to be quite short. However, if the word could only be
identified by knowing what the ending was, readers typically made a short fixation at the beginning followed by a longer fixation on the end of the word.

**Table 1. Approximate Mean Fixation Duration and Saccade Length in Reading, Visual Search, Scene Perception, Music Reading, and Typing**

| Task             | Mean fixation duration (ms) | Mean saccade size (degrees) |
|------------------|-----------------------------|-----------------------------|
| Silent reading   | 225                         | 2 (about 8 letters)         |
| Oral reading     | 275                         | 1.5 (about 6 letters)       |
| Visual search    | 275                         | 3                           |
| Scene perception | 330                         | 4                           |
| Music reading    | 375                         | 1                           |
| Typing           | 400                         | 1 (about 4 letters)         |

With respect to visual search task it was found that when the target was at a small _eccentricity_, it was located accurately with a single saccade; when the target was more peripheral, wrongly directed initial saccades were common (up to
40% of the time). Also _in a complex search tasks the eyes is initially directed to the center of the global display and then to the centers of recursively smaller groups of objects until the target was acquired_.

<anchor>comprehension</anchor>
# Program Comprehension

Programs differ from natural language texts in two important ways. First, programs are lexically and syntactically different from natural language texts. Lexically, they are composed from a limited vocabulary, with some words used as programming language keywords, and others used more freely as identifiers to name program constructs like variables and methods. Syntactically, programs are laid out and organized differently than natural language texts. They feature greater use of formally defined structures and multiple forms of indented layout (both horizontal and vertical). The second difference is semantic. Natural language text is typically understood in two concurrent phases: text (how it is written down) and domain (what it means). Source code comprehension however needs a third dimension of comprehension: execution. Thus, in order to understand a program’s goals, programmers must be able to trace source code execution to discover its operational semantics.[²](#2)

<!-- Programs are different from regular text. Programs are composed of a limited set of words and are organized differently from ordinary texts: they make extensive use of formally defined structures, denoted in the text using special syntactic constructions. There is also a semantic difference. The perception of an ordinary text, in general, consists of two parallel phases: the perception of the text itself and the comprehension of what it is about. When it comes to the text of a program, comprehension means understanding the syntactic and semantic structures of the program, but it also includes understanding the operational semantics of the program, that is, how the state of the program changes during its execution. [²](#2) -->

<anchor>cognitivemodels</anchor>
## Cognitive Models of Program Comprehension[³](#3)
<anchor>concepts</anchor>
### Concepts and terminology
A _mental model_ describes a developer's mental representation of the program to be understood whereas a _cognitive model_ describes the cognitive processes and temporary information structures in the programmer’s head that are used to form the mental model. 

_Programming plans_ are generic fragments of code that represent typical scenarios in programming. For example, a sorting program will contain a loop which compares two numbers in each iteration.

_Delocalized plans_ – programming plans realized by lines scattered in different parts of the program. Delocalized plans complicate program comprehension.[¹⁹](#19)

_Beacons_ are recognizable, familiar features in the code that act as cues to the presence of certain structures.

_Rules of programming_ discourse capture the conventions of programming, such as coding standards and algorithm implementations. 
<!-- These rules form certain expectations in the mind of the programmer. -->

<anchor>topdownmodel</anchor>
### Top-Down Model
In this model, it is assumed that programmers understand a completed program in a top-down manner where the comprehension process is one of reconstructing knowledge about the domain of the program and mapping this knowledge to the source code. The process starts with a hypothesis about the general nature of the program. This initial hypothesis is then refined in a hierarchical fashion by forming subsidiary hypotheses. Subsidiary hypotheses are refined and evaluated in a depth-first manner. The verification (or rejection) of hypotheses depends heavily on the absence or presence of beacons.

Top-down understanding is used when the code or type of code is familiar; expert programmers use beacons, programming plans and rules of programming discourse to decompose goals and plans into lower-level plans.


<anchor>downtopmodel</anchor>
### Bottom-Up Model
The bottom-up theory of program comprehension assumes that programmers first read code statements and then mentally chunk or group these statements into higher level abstractions. These abstractions (chunks) are aggregated further until a high-level understanding of the program is attained.

<anchor>strategies</anchor>
### Opportunistic and Systematic Strategies
With these strategies, programmers either systematically read the code in detail, tracing through the control-flow and data-flow abstractions in the program to gain a global understanding of the program, or that they take an as-needed approach, focusing only on the code relating to a particular task at hand.

Subjects using a systematic strategy acquire both static knowledge (information about the structure of the program) and causal knowledge (interactions between components in the program when it is executed). This enabled them to form a mental model of the program. Those using the as-needed approach only acquire static knowledge resulting in a weaker mental model of how the program worked. More errors occurre since the programmers fail to recognize causal interactions between components in the program.

<anchor>codereading</anchor>
## Specifics of Reading Program Texts
A comparative study of the linearity of program reading by novices and experts[²](#2) showed that novices read source code less linearly than natural language text. Moreover, experts read code less linearly than novices. These findings indicate that there are specific differences between reading natural language and source code, and suggest that non-linear reading skills increase with expertise.

Below, as an example, giving an idea of ​​the difference in reading ordinary texts and program texts, the results of one experiment [¹²](#12) on the analysis of the eye movements of programmers while reading the code are presented.

**Figure 11. Traces of eye movement of two expert programmers when reading the same code.**
<img src="https://habrastorage.org/webt/pq/1j/wr/pq1jwrfci64zg0fwszuwcu3o2f8.jpeg" />
<!-- <img src="code_reading_patterns.png" width="90%"/> -->

These programmers were given different tasks: the first (left figure) was expected to receive an answer, what is the return value of `rect2.area()`, the second was told that he would be given a multiple choice question about the algorithmic idea.

As we can see, these scanpaths are quite different, however, the differences in gaze appear deeper than this difference in what they were expecting to be asked, and suggest that different readers read code in markedly different manners.

According to the authors, reader 1’s gaze might politely be described as erratic. Considered in real time, it flashes wildly about the code, generally spending very little time on any one point. Viewed over time, there is a clear pattern of returning to certain focal points, points that are pertinent to the question that the reader was told to expect; but the gaze fixations are so brief as to leave the analyst wondering whether it is possible to gain any comprehension of the code.

Reader 2, by contrast, appears to read the code slowly and methodically. There are elements of linear scanning, and gaze remains far longer on areas of interest.

Indeed, in the illustration on the left, we observe a rather chaotic trajectory with a large number of long vertical saccades, and on the right, there are mainly horizontal saccades, most of which can be associated with reading the text in a general sense.

We can say that in the first case, we see the dominance of fast ambient vision, characterized by long saccades and short fixations, and in the second, slow focal vision.

The authors describe the following basic types of eye movements that make up more complex code reading strategies:

**Table 3. Basic types of eye movements when reading code.**
| Type                  | Description                                                                                                                                                             |
|-----------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Flicking              | The gaze moves back and forth between two related items, such as the formal and actual parameter lists of a method call.                                                |
| JumpControl           | Subject jumps to the next line according to execution order.                                                                                                            |
| JustPassingThrough    | Fixations are on a blank spot and clearly just stop on the way to some place else.                                                                                      |
| LinearHorizontal      | Subject reads a whole line either from from left to right or right to left, all elements in rather equally dis- tributed time.                                          |
| LinearVertical        | Subject follows text line by line, for at least three lines, no matter of program flow, no distinction between signature and body.                                      |
| RetraceDeclaration    | Often-recurring jumps between places where variable is used and where it had been declared. Form of Flicking.                                                           |
| RetraceReference      | Often-recurring jumps between places where variable is used and where it had been recently referred to. Form of Flicking.                                               |
| Scan                  | Subject first reads all lines of the code from top to bottom briefly. A preliminary reading of the whole program, which occurs during the first 30% of the review time. |
| Signatures            | Subject looks at all signatures first, before looking into method/constructor body.                                                                                     |
| Thrashing             | The gaze moves rapidly and wildly in a sequence that appears to make no particular sense.                                                                               |
| Word(Pattern)Matching | Simple visual pattern matching.                                                                                                                                         |

<anchor>identifiers</anchor>
## Role of Identifiers[⁴](#4)
The comprehension of identifier names in programs is at the core of program understanding. Identifier names are often key beacons to program plans that support higher-level mental models of understanding. Identifiers make up approximately 70% of source code. If a certain identifier naming style significantly increases the speed of code comprehension, this could significantly impact overall program understanding.

In general, using actual words rather than abbreviations lead to better comprehension. Identifiers that violate certain guidelines have lower code quality (more bug patterns) than ones that don’t. Longer names reduce correctness and take longer to recall.

In an eye-tracking study analyzing the effect of identifier style (camel-case and underscore) on accuracy, time, and visual effort, with respect to the task of recognizing a correct identifier in a phrase, it was shown that, although, no difference was found between identifier styles with respect to accuracy, results indicate a significant improvement in time and lower visual effort with the underscore style.

**Figure 12. Part of two gaze plots for the correct underscore (left) and camel-cased (right) versions of the 3-word code identifier[⁴](#4)**
<img src="https://habrastorage.org/webt/2-/dz/vt/2-dzvtxei7st1fmgikp2ww6p4zw.jpeg" />
<!-- <img src="underscore_vs_camelcase.png" /> -->

<anchor>analysis</anchor>
# Basic Principles of Formatting
<anchor>visualrepresentaion</anchor>
## Building the Visual Structure
A priori, we know that a program has a certain logical and syntactic structure, and we expect that the structure of its visual representation will reflect them accordingly.

As mentioned above, our brain is in constant search for some optimal interpretation of the visual scene, which allows us to explain it in the simplest way. Therefore, it can be argued that the more clearly the visual structure is formed, and the more accurately it reflects the structure of the program, the less mental effort we will spend on the perception of this program.

When we visually perceive the program, we get the first impression and evaluate the visual structure of the text mainly due to ambient vision, which has low acuity and color perception, deteriorating from the center to the edge. Fast ambient vision helps us to highlight points of interest in the code for further analysis and reading using slow focal vision. This means that, speaking about the general visual structure of the program text, we must move from the area of ​​focal vision, that is, from the world of letters and symbols, to the area of ​​ambient vision, that is, to the area of ​​spots and the relationships between them.

The sequence of the main structural elements in the program is located in the vertical direction, so when evaluating the overall visual structure of the program, when covering it with a "wide view", vertical eye movement prevails, accompanied by small horizontal deviations. The horizontal movement of the gaze associated with the transition to focal vision and reading directly allows us to get the details of these elements.

Therefore, for a correct assessment of the overall structure of the program, it is important to form the correct visual structure in the vertical direction.

For example, in a function definition, we need to visually separate the function declaration, including its name, return type, parameter list, and function body. Inside the body of the function, it is necessary to separate the initialization code of the initial variables, the body of the main algorithm, the formation and return of the result. In turn, within the initialization code, we need to separate the scope of types, variable names, and values ​​assigned to them.

Consider the following example:
<img src="https://habrastorage.org/webt/no/u0/py/nou0pysvtpvd-grwc_-lqwsi2sw.png" />
<!-- ![](cpp1.png) -->
To evaluate the visual structure, let's make a test similar to the _squint test_ used by the UI designers. Alan Cooper describes this test in his book [¹⁸](#18):
> Close one eye and squint at the screen with the other eye to see which elements pop out, which are fuzzy, and which seem to be grouped. Changing your perspective can often uncover previously undetected issues in layout and composition.

In fact, by squinting, we are trying to gauge how we perceive an image through ambient vision. Instead of squinting, you can try to look at the code with a defocused gaze and somewhat away from the fragment of interest to us.

<img src="https://habrastorage.org/webt/_b/ea/aw/_beaawvv84diaqqhnwkptkhjz3g.jpeg" />
<!-- <img src="https://habrastorage.org/webt/-f/df/s0/-fdfs0pog9fw88ozqxrwjlaff-e.png" /> -->
<!-- ![](cpp1_blur.png) -->
The visual structure of this code contains only three large areas, which obviously does not reflect the program structure correctly.

Stacking vertically allows you to correct this disadvantage:
<img src="https://habrastorage.org/webt/iq/i3/7i/iqi37ixwxuqufyuwxusni3cnneu.png" />
<!-- ![](cpp2.png) -->

The test result:
<img src="https://habrastorage.org/webt/6e/fc/py/6efcpyacl2rcz7idcoiw29thjkk.jpeg" />
<!-- <img src="https://habrastorage.org/webt/ww/62/q5/ww62q51wuyielnb5m8zswzonkfg.png" /> -->
<!-- ![](cpp2_blur.png) -->

Regions of text can be composed of lines of the same structure. In this case, it makes sense to group the same line elements, thereby emphasizing their horizontal structure. This grouping will highlight the common and make the differences more visible.

This way we structure the text both vertically and horizontally. In the first case, we perform these splits by adding blank lines. In the second, we use indentation and alignment.

Indents are used to form a visual representation of the hierarchy in the program structure between elements on different lines.

Let's take a look at the following code:
<img src="https://habrastorage.org/webt/xb/jn/kr/xbjnkre3zaikfrmbo1pctjlcnha.png" />
<!-- <img src="indent1.png"/> -->
The list of arguments is presented as a column and is indented relative to the first line. Is this indentation sufficient to correctly display the logical structure of the program? Obviously not.

Правило сходства по близости связывает список аргументов с именем переменной сильнее, чем с именем функции даже несмотря на то, что благодаря подсветке синтаксиса формируется сходство по цвету. Но что случится, если алгоритм подсветки изменится? Вот как выглядел этот же код на gitlab:

The proximity rule binds the argument list to the variable name more strongly than to the function name, even though syntax highlighting creates a color similarity. But what happens if the highlighting algorithm changes? Here's what the same code looked like on gitlab:
<img src="https://habrastorage.org/webt/qh/gv/7i/qhgv7iklbl_6jujgqm6kluodd60.png" />
<!-- <img src="indent3.png"/> -->
В данном случае подсветка синтаксиса еще больше ухудшила ситуацию, поскольку правило сходства по цвету теперь также усиливает связь аргументов с именем переменной. Имя функции здесь как бы и ни при чем.
In this case, syntax highlighting made things worse, since the color similarity rule now also strengthens the association of arguments with a variable name. The function name has nothing to do with it.

When scanning text, such an arrangement provokes eye movement from the resulting variable `success` directly to the column of the list of arguments and only then regression to the function name.

_Подсветка синтаксиса может значительно облегчить восприятие программы. Однако, как мы видим из этого примера, в случае некорректной визуальной структуры, эффект от нее может быть совершенно противоположный. Учитывая также, что поскольку программист не контролирует подсветку синтаксиса, нельзя принимать ее во внимание при оценке того, насколько  удобочитаемым и правильно отражающим структуру программы является её конкретное визуальное представление._

_Syntax highlighting can greatly facilitate the perception of the program. However, as we can see from this example, in the case of an incorrect visual structure, the effect of it can be completely opposite. Considering also that since the programmer has no control over the syntax highlighting, it should not be taken into account when assessing whether its particular visual representation is readable and correctly reflects the structure of the program._

To correctly display the logical structure of this code in its visual representation, it is necessary that the list of arguments is indented not relative to the beginning of the line, but relative to the beginning of the function name:
<img src="https://habrastorage.org/webt/nz/z9/6l/nzz96lrnme0kfnbe0fhcm8ktgvq.png" />
<!-- <img src="indent4.png"/> -->
In this case, it is clearly seen that the function argument list is syntactically part of the function call expression and is logically a set of parameters that is converted by the function call.

Let's do a squinting test. We will also disable syntax highlighting in order to exclude grouping by the color similarity factor, which we do not control:

<img src="https://habrastorage.org/webt/pd/hd/_n/pdhd_n8bny50x3nqwltsihpvwik.jpeg" />
<!-- <img src="https://habrastorage.org/webt/iq/l1/y1/iql1y1gu6ae2c5iyv2tzt7lvsgw.png" /> -->
<!-- <img src="indent4_blurred.png"/> -->
You can see that the function name and argument list form a tightly coupled scope. However, when using two spaces for indentation, the subordination of the argument list relative to the function name does not seem expressive enough. Using four spaces helps to solve this problem:

<img src="https://habrastorage.org/webt/ot/kj/u_/otkju_th20tr1orgivwrq8ju0qo.jpeg" />
<!-- <img src="https://habrastorage.org/webt/co/s2/nh/cos2nhaky7fucnp08oh0grhckia.png" /> -->
<!-- <img src="indent4-4_blurred.png"/> -->
One more problem remains: the list of arguments looks like one unstructured spot, despite the fact that there are two types of elements, the labels of the arguments and their values. This list has a certain structure, and in order to visually highlight this structure, we should use alignment. But before that, let's digress from this example for a while and look at various ways to format an argument list as label:value.

**One line formatting**
<img src="https://habrastorage.org/webt/xm/os/gy/xmosgy6pigronododp5uljr5oly.png" />
<!-- <img src="code1.png"/> -->
In this version, the visual structure does not reflect the structure of the expression, so the search is difficult. This option is only intended to be read carefully.

Due to the large length of the line, the rest of it, which did not fit on the first line, was placed on the second. In this case, the editor made an indent showing the subordination of this line relative to the previous one. However, elsewhere (for example, in the comparison pane on gitlab), the second line will most likely start at the beginning and the visual structure of the piece of code will be broken.

**Label:value pairs alignment**
<img src="https://habrastorage.org/webt/ir/gp/zb/irgpzbpqbvhdnsuzbrdnxhemwum.png" />
<!-- <img src="code2.png"/> -->
This option looks better in the sense that the method name and the argument list are clearly separated from each other. It is already a little easier to search in the argument list, since each pair is on a separate line and the labels are left aligned.

Тем не менее различение меток и значений затруднено из-за того, что отсутствует явная граница между областями меток и значений для всего списка. Кроме того,  поскольку текст воспринимается как зафиксированный слева и свободный справа, в визуальном представлении этот код выглядит как большой груз (область списка аргументов), подвешенный на тонком и длинном рычаге. Основная масса кода оказывается сильно смещена в правую, менее важную часть пространства.

However, distinguishing between labels and values ​​is difficult because there is no clear border between label and value areas for the entire list. In addition, since the text is perceived as anchored on the left and free on the right, visually this code appears as a large mass (the area of ​​the argument list) suspended from a thin and long arm. The bulk of the code turns out to be strongly shifted to the right, less important part of the space.

In this case, we can try to fix the last flaw by shifting the list of arguments to the left:
<img src="https://habrastorage.org/webt/gd/3y/us/gd3yuslr14dsmyu4f1m4tsy4ssk.png" />
<!-- <img src="code3.png"/> -->
To better represent the internal structure of the argument list, it is necessary to form an explicit dividing line between the label and value areas. We have two options here.

**Option 1: labels and arguments are left aligned separately**
<img src="https://habrastorage.org/webt/qc/n8/zh/qcn8zhxgazbgclie1_0o0c3iv0c.png" />
<!-- <img src="code4.png"/> -->
Of all the options proposed so far, this one most accurately conveys the logical structure of the code. It clearly distinguishes the areas of labels and values, identifying and finding individual elements within them is much easier. However, due to the large difference in the label lengths of the arguments, the bonds within pairs with short labels are weakened.

**Option 2: inner alignment**
<img src="https://habrastorage.org/webt/oe/zj/lj/oezjljl0bqs6rc0gryw6822r4e8.png" />
<!-- <img src="code5.png"/> -->
Having all the advantages of the previous option, this option looks more natural for this example. In it, the argument labels look like a natural continuation of the method name, as it should be in this case. The code looks compact enough without the need to carry the argument list to the next line.

For the `reticulateSplines` example, the code in this styling would look like this:
<img src="https://habrastorage.org/webt/q-/ff/ay/q-ffaynkxm45pkwpe4ivn0ef0b0.png" />
<!-- <img src="indent5.png"/> -->
Alignment is a powerful tool for optimizing the visual presentation of code by forming compact groups in a horizontal direction.

Let's analyze the following code:
<img src="https://habrastorage.org/webt/fs/mg/k0/fsmgk0pgwcqzom18vxjrcqkgnpq.png" />
<!-- <img src="proto1.png" /> -->

Basically, every single protocol declaration looks reasonably readable. However, this cannot be said about the entire text: the general structure is inexpressive, there are no clearly expressed masses in it that could attract the eye. As a result, "dazzles in the eyes", and there is a feeling of tiring monotony. To perceive this code, it can only be read sequentially and completely: "protocol DataBaseDependent ServiceDependent var dataBase DataBase get set, protocol LocalConfigDependent …"

Let's reformat it so that each declaration spans one line and align it:

<img src="https://habrastorage.org/webt/pt/uu/bv/ptuubvmnslk97jdapakeffafaa0.png" />
<!-- <img src="proto3.png" /> -->

Now clearly defined groups (grouping by "proximity") have been formed in the horizontal direction, and the structure of each definition and the differences between them have been clearly revealed. The way this code is read is also different. After a quick initial familiarization with the structure and identifying common parts, further analysis simply ignores these common parts, and attention is focused only on the different parts. Thus, due to the more active use of ambient vision, the load on the focal vision is reduced and the amount of mental effort required to understand the program is reduced.

As mentioned above, there is some asymmetry in how we perceive the left and right sides of the visual scene. This asymmetry is even more natural in the perception of the program text: the text is rigidly anchored to the left margin, where indents set the level of hierarchy in the logical structure of the program. The right edge is free and does not have a hard limit. Reading from left to right and top to bottom determines what _something new we expect to see top and left or center_.

Because of that, the following constructions cannot be called good in terms of readability:

<img src="https://habrastorage.org/webt/82/x3/y1/82x3y16luvwnxvimg_cdysisniq.png" />
<!-- <img src="closure1.png"/> -->

In such constructs, the new namespace begins at the end of the line, that is, in the least important area, where this beginning is not expected. It's more natural to place the beginning of this block of code where it should be - at the top left:
<img src="https://habrastorage.org/webt/d3/2s/en/d32senybyy8wrbv9di8l_xy_tta.png" />
<!-- <img src="closure2.png"/> -->

<anchor>linelength</anchor>
## Line Length
Отсутствие жесткого ограничения справа, не означает, что ограничения нет вообще. Многовековой опыт книгопечатания,[¹³](#13) и десятилетия опыта, наработанного [веб-дизайнерами](http://webtypography.net/2.1.2), сходятся к тому, что оптимальная длина строки, обеспечивающей комфортное чтение составляет приблизительно 45-75 символов.

Несмотря на структурные отличия текстов программ, трудно вообразить, что эти отличия настолько сильны, что могут сделать длинные строки, трудные для чтения обычных текстов, легкими в случае, когда мы читаем программу. Наоборот, можно ожидать, что программы, подобно научным изданиям, требуют более коротких строк, чем проза.[¹⁷](#17)

Видимо, повторяя Стива Макконнелла[¹⁴](#14), некоторые разработчики говорят, что на их больших мониторах длинные строки прекрасно помещаются, и их присутствие в коде нормально. Этот аргумент «больших мониторов» не выдерживает никакой критики:

- Длинную строку трудно охватить «широким взглядом».
- Оценка структуры выражения затруднена из-за того, что эта структура размазана по строке и не формирует явно выраженных компактных визуальных областей.
- Из-за неявно выраженной структуры и увеличения эксцентриситета затрудняется поиск.
- Из-за увеличения расстояния затрудняются переходы от конца строки к началу следующей, и соответственно замедляется чтение.
- Если отодвинуться от монитора с целью уменьшения углового размера строки, чтобы нивелировать некоторые указанные выше проблемы, это затруднит чтение за счет уменьшения размера букв и расстояний между ними. При этом это размер области идентификации и распознавания и размер саккад, измеряемых в буквах, не изменится. То есть, чтение замедлится.
- Большие мониторы не всегда доступны или текст программы может отображаться в области с гораздо меньшей шириной. При этом строка или не помещается в области видимости и требует прокрутки, либо строка разбивается на несколько строк и это, как правило, разрушает структуру программы во всей области видимости.

По ряду объективных причин не всегда можно избежать длинных строк (например, из-за использования длинных идентификаторов, которые мы не в силах изменить). Также в случаях, когда нас не интересует структура выражения (например, при выводе отладочного сообщения в лог программы), использование длинной строки может оказаться даже предпочтительнее структурирования длинного выражения разбивкой его на несколько строк, так как делает этот код менее значимым с точки зрения амбьентного зрения.

В общем же случае, длинные строки, как и длинные идентификаторы, это признак плохой читаемости кода.

<anchor>names</anchor>
## Names
Имена играют важнейшую роль для обеспечения удобочитаемости программного кода. Они занимают его большую часть и часто играют роль маяков, позволяющих идентифицировать характерные структурные части программы. Основные требования к именам – это их краткость и выразительность. _Чем имя длиннее, тем оно труднее для чтения, запоминания и поиска._ Длинные имена, как правило приводят к длинным строкам, что тоже затрудняет чтение и поиск. Требование выразительности означает, что в области контекста использования, имя должно позволять однозначно определять роль обозначаемого им элемента программы.

Требования краткости и выразительности могут очевидным образом конфликтовать друг с другом, поскольку выразительность может требовать использования более длинных, составных имен. Поэтому имеет смысл сделать оценку допустимой рекомендованной длины имени.

В идеале, мы хотим распознавать имя с первого взгляда (при первой фиксации). Это значит, что первую оценку оптимальной длины можно заложить как размер области идентификации, то есть 10-12 символов. Особенностью текстов программ, как уже писалось выше является то, что набор допустимых слов в них ограничен, поэтому велика вероятность, что даже в случае более длинного имени мы сможем впоследствии распознавать его по первой части, так что в принципе, даже при длине больше 12 символов нам потребуется лишь одна фиксация. Мы, однако хотим, чтобы при этом это имя помещалось в размер области распознавания (17-19 символов) и оставался некоторый запас, таким образом, чтобы наш мозг имел возможность оптимально спланировать следующую саккаду. Если мы возьмем 4 символа от конца области распознавания, то получим оценку в 13-15 символов.

Допуская в редких случаях две фиксации с «угадыванием» мы получим оценку в 20-24 символа (13-15 из предыдущей оценки + 7-9 на саккаду внутри слова) .

Взяв середины отрезков полученных оценок получим следующую таблицу:

**Таблица 4. Оценки максимальной длины имени.**

| Число фиксаций              | Оценка максимальной длины имени |
| --------------------------- | ------------------------------- |
| 1                           | 11                              |
| 1 (с угадыванием окончания) | 14                              |
| 2                           | 22                              |

Эти рекомендации достаточно хорошо согласуются с теми границами, которые приводит в своей книге[¹⁴](#14) Стив Макконнелл: 10-16 и 8-20. Теперь мы можем как-то объяснить их.

На практике иногда приходится использовать имена, длины которых выходят за предлагаемые пределы. Например, когда имя включают в себя некоторое стандартное именование группы, к которой относится данный элемент, как в `PreferencesViewController`. Располагая значимую, уникальную часть имени в начале, мы можем ожидать, что распознаем уникальную часть имени уже при первой фиксации, и в то же время нам не потребуется больших усилий для распознания «стандартного дополнения».

За редким исключением не имеет смысла использовать в именах какие-либо префиксы, описывающие некоторые общие характеристики (например тип) или для различения классов,  являющихся частью вашего приложения. Префиксы маскируют смысловую часть имени, в их присутствии позиция первой фиксации на слове при чтении сдвигается  влево от оптимальной, они требуют определенных усилий на дополнительный анализ слова. В некоторых случаях они могут изменять значение имени (`kBytesPerSec` это «килобайты в секунду» или константа `BytesPerSec`?).

Декорирование имен классов и функций имеет смысл лишь в случае разработки библиотеки на языке, в котором отсутствует понятие пространства имен, позволяющих ограничить их видимость. Все сущности, определяемые внутри вашего приложения, находятся на верхнем уровне пространства имен и, как правило, не нуждаются в каких-либо префиксах  для предотвращения конфликтов имен.

<anchor>spaces</anchor>
## Spaces
Как указывалось выше, использование любого другого разделителя между словами вместо пробела требует больших усилий при чтении, поскольку приводит к затруднениям при определении границ слова, что в свою очередь затрудняет его распознавание и планирование следующей саккады.

Поэтому рекомендуется разделять идентификаторы в программе с помощью пробелов, даже в случае, если формально такого разделения не требуется. Например, имеет смысл разделять пробелом имя функции и список ее параметров/аргументов:

<img src="https://habrastorage.org/webt/1r/iy/il/1riyil0pm_dpnf0h9jkivaqvspi.png" />
<!-- ![](space1.png) -->

В первой строке пробел отсутствует, и имя функции сливается с первым аргументом в  списке аргументов. Кроме затруднения в чтении, мы можем также заметить, что визуальная структура не совсем корректно отражает логическую структуру программы: выражение вызова функции включает в себя имя функции и список аргументов, список аргументов включает в себя первый и второй аргументы. В первой же строке имя функции сильнее связано с первым аргументом, чем аргументы между собой.

Еще один пример:

<img src="https://habrastorage.org/webt/rs/dz/ku/rsdzkuirda_pdn22_ocsalvl8rg.png" />
<!-- ![](space3_1.png) -->

После разделения на две группы, добавления пробелов и выравнивания:

<img src="https://habrastorage.org/webt/xv/ff/yr/xvffyrmbd0n3tn-ay-xxkdhsjqc.png" />
<!-- ![](space3_2.png) -->

В данном случае добавление пробелов не только облегчило чтение отдельных строк за счет явного разделения идентификаторов внутри их, но и (вместе с выравниванием) облегчило их сравнение, посредством образования компактных визуальных групп по вертикали. Для того, чтобы понять, что делает этот код, уже не надо читать отдельно каждую строку.

В случае, когда совокупная длина идентификаторов не превышает размера области распознавания, это требование не является настолько критическим, поскольку все выражение может быть сразу охвачено одним взглядом:

<img src="https://habrastorage.org/webt/qx/o-/al/qxo-alqr0-hwf2ycvpjo_wwh2dq.png" />
<!-- ![](space2.png) -->

<anchor>braces</anchor>
## Arranging Curly Braces
На сегодняшний день в языках с Си-подобным синтаксисом доминируют два основных способа расстановки фигурных скобок: в первом открывающая скобка находится на отдельной строке с тем же отступом, как и начало связанного с ней предшествующего синтаксического элемента, а во втором открывающая скобка располагается в конце строки, содержащей окончание такого элемента.

Далее я буду условно называть эти стили _Allman_ и _One Truce Brace Style_ (_1TBS_) по названиям наиболее популярных стилей, которые используют соответствующие правила расстановки скобок. 

Расположение открывающей скобки в начале отдельной строки в стиле _Allman_ обладает следующими преимуществами:

- Скобка всегда располагается в левой части визуальной области, т. е. в области наибольшего внимания, и при сканировании кода (в котором преобладают вертикальные движения глаз) всегда так или иначе попадают в область фокального зрения. В стиле _1TBS_ открывающие скобки часто оказываются в правой области кода и попадают лишь в область периферийного зрения, что значительно затрудняет их обнаружение. Другими словами, то что в первом случае происходит естественно и как бы само по себе, в втором требует дополнительных и специальных усилий.
- Облегчено сопоставление открывающей и закрывающей скобок и, соответственно, определение границ обрамленного ими блока кода. Поиск парной скобки требует лишь вертикального перемещения взгляда, на пути его следования от одной скобки к другой нет никакого текста, и поиск происходит в известном направлении до первого символа. 
   Действительно, всегда, когда мы видим в тексте закрывающую скобку на отдельной строке, мы знаем, что парная ей открывающая находится *выше*, а это значит, что основным и естественным направлением поиска её будет поиск вверх. 
   При использовании _1TBS_, поиск в общем случае требует больших усилий за счет того, что он осуществляется в широком секторе, причем взгляд проходит через текст, который надо анализировать, и который часто содержит вложенные пары фигурных скобок, визуально конкурирующие с целевой.
- Скобка расположена в начале строки, именно там, где мы ожидаем увидеть начало чего бы то ни было, и естественным образом обозначает начало блока кода. В стиле _1TBS_ из-за своего положения в конце строки открывающая скобка часто перестает играть роль явного визуального маркера начала блока. Более того, в некоторых случаях (напр. повторяющиеся конструкции `} else if`) закрывающая скобка предыдущего блока оказывается в начале строки, содержащей конструкцию, предваряющую новый блок, и тем самым визуально связывается с началом этого блока. Таким образом, её формальное и визуальное значения перестают соответствовать друг другу.
- Расположение скобок на отдельных строках, естественным образом добавляет вертикальные пробелы между синтаксической конструкцией перед открывающей скобкой и обрамленным блоком кода, что, в большинстве случаев, лучше отражает структуру всей конструкции.
- Горизонтальная позиция открывающей скобки в стиле _Allman_ однозначно определяет уровень вложенности, к которому она относится. В _1TBS_ эта информация отсутствует, так как расположение открывающей скобки в основном определяется лишь длиной строки, расположенной перед ней.

Недостатки _1TBS_ приводит к тому, что открывающая фигурная скобка перестает в полной мере участвовать в формировании визуального представления кода, стиль провоцирует программиста не разделять блоки кода внутри скобок и окружающие их элементы пустыми строками, и в результате текст программы часто выглядит как один плохо структурированный массивный кусок:

![picture36](https://habrastorage.org/webt/y1/kr/zq/y1krzqjyfs1tpw0wshh7kznwioy.png)

Код в примере выше демонстрирует основные упомянутые проблемы стиля _1TBS_, а именно: отсутствие явно выраженной визуальной структуры, инвертирование роли закрывающих скобок, потеря визуальной значимости открывающих, которые при беглом взгляде на код лишь приблизительно угадываются с помощью периферийного зрения и их точная локация требует дополнительных горизонтальных движений глаз. При этом этот код еще можно назвать простым с той точки зрения, что в нем условия выражения `if` занимают всего одну строку, и открывающие скобки находятся на той же строке, что и `if`, а блоки кода внутри скобок имеют достаточно простую линейную структуру и не содержат вложенных блоков.

Переформатирование этого кода с использованием стиля _Allman_ позволяет получить более приемлемый результат:

![picture37](https://habrastorage.org/webt/jv/x-/pf/jvx-pfjfuqs01oiohffb9mjmltw.png)

Несмотря на то, что в большинстве случаев _Allman_ объективно выигрывает у _1TBS_, иногда _1TBS_  оказывается предпочтительнее. Как правило, это связано с тем, что в таких случаях дополнительное вертикальное пространство, образуемое за счет расположения скобок на отдельных строках в стиле _Allman_, приводит к тому, что вся конструкция становится визуально разрозненной, теряет свой внутренний ритм и перестает восприниматься как единое целое. И в то же время, при использовании _1TBS_, либо смещение открывающей скобки невелико и не оказывает значимого влияние на восприятие кода, либо её обнаружение непринципиально (например, в случае конструкции `if`, когда и условие, и блок кода занимают по одной строке).

Так на предыдущем примере, постановка последней открывающей скобки в конце строки выглядит достаточно естественно, и образовавшееся пустое пространство лишь добавляет небольшой акцент, компенсирующий малый визуальный объем последнего блока, состоящего всего из одной строки. При расположении же скобки на отдельной строке это пространство становится слишком большим, и эта строка выглядит уже оторванной от остальной конструкции:

![](https://habrastorage.org/webt/qx/b5/qe/qxb5qetfvjaier48xmxjlzw1yr8.png)

Возникает вопрос: в каких случаях допустимо использовать _1TBS_? Здесь можно предложить следующие ограничения:

- Открывающая скобка располагается на той же строке, что и начало синтаксической конструкции, частью которой она является.
- Скобка не замаскирована большой массой кода непосредственно примыкающего к ней (в основном сверху).
- Она располагается в левой области текста (области наибольшего внимания).
- Смещение скобки по горизонтали не должно быть большим, так что скобка оказывается в области распознавания (14-15 размеров букв).
- Блок, ограниченный скобками, не содержит вложенных блоков таких, что скобки, ограничивающие эти вложенные блоки, расположены близко к открывающей скобке основного блока и визуально конкурируют с ней.

Таким образом, выбор способа расстановки скобок должен осуществляться в каждом конкретном случае, а формальное следование «жестким» правилам расстановки рано или поздно приводит к неудовлетворительному результату. Наиболее оптимальным вариантом видится комбинация стилей  _Allman_ (как основного) и _1TBS_ (как вспомогательного, используемого в редких случаях) .

<anchor>resume</anchor>

# Conclusion
Формирование удобочитаемого, то есть легкого для восприятия текста программы требует учета специфических особенностей зрения человека, таких как амбьентное и фокальное зрение, механизмов чтения текста вообще и особенностей чтения текстов программ в частности.

Основную стратегию оптимизации удобочитаемости можно сформулировать как _стремление к более эффективному использованию амбьентого зрения и снижения нагрузки на фокальное_.

Реализация этой стратегии достигается за счет формирования текста в виде относительно компактного по горизонтали «изображения», обладающего ярко выраженной визуальной структурой, корректно отражающей структуру программы. Это изображение формируется за счет иерархического группирования логически связанных элементов программы в компактные визуальные области посредством горизонтальных отступов, добавления пустых строк и выравнивания. Легкость чтения текста программы также обеспечивается выбором оптимальных по длине и выразительности идентификаторов и явного разделения их в тексте программы с помощью пробелов.

В  своей книге о типографике Роберт Брингхерст пишет[¹³](#13):

> Заголовки, подзаголовки, большие цитаты, сноски, иллюстрации, подписи к ним и другие включения, набираемые в разрез текста, создают своеобразные синкопы и вариации в противовес основному ритму строк… Эти вариации могут и должны вдохнуть жизнь а страницу…
>
> Чтобы книга, то есть длинный текст, была удобна для чтения, страница должна дышать в обоих измерениях.

Аналогично можно сказать,  что  структура программы, разворачиваясь в тексте сверху вниз, также обладает определённым ритмом и вариациями. Задача программиста — визуализировать этот ритм, сделать его явным в общем и в деталях.


------
<anchor>1</anchor>¹) <a href="https://www.semanticscholar.org/paper/Eye-movements-in-reading-and-information-20-years-Rayner/87c8a7be8d5e2e2209e766c3e28a3e8ee5babb64">Eye Movements in Reading and Information Processing: 20 Years of Research. Keith Rayner – University of Massachusetts at Amherst</a>
<anchor>2</anchor>²) <a href="https://researchonline.gcu.ac.uk/files/24953094/ICPC2015_authors_version.pdf">Eye Movements in Code Reading: Relaxing the Linear Order. Roman Bednarik, Bonita Sharif</a>
<anchor>3</anchor>³) <a href="http://www.ptidej.net/courses/inf6306/fall10/slides/course8/Storey06-TheoriesMethodsToolsProgramComprehension.pdf">Theories, tools and research methods in program comprehension: Past, Present and Future. Margaret-Anne Storey</a>
<anchor>4</anchor>⁴) <a href="http://www.cs.kent.edu/~jmaletic/papers/ICPC2010-CamelCaseUnderScoreClouds.pdf">An Eye Tracking Study on camelCase and under_score Identifier Styles. Bonita Sharif and Jonathan I. Maletic – Department of Computer Science Kent State University</a>
<anchor>5</anchor>⁵) <a href="https://www.researchgate.net/publication/238443707_Achieving_Software_Quality_through_Source_Code_Readability">Achieving Software Quality through Source Code Readability, Phillip Relf</a>
<anchor>6</anchor>⁶) <a href="https://ieeexplore.ieee.org/document/5328661">Relating Identifier Naming Flaws and Code Quality: An Empirical Study. Simon Butler</a>
<anchor>7</anchor>⁷) Грегори Р.Л. Глаз и Мозг. М.:Прогресс, 1970
<anchor>8</anchor>⁸) Дэвид Хантер Хьюбел. Глаз, мозг, зрение. Мир, 1970
<anchor>9</anchor>⁹) Величковский Б.М. Когнитивная наука. Основы психологии познания. Academia, Смысл, 2006
<anchor>10</anchor>¹⁰) Rudolph Arnheim. Art and Visual Perception.  University of California Press, 1974
<anchor>11</anchor>¹¹) Ярбус А.Я. Роль глаз в процессе зрения. М.:Наука, 1965
<anchor>12</anchor>¹²) <a href="http://emipws.org/wp-content/uploads/2015/02/emip2013_report.pdf">Eye movements in programming education: analysing the expert’s gaze. Simon. University of Newcastle, Australia</a>
<anchor>13</anchor>¹³) Роберт Брингхерст. Основы стиля в типографике. М.:Дмитрий Аронов, 2006
<anchor>14</anchor>¹⁴) Steve McConnell. Code complete.  Макконнелл С. Совершенный код. М.:Русская редакция, 2010
<anchor>15</anchor>¹⁵) <a href="https://link.springer.com/article/10.3758/BF03206156">Saccade size in reading depends upon character spaces and not visual angle. Robert E. Morrison, Keith Rayner, 1981</a>
<anchor>16</anchor>¹⁶) Robert L.Solso. Cognitive psychology. 6-th edition. Allyn & Bacon, 2001.
<anchor>17</anchor>¹⁷) <a href="https://www.sovsib.ru/docs/ost2912494.pdf">ОСТ 29.124–94. Издания книжные для взрослых читателей.</a>
<anchor>18</anchor>¹⁸) Alan Cooper. About Face: The Essentials of Interaction Design, Fourth Edition. John Wiley & Sons, Inc., 2014
<anchor>19</anchor>¹⁹) <a href="http://www.cs.kent.edu/~jmaletic/Prog-Comp/Papers/letovsky-1986-software.pdf">S. Letovsky and E. Soloway, Delocalized plans and program comprehension, IEEE Software, pp. 41–49, 1986.</a>