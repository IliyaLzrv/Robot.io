import Phaser from "phaser";

class GameScene extends Phaser.Scene {
  constructor(mapData) {
    super("GameScene");
    this.mapData = mapData;
  }

  preload() {
    this.load.tilemapTiledJSON("map", this.mapData); // Use the map data
    this.load.image("tiles", "path/to/tileset.png"); // Adjust tileset path
  }

  create() {
    const map = this.make.tilemap({ key: "map" });
    const tileset = map.addTilesetImage("tilesetName", "tiles");
    map.createLayer("backgroundLayer", tileset, 0, 0);
  }
}

export default function createPhaserGame(mapData) {
  return {
    type: Phaser.AUTO,
    width: 800,
    height: 600,
    parent: "phaser-game",
    scene: new GameScene(mapData),
    physics: {
      default: "arcade",
      arcade: {
        debug: false,
      },
    },
  };
}
