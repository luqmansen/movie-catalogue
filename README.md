# movie-catalogue
## Movie catalogue application for Google Developer Kejar 2019 

[![](http://img.youtube.com/vi/hkB9BcuPOTI/0.jpg)](http://www.youtube.com/watch?v=hkB9BcuPOTI "")

This is a native android app for submission requirement in "Menjadi Android Developer Expert" from Google Developer Kejar 2019 in collaboaration with dicoding.com

There are 5 stage of this application representing 5 submission for the program 

1.  **Submission 1: Simple Movie Catalogue**
</br>**Code**: https://github.com/luqmansen/movie-catalogue/releases/tag/1.0
    </br> 
    Requirement:
    1. Listview:
        - implement List view for main movie list
    2. Parcelable:
        - use parcelable concept for send data between activity
    
  
2. **Submission 2: Movie Catalogue (UI/UX)**
    </br>**Code**: https://github.com/luqmansen/movie-catalogue/releases/tag/2.0
    </br>Requirement:
    1. Movie List:        
        - 2 Pages for displaying movie and TV show list
        - Use fragment for task above
        - Use RecycleView for showing movie list
        - Use TabLayout, BottomNavigationView, etc for navigation between movie pages and TV show pages
    2. Movie Detail:
        - Display movie poster and more info
        - Use Parcelable for interface between activity or fragment
        - Use ConstraintLayout for layouting
    3. Localization:
        - App should (at least) support Bahasa Indonesia and English


3. **Submission 3: Movie Catalogue (API)**
    </br>Requirement:
    1. Movie List:        
        - Show loading indicator while data gets loaded
        - Other reqs. are exactly the same as Submission 2
    2. Movie Detail:
        - idem
    3. Localization:
        - idem
    4. Configuration Change:
        - Application has to be able to keep the data when phone orientation changes.
    
    Nice to have
    - Use 3rd party library (eg: Retrofit, etc)
    - Apply design pattern (eg: MVVM, MVP, etc)
    - App show an error when data can't be displayed
        
4. **Submission 4: Movie Catalogue (Local Database)**
    <br>
    Idem with above, but able to store selected favorite movie on sqlite database and display it to another fragment
    
5. **Submission 5: Final Project**
    <br>
    1. Seach movie feature with API endpoints query **done**
    2. Stack Widget
    3. Alarm Manager
    4. Content Provider
