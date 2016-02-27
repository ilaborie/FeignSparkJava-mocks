"use strict";
const fs = require('fs');
const logger = console;

module.exports.writeJsonAsync = (file, json) => {
  return new Promise((resolve, reject) => {
    const data = JSON.stringify(json, null, 2);
    fs.writeFile(file, data,
      err => {
        if (err) {
          reject(err);
        } else {
          logger.info(`Saved file ${file}`);
          resolve(file);
        }
      });
  });
};

module.exports.mkdirAsync = (path) => {
  return new Promise((resolve, reject) => {
    fs.mkdir(path, err => {
      if (err) {
        logger.error(err);
        reject(err);
      } else {
        logger.info(`Make dir ${path}`);
        resolve(path);
      }
    });
  });
};

module.exports.randomBoolean = () => Math.random() > 0.5;
module.exports.randomValue = (min, max) => Math.floor(Math.random() * (max - min)) + min;
module.exports.randomGaussian = () => ((Math.random() + Math.random() + Math.random() + Math.random() + Math.random() + Math.random()) - 3) / 3;
module.exports.randomPoisson = lambda => {
  const L = Math.exp(-lambda);
  let k = 0;
  let p = 1;
  do {
    k = k + 1;
    let u = Math.random();
    p = p * u;
  } while (p > L);
  return k - 1;
};

module.exports.shuffle = array => {
  for (let i = array.length; i; i -= 1) {
    let j = Math.floor(Math.random() * i);
    let x = array[i - 1];
    array[i - 1] = array[j];
    array[j] = x;
  }
};
