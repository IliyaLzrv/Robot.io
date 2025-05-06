import React, { useEffect } from 'react';
import Phaser from 'phaser';
import '../styles/battlefield.css';

const BattlefieldPage = () => {
  useEffect(() => {
    const config = {
      type: Phaser.AUTO,
      width: 800,
      height: 600,
      scene: {
        preload: function () {
          this.load.image('tiles', '/path-to-tileset.png'); // Replace with actual tileset path
          this.load.tilemapTiledJSON('map', '/path-to-map.json'); // Replace with actual Tiled JSON file
        },
        create: function () {
          const map = this.make.tilemap({ key: 'map' });
          const tiles = map.addTilesetImage('tilesetName', 'tiles'); // Replace tilesetName with Tiled's tileset name
          map.createLayer('Layer1', tiles, 0, 0);
        },
      },
    };

    new Phaser.Game(config);
  }, []);

  return <div id="battlefield"></div>;
};

export default BattlefieldPage;
