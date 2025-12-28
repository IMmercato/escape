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

function showScreen(screenId) {
    document.querySelectorAll('.screen').forEach(s => s.classList.remove('active'));
    document.getElementById(screenId).classList.add('active');
}

function startGame() {
    showScreen('puzzle');
}

function sumbitShift() {
    const input = document.getElementById('shiftInput').value;

    if (input === '1') {
        document.getElementById('hint').textContent = atob("SWR1cyBNYXJ0aWFl");
        document.getElementById('suggest').textContent = atob("V2hlbiBkYWdnZXJzIG1ldCBkZXN0aW55");
        document.getElementById('omen').classList.add('show');
        document.getElementById('shiftInput').value = '';
        return;
    }

    const k = parseInt(input);
    if (isNaN(input)) {
        alert('The gods do not understand this symbol.');
        document.getElementById('shiftInput').value = '';
        return;
    }

    if (k === shift) {
        const restored = caesarCipher(encrypted, -k);
        document.getElementById('restoredText').textContent = restored;
        showScreen('gate');
    } else {
        attempts--;
        score -= 20;

        document.getElementById('attempts').textContent = attempts;
        document.getElementById('score').textContent = score;

        const rot13 = caesarCipher(encrypted, 13);
        document.getElementById('hintText').textContent = rot13;
        document.getElementById('hintBox').style.display = 'block';

        if (attempts === 0) {
            showScreen('gameover');
        }
    }

    document.getElementById('shiftInput').value = '';
}

function sumbitGate() {
    const psw = document.getElementById('psw').value;
    const flag = atob("c3Bxcl8=");
    const correctPhrase = flag + shift;

    if (psw === correctPhrase) {
        const message = "VGhlIERpY3RhdG9yIHdoaXNwZXJzOiBWZW5pLCBWaWRpLCBWaWNpLg==";
        document.getElementById('finalMessage').textContent = message;
        document.getElementById('score1').textContent = score;
        showScreen('victory');
    } else {
        alert('The Senate rejects your claim.');
    }
}

function verify() {
    showScreen('verify');
}

function sumbitDecode() {
    const message = "VGhlIERpY3RhdG9yIHdoaXNwZXJzOiBWZW5pLCBWaWRpLCBWaWNpLg==";
    const answer = document.getElementById('answer').value.trim();

    attempts = 3;

    if (answer === atob(message)) {
        showScreen('safe');
    } else {
        attempts--;
        score -= 20;

        if (attempts > 0) {
            alert("Incorrect. Attempts remaining: " + attempts);
        } else {
            alert("You have failed the Dictator's test. Game Over.");
            showScreen('gameover');
        }
    }
}

function unlockSafe() {
    const primo = document.getElementById('primo').value;
    const secondo = document.getElementById('secondo').value;
    const terzo = document.getElementById('terzo').value;
    const pin = primo + secondo + terzo;

    attempts = 3;

    if (pin === atob("NTU1")) {
        document.getElementById('finalScore').textContent = score;
        showScreen('complete');
    } else {
        attempts--;

        if (attempts > 0) {
            document.getElementById('safeAttempts').textContent = attempts + ' attempts left';
            document.getElementById('safeAttempts').style.color = '#ff4444';

            if (attempts === 1) {
                alert("Wrong combination!\n\nHint: Think about the message...");
            } else {
                alert("Wrong combination! Try again.");
            }

            document.getElementById('primo').value = '';
            document.getElementById('secondo').value = '';
            document.getElementById('terzo').value = '';
            document.getElementById('primo').focus();
        } else {
            alert("The safe locks permanently.\nYou have failed.");
            showScreen('gameover');
        }
    }
}

function closeOmen() {
    document.getElementById('omen').classList.remove('show');
}

document.getElementById('shiftInput').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') sumbitShift();
});

document.getElementById('psw').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') sumbitGate();
});

document.getElementById('answer').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') sumbitDecode();
});

document.getElementById('primo').addEventListener('input', function (e) {
    if (this.value.length === 1) {
        document.getElementById('secondo').focus();
    }
});

document.getElementById('secondo').addEventListener('input', function (e) {
    if (this.value.length === 1) {
        document.getElementById('terzo').focus();
    }
});

document.getElementById('terzo').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') unlockSafe();
});