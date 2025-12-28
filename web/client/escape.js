let attempts = 3;
let score = 100;

const shift = Math.floor(Math.PI * 5) % 26;
const encoded = "THd0YyBScHRocGcgdXRhYSwgaXd0IEh0Y3BpdCBsd3hoZXRndHM6IEhFRkcgamN4aXRzIGx4aXcgaXd0IFhzdGggY2picXRnLCB5ZHhjdHMgcW4gamNzdGdocmRndC4gVXhnaGksIGd0aGlkZ3QgaXd4aCBocmdkYWEgbHhpdCBpd3QgWHN0aCBpZCBhdHBnYyBiZGd0Lg==";
const encrypted = atob(encoded);

document.getElementById('welcomeText').textContent = "rotaiV evlaS";

document.getElementById('encryptedText').textContent = encrypted;

function caesarCipher(text, key) {
    let result = '';
    const shift = ((key % 26) + 26) % 26;

    for (let i = 0; i < text.length; i++) {
        const char = text[i];
        const code = char.charCodeAt(0);

        if (code >= 65 && code <= 90) {
            result += String.fromCharCode(((code - 65 + shift) % 26) + 65);
        } else if (code >= 97 && code <= 122) {
            result += String.fromCharCode(((code - 97 + shift) % 26) + 97);
        } else {
            result += char;
        }
    }
    return result;
}


function startGame() {

}