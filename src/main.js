//in this file i'll work with all about web scrapping

// const { getJson} = require("serpapi");
// const util = require('util');
// require('dotenv').config();
// const API_KEY = process.env.API_KEY;

var prompt = require('prompt-sync')();
pedirFechas();
pedirCiudades();

function pedirFechas() {
  //TODO validar formatos `
  let fechaPartida = prompt('Ingrese la fecha de partida (yyyy-mm-dd)');
  let fechaRegreso =  prompt('Ingrese la fecha de regreso (yyyy-mm-dd)');
}

function pedirCiudades() {
console.log("marque las ciudades a visitar");

  let mapa = new Map();

// Agregar las ciudades con sus respectivos códigos y países
mapa.set(0, { codigo: "MAD", ciudad: "Madrid", pais: "España", activo: false });
mapa.set(1, { codigo: "BCN", ciudad: "Barcelona", pais: "España", activo: false });
mapa.set(2, { codigo: "LHR", ciudad: "Londres", pais: "Reino Unido", activo: false });
mapa.set(3, { codigo: "CDG", ciudad: "París", pais: "Francia", activo: false });
mapa.set(4, { codigo: "FCO", ciudad: "Roma", pais: "Italia", activo: false });
mapa.set(5, { codigo: "AMS", ciudad: "Ámsterdam", pais: "Países Bajos", activo: false });
mapa.set(6, { codigo: "BER", ciudad: "Berlín", pais: "Alemania", activo: false });
mapa.set(7, { codigo: "PRG", ciudad: "Praga", pais: "República Checa", activo: false });
mapa.set(8, { codigo: "IST", ciudad: "Estambul", pais: "Turquía", activo: false });
mapa.set(9, { codigo: "VIE", ciudad: "Viena", pais: "Austria", activo: false });
mapa.set(10, { codigo: "LED", ciudad: "San Petersburgo", pais: "Rusia", activo: false });
mapa.set(11, { codigo: "LIS", ciudad: "Lisboa", pais: "Portugal", activo: false });

  mapa.forEach((valor, llave) => {
    console.log(` quiere visitar ${valor.ciudad} , ${valor.pais}`);
    let opcion = prompt('escoja 1 para si y otro para no');

    if (opcion == 1) {
      valor.activo = true;
    } else {
      valor.activo = false;
    }
  });
}

function elegirUnPlan(){
  console.log("1 para visitar la mayor cantidad de landmarks lo mas economico posible");
  console.log("2 para visitar la mayor cantidad de landmarks lo mas rapido posible");
  console.log("3 para ir con comodidad pero si es necesario perder algunas de landmarks");
  //TODO dependiendo de la opcion hacer algo
}

const fs = require("fs");
fs.writeFile("./src/archivo.txt", "datosss", (error) => {
  if (error) {
    throw error;
  }
  console.log("creado de forma exitosa");
});



/*
// const { getJson } = require("serpapi");

// getJson({
//     api_key: "435d1bd28c03fcce992e97899fcf847517cb584cde55b678cd03bc9d00bad16",
//     engine: "google_flights",
//     hl: "es-419",
//     gl: "co",
//     departure_id: "BOG",
//     arrival_id: "MAD",
//     outbound_date: "2024-05-21",
//     currency: "COP",
//     adults: "2",
//     stops: "0",
//     no_cache: "true",
//     type: "2",
//     travel_class: "2"
//   }, (json) => {
//     console.log(json);
//   });*/
