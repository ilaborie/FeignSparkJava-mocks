'use strict';
const utils = require('./utils');
const cleaner = require('./cleanData');
const mocking = require('./mocking');

const colors = new Set(["Vin Blanc", "Vin Rouge", "Vin Rosé", "Vin Ambré"]);

// Wine
const wines = require('./wines.json')
  .filter(wine => colors.has(wine.couleur))
  .filter(wine => "France" === wine.pays)
  .map(cleaner.cleanWine);

// Regions
const regions = new Set();
wines.forEach(wine => regions.add(wine.region));
console.log(regions);

// Comments
const comments = {};
wines.forEach(wine => {
  comments[wine.id] = mocking.createComments();
});

// Stocks
const stocks = {};
wines.forEach(wine => {
  stocks[wine.id] = mocking.createStock(wine);
});

// Mock Cellar
utils.shuffle(wines);
const cellar = wines
  .filter((elt, index) => index < 12)
  .map(mocking.createCellarWine);

// Mock Catalog
utils.shuffle(wines);
const search = wines.filter((elt, index) => index < 12);

// Mock Detail
const wine = wines[0];
const detail = {
  wine: wine,
  stock: stocks[wine.id],
  comments: comments[wine.id]
};

// Mock Cart
utils.shuffle(wines);
const cart = wines
  .filter((elt, index) => index < 4)
  .map(mocking.createCartWine);

// Write files
utils.writeJsonAsync('../reference_service/src/main/resources/wines.json', wines);
utils.writeJsonAsync('../comments_service/src/main/resources/comments.json', comments);
utils.writeJsonAsync('../stocks_service/src/main/resources/stocks.json', stocks);
utils.writeJsonAsync('../web-frontend/src/assets/mock/cellar.json', cellar);
utils.writeJsonAsync('../web-frontend/src/assets/mock/search.json', search);
utils.writeJsonAsync('../web-frontend/src/assets/mock/detail.json', detail);
utils.writeJsonAsync('../web-frontend/src/assets/mock/cart.json', cart);
