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
- [ ] Create savegame folder based on kingdom name
- [ ] After creating initial savegame folder, save kingdom.json
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
- [ ] For hero
- [ ] Update tiles to include **explored**

## Autoexplore

- [ ] Determine how to handle changing the state of the PlayerController
- [ ] Implement autoexplore
    - [ ] Toggle on
    - [ ] Toggle off at button click
    - [ ] Toggle off when enemy in view

## Items

- [ ] Initial item skeleton
- [x] Inventory component added to Entity
- [ ] Implement relatively generic Popup for choosing something
    - [ ] PickupItemCommand
    - [ ] DropItemCommand
- [ ] Generate items as part of level generation

## Zone
- [ ] Initial Zone skeleton
  - [ ] Theme (undead, fire, etc.)
  - [ ] Depth

## Level
- [ ] Level Generation strategy
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
