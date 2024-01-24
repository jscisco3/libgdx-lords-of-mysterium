- Attributes
- Items
- Inventory
- Equipment

## Items

- [x] Parse Raws
- [x] Spawn Table
- [x] Pick Up Action
- [x] Drop Action
- [ ] Improve Inventory window
    - [ ] Item category, weight, value
    - [ ] Keybindings for dropping
    - [ ] Large inventory (scrolling, pagination, tabs, etc)
- [ ] Action prototype passed to InventoryWindow
    - [ ] d -> opens inventory window, when selecting item it will be dropped
    - [ ] e -> opens inventory window, when selecting item it will be equipped. Only show equippable items
    - [ ] i -> opens inventory window, whene selecting the item it will be `used`: equipped if equippable, consumed
      otherwise
- [ ] Item stacking
    - [ ] Based on name?
    - [ ] Based on attributes?
    - [ ] Split stacking

### Consumables

- [ ] Health potion
- [ ] Scroll of magic missile
    - [ ] Targeting state? May have to push rendering to the State class, as well as input.

### Equipment

## Animations

## Attributes

## Packaging and Deployment

- [ ] Run on a clean machine
    - [ ] jpackage
    - [ ] packr

## Asset Manager

- [x] Loading screen: Initial
- [ ] Loading screen: pretty
- [x] Asset manager class added to Game
    - ref: https://stackoverflow.com/questions/33651323/libgdx-best-way-to-load-all-assets-in-the-game
- [x] Refactor how assets are stored on Entities and Features

## Dungeon Generation

- [ ] Fix BSP Generator
- [ ] Drunken Walk Generator
- [ ] Cellular Automata Builder
- [x] Room Based Starting Position

## Monster / Item Generation

- [x] Raws
- [x] Room Based Spawner
- [ ] Voronoi Spawner

## Player Generation

- [x] Area Based Starting Position
- [x] Room Based Starting Position

# New Game

- [x] Generate a Single Level
- [x] Place the Player

## Gameplay

- [x] Walk around
- [x] Explore
- [x] Bump Combat
- [ ] Inventory Management

## AI

- [x] Wander AI
- [x] Hunter Seeker AI
- [ ] 