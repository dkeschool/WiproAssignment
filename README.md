About Canada 
============

About Canada app shows the list of some unknown key facts of Canada in a fast and efficient way.

Technical Feature:
-----------------

1- It has used the recycler view for smooth scrolling of the list.

2- Glide for fast image loading with disk cache management and resource pooling in a simple and easy way.

3- Used Andorid Jetpack library for MVVM pattern with Live Data.

4- Used retrofit, rx library for API hitting and Gson class for Json parsing. 

5- Sdp library for Ui manament, to make it compatible for different screen size devices.

6- Also Used Mockito library for writting Unit Test cases.


Specification :
--------------

Create an Android app which:

1. Ingests a json feed from https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json  The feed contains a title and a list of rows.  You can use a third party json parser to parse this if desired.

2. Displays the content (including image, title and description) in a ListView  The title in the ActionBar should be updated from the json data.  Each row should be dynamically sized to the right height to display its content - no clipping, no extraneous white-space etc. This means some rows will be larger than others.

3. Loads the images lazily (don’t download them all at once, but only as needed).

4. Implement a refresh function allowing the data & view to be updated  Use either a refresh button or pull down to refresh.

5. Should not block UI when loading the data from the json feed.


