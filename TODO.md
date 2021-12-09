## Asset Manager
- [x] Loading screen: Initial
- [ ] Loading screen: pretty
- [x] Asset manager class added to Game
    - ref: https://stackoverflow.com/questions/33651323/libgdx-best-way-to-load-all-assets-in-the-game
- [x] Refactor how assets are stored on Entities and Features

## Screens

### Main Menu
- [x] New game button to take to GameScreen
    - TODO: Send to screen for adding Kingdom name info, other stuff
- [x] Kingdom Screen to launch to generic kingdom layout quickly
- [x] Load game button added
    - TODO: Add functionality
- [x] Exit game button
- [ ] Settings
  - [ ] Keybindings
  - [ ] Resolution
  - [ ] Tileset?

### Kingdom Screen
- [x] Background
- [x] Screen for naming kingdom and so on 
- [x] Buildings w/o functionality
- [ ] Add functionality to buildings
    - [ ] Inn: Hire heroes
    - [ ] Portal: Choose dungeon
    - [ ] Graveyard: Display dead heroes
  
### Inn screen
- [ ] Display list of available heroes
- [ ] Hire hero and add to kingdom

### Portal screen
- [ ] Display three columns for the keyword to the dungeon
- [ ] Display preview of dungeon characteristics based on keywords
  - [ ] Have keywords be unidentified until visited?
  - [ ] Identify keywords by other means?
- [ ] Display list of recently visited dungeons

### Hero death screen
- [ ] Something

## Saving game
- [x] Create savegame folder based on kingdom name
- [x] After creating initial savegame folder, save kingdom.json
- [ ] Allow arbitrary save in kingdom screen
- [ ] Save kingdom with hired heroes
- [ ] Save kingdom with available heroes
- [ ] When entering dungeon, autosave kingdom
- [ ] When entering dungeon, save the dungeon name & seed

## Loading game
- [ ] Display loadable games
- [ ] Load into kingdom save
- [ ] Load into dungeon save

## Field of View
- [x] For hero
- [x] Update tiles to include **explored**
- [x] Update render method to only draw explored tiles
- [x] Update render method for fog of war
  - [ ] Update render method to show last thing of interest (entity, item) in explored square

## Autoexplore

- [x] Determine how to handle changing the state of the PlayerController
- [x] Implement autoexplore
    - [x] Toggle on
    - [x] Toggle off at button click
    - [ ] Toggle off when enemy in view
    - [ ] When everything reachable has been explored, navigate to stairs down
    - [ ] When everything reachable has been explored, navigate to stairs up if standing on stairs down

## Items

- [ ] Initial item skeleton
- [x] Inventory component added to Entity
- [x] Implement relatively generic Popup for choosing something
    - [x] PickupItemCommand
    - [x] DropItemCommand
- [ ] Generate items as part of level generation

## Zone
- [ ] Initial Zone skeleton
  - [ ] Theme (undead, fire, etc.)
  - [ ] Depth

## Level
- [x] Level Generation strategy
- [ ] Basic level generator with Squidlib
- [ ] AscendLevel command
- [ ] DescendLevel command

## Data files
- [ ] Data files for NPCs
- [ ] Data files for Items
- [ ] Loot table

## Equipment
- [ ] Equipment component for Entity
- [ ] Implement Equippable items
- [ ] Implement stat boots on equipped items
- [ ] EquipItem command (relies on PopupMenu)
- [ ] UnequipItem command (relies on PopupMenu)

## Game log
- [ ] Display messages in some log

## AI
### General AI enhancements
- [ ] Give each entity its own Dijkstra map that is initialized
- [ ] Entities should listen for changes to the level to update their dijkstra map

### Wander AI
- [ ] Initial skeleton that choose a goal and moves towards it
- [ ] Handle other entities in the dungeon when selecting the path

### Hunter Seeker AI
- [ ] Initial skeleton that chooses a target (the hero) and moves towards it
- [ ] Handle other entities in the dungeon when selecting the path