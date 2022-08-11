let url1;
let url2;
let host;

const hostName = window && window.location && window.location.hostname;

if (hostName === "localhost") {
  url1 = "http://localhost:8080/api"
}

url2 = "http://192.168.0.174:8080/api";

host = url1

export const API_BASE_URL = `${host}`