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

### Kingdom Screen

- [x] Background
- [ ] Buildings w/o functionality
- [ ] Add functionality to buildings
    - [ ] Inn: Hire heroes
    - [ ] Portal: Choose dungeon
    - [ ] Graveyard: Displa dead heroes

### Hero death screen

- [ ] Something

## Saving game

## Loading game

## Field of View

- [ ] For player
    - [ ] Update tiles
    - [ ]

## Autoexplore

- [ ] Determine how to handle changing the state of the PlayerController
- [ ] Implement autoexplore
    - [ ] Toggle on
    - [ ] Toggle off at button click
    - [ ] Toggle off when enemy in view

## Items

- [ ] Initial item skeleton
- [ ] Inventory component added to Entity
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
