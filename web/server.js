const express = require('express');
const path = require('path');

const app = express();
app.use(express.json());

app.get("/", (req,res) => {
    res.sendFile(path.join(__dirname, "client", "escape.html"));
});

app.use(express.static(path.join(__dirname, 'client')));

const PORT = 3000 || process.env.PORT;

app.listen(PORT, () => {
    console.log("Server running on http://localhost:300");
});