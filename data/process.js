"use strict";
const utils = require('./utils');
const cleaner = require('./cleanData');
const mocking = require('./mocking');

const wines = require('./wines.json').map(cleaner.cleanWine);

// Wines DB
utils.writeJsonAsync('../reference_service/src/main/resources/wines.json', wines);

// Comments
const comments = {};
wines.forEach(wine => {
  comments[wine.id] = mocking.createComments();
});
utils.writeJsonAsync('../comments_service/src/main/resources/comments.json', comments);

// Stocks
const stocks = {};
wines.forEach(wine => {
  stocks[wine.id] = mocking.createStock();
});
utils.writeJsonAsync('../stocks_service/src/main/resources/stocks.json', stocks);

// Mock Cellar
utils.shuffle(wines);
const cellar = wines
  .filter((elt, index) => index < 12)
  .map(mocking.createCellarWine);
utils.writeJsonAsync('result/mock/cellar.json', cellar);

// Mock Catalog
utils.shuffle(wines);
const search = wines.filter((elt, index) => index < 12);
utils.writeJsonAsync('../web-frontend/src/assets/mock/search.json', search);

// Mock Detail
const wine = wines[0];
const detail = {
  wine: wine,
  stock: stocks[wine.id],
  comments: comments[wine.id]
};
utils.writeJsonAsync('../web-frontend/src/assets/mock/detail.json', detail);

// TODO Mock Cart
utils.shuffle(wines);
const cart = wines
  .filter((elt, index) => index < 4)
  .map(mocking.createCartWine);

utils.writeJsonAsync('../web-frontend/src/assets/mock/cart.json', cart);


