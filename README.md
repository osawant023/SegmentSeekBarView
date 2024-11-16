<h1 align="center">SegmentSeekBarView</h1>

<p align="center">
  <img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-a503fc?logo=kotlin&logoColor=white&style=for-the-badge"/></a>
  <img alt="XML" src="https://img.shields.io/static/v1?style=for-the-badge&message=XML&color=4285F4&logo=XML&logoColor=FFFFFF&label="/></a> 
  <a href="https://github.com/osawant023/SegmentSeekBarView/releases"><img alt="XML" src="https://img.shields.io/static/v1?style=for-the-badge&message=Release:1.1&color=4285F4&logo=Github&logoColor=white&label="/></a> 
</p>

<b>SAMPLE:</b><br>
![SegmentSeekBarView Demo](https://github.com/osawant023/SegmentSeekBarView/blob/master/Screenshot%202024-11-17%20011349.png)


## Installation

[![](https://jitpack.io/v/osawant023/SegmentSeekBarView.svg)](https://jitpack.io/#osawant023/SegmentSeekBarView)

To get started with SegmentSeekBarView in your Android XML project, 
Add it in your root `build.gradle` at the end of repositories:

```
allprojects {
	repositories {
		...
	    maven { url 'https://jitpack.io' }
    }
}
```

Lastly, add the following dependency to your app's `build.gradle.kts` (Kotlin) or `build.gradle` (Groovy) file:

<details>
<summary>Kotlin</summary>
<br>

```kotlin
dependencies {
    implementation("com.github.osawant023:SegmentSeekBarView:$currentVersion")
}
```
</details>

<details>
<summary>Groovy</summary>
<br>

```kotlin
dependencies {
    implementation 'com.github.osawant023:SegmentSeekBarView:$currentVersion'
}
```
</details>

# Usage

## <b> Segments configuration </b>

```kotlin
 segmentProgressView.setSegments(
            barHeight = 20,
            value = 50f ,
            max = 100f ,
            badgeWidth = 15,
            badgeHeight = 15,
            newSegments = arrayListOf(
                SegmentData(
                    "Segment 1",
                    ContextCompat.getColor(this, R.color.red1),
                    "25"
                ),
                SegmentData(
                    "Segment 2",
                    ContextCompat.getColor(this, R.color.red2),
                    "50"
                ),
                SegmentData(
                    "Segment 3",
                    ContextCompat.getColor(this, R.color.red3),
                    "75"
                ),
                SegmentData(
                    "Segment 4",
                    ContextCompat.getColor(this, R.color.red4),
                    "100"
                )
            ),

        )
        segmentProgressView.invalidate()
```


