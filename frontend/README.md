# Overview
This is the frontend of `Soccer player management App` web app.

## Library used
1. UI built with `ReactJs`. 
2. For styling [tailwind.css](https://tailwindcss.com/) is used.
3. For state management `React Context API` is used


## Special consideration
1. User can't send multiple login/signup or any edit request simutanously.
2. Page not found and Server down case is handled.
3. Authentication is checked before accessing a url. If not authorised autmatically redirects to login page.
4. Compatible with any screen size