"use strict";

const fs = require('fs');
const wines = require('./wines.json');

const randomBoolean = () => Math.random() > 0.5;
const randomValue = (min, max) => Math.floor(Math.random() * (max - min)) + min;

const cellar = wines
  .filter((elt, index) => index < 12)
  .map(wine => {
    return {
      wine: wine,
      favorite: randomBoolean() && randomBoolean(),
      quantity: {
        value: randomValue(1, 12),
        unit: "Btls"
      }
    }
  });

const writeMockAsync = (file, json) => {
  const dest = `src/assets/mock/${file}`;
  return new Promise((resolve, reject) => {
    fs.writeFile(dest, JSON.stringify(json, null, 2),
      err => {
        if (err) reject(err);
        resolve(file)
      });
  });
};


Promise.all(
  [
    writeMockAsync('login.json', '123456'),
    writeMockAsync('cellar.json', cellar)
  ]
).then(() => console.log('done'));
