"use strict";
const utils = require('./utils');
const faker = require('faker');
faker.locale = 'fr';

module.exports.createCellarWine = wine => {
  return {
    wine: wine,
    favorite: utils.randomBoolean() && utils.randomBoolean(),
    quantity: utils.randomValue(1, 12)
  }
};

module.exports.createCartWine = wine => {
  return {
    wine: wine,
    quantity: utils.randomValue(1, 6)
  };
};

module.exports.createComments = () => {
  const result = [];
  const size = utils.randomValue(0, 8);
  for (let i = 0; i < size; i++) {
    result.push({
      id: faker.random.uuid(),
      author: faker.internet.userName(),
      email: faker.internet.email(),
      date: faker.date.recent(),
      message: faker.lorem.sentences()
    });
  }
  return result;
};

const categoryPrices = {
  LOW: 50,
  MID: 150,
  EXP: 600
};
module.exports.createStock = wine => {
  return {
    stock: utils.randomValue(0, 80),
    price: utils.randomPoisson(categoryPrices[wine.priceCategory]) / 10
  };
};


