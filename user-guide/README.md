Check [Readme](/README.md) for how to setup this game api in your local.

When we start this game it comes with few users (user1 ~ user10) and some dummy data.


# Sign up
- First let's create a new user named `khairul`.
- Basic validation (eg: simple email validation) present in signup page. 
- Also you can't pick existing email/user names
- Register button get disabled while form is submitting (Also text changed to `submitting...` )
- Upon successful Sign up user goes to home page.

![Sign Up](/user-guide/signUp.gif)

# Login 
- shows login errors in toaster.
- Upon successful login user goes to home page.


![Login](/user-guide/login.gif)

# Team Crud Operation 
- Upon successful Register a new User gets a team with `5 Billion` budget and he also gets `20 players` each having `1 million` value. So total team value becomes `20 million` (sum of all players value)
- User can only change team name and country. Other values are managed by system


![Team Crud](/user-guide/team-crud.gif)


# Player Crud Operation + Sell player
- User can only change player name and country.
- He can also sell his players with a fixed price.
- Upon sell request players get added to the transfer list.

![Player Crud](/user-guide/players-crud-and-sell.gif)

# Buy Players from transfer list

## Situation 1: Owner can't buy his own players

![User can't buy own players](/user-guide/player-cant-buy-own-player.gif)

## Situation 2: User buys players from another user
- Suppose khairul goes to transfer list and buys a player from user1, whose selling price is `2 million` (initial dummy data)
- So, his budget gets reduced to `3 million` as he just spent `2 million`
- Also his team value increased over `2 million`, because system is adding random additional value upon player sell.

![Budget and value update](/user-guide/budget-updated-by-system.gif)

## Situation 3: User cant't buy any more players when he doesn't have enough budget.
- khairul tries to buy more players after buying 2 players, he doesn't have enough budget to buy more. So he gets errors.
  
![Not enough budget](/user-guide/not-enough-budget.gif)



## Situation 4: Concurrency case
Suppose 2 player tries to buy same player at the same time. If 1st user completes his transfer before 2nd user, then 2nd user will get `already sold` error

![Already sold errors](/user-guide/already-sold.gif)


# Final team situations
Khairul bought 2 players from user1 each having `2 million` sell value. After this transfer team situation becomes like this:

### Khairul team's situation
![Khairul's team](/user-guide/khairls-team-situation.png)

### user1 team's situation
![user1's team](/user-guide/user1-team-situation.png)