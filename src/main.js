//in this file i'll work with all about web scrapping

// const { getJson} = require("serpapi");
// const util = require('util');
// require('dotenv').config();
// const API_KEY = process.env.API_KEY;

let mapa = new Map();

mapa.set(0, "MAD");//madrid
mapa.set(1, "BCN");//BARCELONA
mapa.set(2, "LHR");//londres main
mapa.set(3, "CDG");//paris
mapa.set(4, "valor3");
mapa.set(5, "valor3");
mapa.set(6, "valor3");
mapa.set(7, "valor3");
mapa.set(8, "valor3");
mapa.set(9, "valor3");


const { getJson } = require("serpapi");

getJson({
    api_key: "435d1bd28c03fcce992e97899fcf847517cb584cde55b678cd03bc9d00bad16",
    engine: "google_flights",
    hl: "es-419",
    gl: "co",
    departure_id: "BOG",
    arrival_id: "MAD",
    outbound_date: "2024-05-21",
    currency: "COP",
    adults: "2",
    stops: "0",
    no_cache: "true",
    type: "2",
    travel_class: "2"
  }, (json) => {
    console.log(json);
  });
