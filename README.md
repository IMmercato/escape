# Escape

A mysterious message has appeared on your terminal. Your mission is to compile the source code, run the program, and uncover the truth hidden within ancient Rome, Renaissance Florence, and Dante's Inferno.

## Available Versions

This escape room experience is available in two versions:

### 1. **Java Desktop Version** (Original - GUI + Terminal Hybrid)
A rich experience combining Swing GUI windows with terminal-based puzzles.

### 2. **Web Version** (Browser-Based)
A fully web-based implementation of Chapter I playable in any modern browser.

---

## Java Desktop Version

### Prerequisites

Before you begin, ensure you have the **Java Development Kit (JDK) 8** or higher installed on your system.

* To check your version, run: `java -version`

### Getting Started

Follow these steps to initialize the sequence:

1. **Navigate to the Project**
   
   Open your terminal and move into the source directory:
   ```bash
   cd escape
   ```

2. **Compile the Source**
   
   Transform the Java file into executable bytecode:
   ```bash
   javac escape.java
   ```

3. **Execute the Program**
   
   Run the application to reveal what's hidden:
   ```bash
   java escape
   ```

### Troubleshooting

* **"javac is not recognized":** Ensure your JDK `bin` folder is added to your system's PATH environment variable.
* **Case Sensitivity:** Remember that Java is case-sensitive. Ensure the filename matches `escape.java` exactly.

---

## Web Version

### Prerequisites

Before you begin, ensure you have **Node.js** installed on your system.

* To check if Node.js is installed, run: `node -v`
* Download Node.js from: [nodejs.org](https://nodejs.org/)

### Getting Started

Follow these steps to run the web version:

1. **Navigate to the Web Directory**
   
   Open your terminal and move into the web directory:
   ```bash
   cd web
   ```

2. **Install Dependencies**
   
   Install the required npm packages:
   ```bash
   npm install
   ```

3. **Start the Server**
   
   Launch the web server:
   ```bash
   npm start
   ```

4. **Open in Browser**
   
   Open your web browser and navigate to:
   ```
   http://localhost:3000
   ```

### Troubleshooting

* **Port already in use:** If port 3000 is already in use, modify the PORT variable in `server.js`
* **Cannot find module 'express':** Run `npm install` in the web directory
* **Server not starting:** Ensure Node.js is properly installed with `node -v`

---

## Notes

* The web version currently implements Chapter I
* The Java version includes all four chapters
* Both versions maintain the same core puzzles and storyline for Chapter I
* Your progress and score are tracked throughout the journey

---

## License

MIT License - See LICENSE file for details

## Credits

Created by Imesh (IMmercato)