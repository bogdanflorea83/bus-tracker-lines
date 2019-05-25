const functions = require('firebase-functions');
const express = require('express');
const app = express();

app.get('/current_version', (request, response) => {
    response.set('Cache-Control', 'public, max-age=86400, s-maxage=86400');
    response.json({"version": "v1"});
});

app.get('/cities', (request, response) => {
    response.set('Cache-Control', 'public, max-age=86400, s-maxage=86400');
    response.json(require("./"+request.query.version+"/"+request.query.country+"/citiesMin.json"));
});

app.get('/city', (request, response) => {
    response.set('Cache-Control', 'public, max-age=86400, s-maxage=86400');
    response.json(require("./"+request.query.version+"/"+request.query.country+"/"+request.query.city+"/allLinesMin.json"));
});

exports.app = functions.https.onRequest(app);
