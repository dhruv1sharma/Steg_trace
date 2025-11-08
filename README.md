# Steghide Detection Tool

## Overview
**Steghide Detection Tool** is a Java-based application designed to detect and extract hidden messages embedded within image files using the **Steghide** steganography utility. This tool provides an automated interface for analyzing images and retrieving concealed data, making it suitable for digital forensics, cybersecurity, and information security research.

---

## Features
- Detects hidden data within image files using Steghide.  
- Supports passphrase-protected and unprotected steganographic files.  
- Executes Steghide commands via Java's `ProcessBuilder` for cross-process communication.  
- Automatically extracts hidden messages to a temporary file.  
- Displays extraction status and hidden message content in the console.  
- Cleans up temporary output files after processing.  

---

## How It Works

1. The program prompts the user for:
   - The **image file path** to analyze.  
   - The **passphrase** (optional) for decoding hidden data.  

2. A Steghide extraction command is constructed and executed using:

   ```bash
   steghide extract -sf <imagePath> -p <passphrase> -xf temp_output.txt
````

3. If Steghide successfully extracts data, the program:

   * Reads the contents of `temp_output.txt`.
   * Displays the extracted message in the console.
   * Deletes the temporary file afterward.

4. If extraction fails, the tool provides diagnostic messages based on Steghide’s output and exit code.



## Requirements

* **Java JDK 8 or higher**
* **Steghide** installed on the system
  (Ensure Steghide’s executable path is correctly set in the code or added to the system PATH)
* Supported OS: **Windows, Linux, or macOS** (with path adjustments)

---

## Usage

### 1. Clone this repository:

```bash
git clone https://github.com/<your-username>/SteghideDetection.git
```

### 2. Navigate to the project directory:

```bash
cd SteghideDetection
```

### 3. Compile the Java file:

```bash
javac SteghideDetection.java
```

### 4. Run the program:

```bash
java SteghideDetection
```

### 5. Enter the image file path and optional passphrase when prompted.

---

## Example

```
Enter the path of the image file (e.g., C:\Users\sample.jpg): C:\Users\Dhruv\Pictures\secret.jpg
Enter the passphrase (leave empty if none): password123
```

**Output:**

```
Result: The image contains a hidden message.
Hidden Message:
Confidential Data: Project Alpha Key - 9A7X3B
```

---

## Notes

* Ensure Steghide is properly installed and accessible via system path or by specifying its full path in the source code.
* If no message is detected or an incorrect passphrase is provided, Steghide returns a non-zero exit code.
* The tool is primarily designed for research and educational purposes in the field of steganography and digital forensics.

---

## License

This project is released under the **MIT License**.
You are free to use, modify, and distribute it with proper attribution.

```



