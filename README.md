About Canada 
============

About Canada app shows the list of some unknown key facts of Canada in a fast and efficient way.

Specification :
--------------

It is an Android app which:

1. Ingests a json feed from https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json  The feed contains a title and a list of rows.

2. Displayed the content (including image, title and description) in a recycler view.  The title in the ActionBar is updated from the json data.  

3. Each row is dynamically sized to the right height to display its content - no clipping, no extraneous white-space etc. 

4. Images are loaded lazily (not download them all at once, but only as needed).

4. Implemented a swipe refresh layout allowing the data & view to be updated.

5. Not blocked the UI while loading the data from the json feed.


Technical Feature:
-----------------

1- It has used the recycler view for smooth scrolling of the list.

2- Glide for fast image loading with disk cache management and resource pooling in a simple and easy way.

3- Used Andorid Jetpack library for MVVM pattern with Live Data.

4- Used retrofit, rx library for API hitting and Gson class for Json parsing. 

5- Sdp library for Ui manament, to make it compatible for different screen size devices.

6- Also Used Mockito library for writting Unit Test cases.
