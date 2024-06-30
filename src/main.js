//in this file i'll work with all about web scrapping

// const { getJson} = require("serpapi");
// const util = require('util');
// require('dotenv').config();
// const API_KEY = process.env.API_KEY;
var prompt = require("prompt-sync")();
const fs = require("fs");

async function leer(ruta) {
  try {
    
    const data = await fs.promises.readFile(ruta, 'utf8'); // Use fs.promises.readFile with async/await
    //console.log(data.toString());
    return data.toString().split('\n');
  } catch (err) {
    console.error(err);
    return []; // Return an empty array in case of an error
  }
}

function scrapping(a, b) {}

function pedirFechas() {
  //TODO validar formatos `
  let fechaPartida = prompt("Ingrese la fecha de partida (yyyy-mm-dd)");
  let fechaRegreso = prompt("Ingrese la fecha de regreso (yyyy-mm-dd)");
}

function cargarCiudades() {}

async function pedirCiudades() {
  const datosCiudades = await leer(__dirname + "/ciudades.txt"); // Use await to get the data from leer
  console.log("Marque las ciudades a visitar ");

    let mapa = new Map();
  // Agregar las ciudades con sus respectivos códigos y países
    //TODO agregar paises???
    console.log("cant de ci" + datosCiudades.length);
    for(let i =0; i< datosCiudades.length; i++){
      let dato = datosCiudades[i].split("-");
      mapa.set(
        i,
        {
          codigo: dato[0],
          ciudad: dato[1],
          seleccionado: false
        },
      );
    }


  mapa.forEach((valor, llave) => {
    console.log(`\nquiere visitar ${valor.ciudad} `);
    let opcion = prompt('escoja 1 para si y otro para no ');

    if (opcion === 1) {
      valor.activo = true;
    } else {
      valor.activo = false;
    }
  });
}
pedirCiudades(); 
/*const { log } = require("console");
fs.writeFile("./src/archivo.txt", "datosss", (error) => {
  if (error) {
    throw error;
  }
  console.log("creado de forma exitosa");
});*/

//que haga las peticiones a todas las ciudades que marcó?????
/*
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
  });*/





  function ejecutarJavaParaSimpleOut() {
    const { exec, spawn } = require("child_process");
    const { stdout, stderr } = require("process");
    //const exec = require("child_process").exec;
  
    exec("javac src/Main.java", (error, stdout, stderr) => {
      if (error) {
        console.log(error);
        return false;
      }
      console.log(stdout);
    });
  
    // exec("java src/Main.java", (error, stdout, stderr) => {
    //   if (error) {
    //     console.log(error);
    //     return false;
    //   }
    //   console.log(stdout);
    // });
  }

  function ejecutarJavaComplejo() {
    const { execFile } = require("child_process");
  
    // const process = exec('java', ["Main.java"], (error, stdout, stderr) => {
    //   if (error) {
    //     throw error;
    //   }
    //   console.log(stdout);
    // });
    //const ruta = "C:\Users\willi\AppData\Roaming\Code\User\workspaceStorage\6675818ca8c938380defda64e289a7fb\redhat.java\jdt_ws\Explorando-Europa-Un-viaje-por-nodos-y-aristas-_4aacfc4b\bin"
    const process2 = execFile(
      "java ",
      ["-cp", "ruta", "src/Main.java"],
      (error, stdout, stderr) => {
        if (error) {
          throw error;
        }
        console.log(stdout);
      }
    );
  
    // console.log(proceso.pid);
    // console.log(proceso.connected);
  
    // proceso.stdout.on("data", function (dato) {
    //   console.log("Esta muerto?");
    //   console.log(proceso.killed);
    //   console.log(dato.toString());
    // });
  
    // proceso.on("exit", () => {
    //   console.log("El proceso termino");
    //   console.log(proceso.killed);
    // });
  }