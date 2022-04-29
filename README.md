
## Setup
- You can change API_KEY by replacing *stringConfigField* in *BuildProductFlavors.kt* file

## Requirements

- JDK 8
- Latest Android SDK tools
- Latest Android platform tools
- Android SDK 29
- AndroidX

---

## Technologies I use in this project

1. **Modular App Architecture** with 2 main features: **news-list** and **news-detail**.
2. **Fresco** for displaying image.
3. **Retrofit**.
4. **Coroutine** .
5. **MVVM Architecture**.

---

## Demo features

1. Load list of *everything* and *top headlines* articles
2. Pagination articles with loading indicator at the bottom of the screen
2. Filter option to sort *everything* articles
3. Display article in webview: forward, backward and refresh
4. Cache API response and articles to use it in offline mode (no internet connection)
