import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

public class MyFileWriter {
    public static void main(String[] args) throws IOException {
        String data = "Hello, World!";
        String fileName1 = "example.txt";
        String fileName2 = "example2.txt";
        String fileName3 = "example3.txt";
        String fileName4 = "example4.txt";
        String fileName5 = "example5.txt";
        String psTestFile = "priscillaTest!";
        System.out.println(System.getProperty("user.dir"));
        // generateHiddenFile();
        // generateRegFile();
        printFileSize(psTestFile); //testing filesize method i added

        //Creates the priscilla test file
        try (FileOutputStream outputStream = new FileOutputStream(psTestFile)) {
            outputStream.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 1. Using FileWriter
        try (FileWriter writer = new FileWriter(fileName1)) {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2. Using BufferedWriter
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName2))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3. Using FileOutputStream
        try (FileOutputStream outputStream = new FileOutputStream(fileName3)) {
            outputStream.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        

        // 4. Using BufferedOutputStream
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName4))) {
            bufferedOutputStream.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 5. Using Files (java.nio.file)
        try {
            Files.write(Paths.get(fileName5), data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateHiddenFile() throws IOException {
        Path secret = Path.of(".secret.txt");
        Files.writeString(secret, ":-)");
    }

    public static void generateRegFile() throws IOException {
        Path folder = Path.of(".veryprivate");
        Files.createDirectory(folder);
        Path file = folder.resolve("findme.txt");
        Files.writeString(file, "SSN: 1234567");
    }

    // Calculate and print the file size using the File class
    private static void printFileSize(String... fileNames) {
    long totalSize = 0;
    for (String fileName : fileNames) {
        File file = new File(fileName);
        if (file.exists()) {
            totalSize += file.length();
        }
    }
    System.out.println("Total size of all files: " + totalSize + " bytes");
}
}