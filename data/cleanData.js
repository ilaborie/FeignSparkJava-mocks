"use strict";

const toInt = value => parseInt(value);

const transform = {
  millesime: toInt,
  garderJusquA: toInt,
  aBoireAPartirDe: toInt,
  image: img => img.substring(img.lastIndexOf('/') + 1)
}

module.exports.cleanWine = wine => {
  const result = Object.assign({}, wine);
  delete result.type;
  for (let attr of Object.keys(result)) {
    if (transform[attr]) {
      result[attr] = transform[attr](result[attr]);
    }
  }
  return result;
};
