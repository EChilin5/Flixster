# Flix
Flix is an app that allows users to browse movies from the [The Movie Database API](http://docs.themoviedb.apiary.io/#).

## Flix Part 2

### User Stories

#### REQUIRED (10pts)

- [x] (8pts) Expose details of movie (ratings using RatingBar, popularity, and synopsis) in a separate activity.
- [x] (2pts) Allow video posts to be played in full-screen using the YouTubePlayerView.

#### BONUS

- [x] Implement a shared element transition when user clicks into the details of a movie (1 point).
- [x] Trailers for popular movies are played automatically when the movie is selected (1 point).
  - [x] When clicking on a popular movie (i.e. a movie voted for more than 5 stars) the video should be played immediately.
  - [ ] Less popular videos rely on the detailed page should show an image preview that can initiate playing a YouTube video.
- [ ] Add a play icon overlay to popular movies to indicate that the movie can be played (1 point).
- [ ] Apply data binding for views to help remove boilerplate code. (1 point)
- [x] Add a rounded corners for the images using the Glide transformations. (1 point)

### App Walkthough GIF


<img src="https://github.com/EChilin5/Flixster/blob/master/walkthrough_part2_1.gif" width=250><br>

<img src="https://github.com/EChilin5/Flixster/blob/master/walkthrough_part2_2.gif" width=250><br>


<img src="https://github.com/EChilin5/Flixster/blob/master/walkthrough_part2_3.gif" width=250><br>

### Notes

Describe any challenges encountered while building the app.

The challenges I faced for this portion of the assignment was the shared element. As I forgot to include a 
specific id which made the landscape version to crash. After reviewing the code I was able to locate. Additionally
getting the api to work was troublesome as I had to downgrade to api 29 since it allowed the video to play. 
My emulator is kind of slow so it will take a while for it to load so I couldn't tell if it worked or not. There are 
times where the UI might crash as it loading but if I wait it will load eventually.

## Open-source libraries used
- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

---

## Flix Part 1

#### REQUIRED (10pts)
- [x] (10pts) User can view a list of movies (title, poster image, and overview) currently playing in theaters from the Movie Database API.

#### BONUS
- [x] (2pts) Views should be responsive for both landscape/portrait mode.
   - [x] (1pt) In portrait mode, the poster image, title, and movie overview is shown.
   - [x] (1pt) In landscape mode, the rotated alternate layout should use the backdrop image instead and show the title and movie overview to the right of it.

- [x] (2pts) Display a nice default [placeholder graphic](https://guides.codepath.org/android/Displaying-Images-with-the-Glide-Library#advanced-usage) for each image during loading
- [x] (2pts) Improved the user interface by experimenting with styling and coloring.
- [ ] (2pts) For popular movies (i.e. a movie voted for more than 5 stars), the full backdrop image is displayed. Otherwise, a poster image, the movie title, and overview is listed. Use Heterogenous RecyclerViews and use different ViewHolder layout files for popular movies and less popular ones.

### App Walkthough GIF

<img src="https://github.com/EChilin5/Flixster/blob/master/walkthrough.gif" width=250><br>

<img src="https://github.com/EChilin5/Flixster/blob/master/walkthrough2.gif" width=250><br>



### Notes
Describe any challenges encountered while building the app.

Some challenges I faced when building this app was working with UI.There are some features I prefered to do in code for xml while other times the design were useful. It was the relative layout that made it quite difficult. At first I had issues connecting to the internet in order to access the API and fetch data properly.

### Open-source libraries used

- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Androids

