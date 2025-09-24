import java.io.*;
import java.nio.file.*;
import java.security.MessageDigest;
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
    }

    public static String hashFile(String path) throws Exception {
        File file = new File(path);
        if (!file.exists()) {
            throw new IOException("File not found");
        }
        if (testHashFileEmptyFiles(path)) {
            return "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";
        }
        if (testHashFileLargeFiles(path)) {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            try (FileInputStream fis = new FileInputStream(path)) {
                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    digest.update(buffer, 0, bytesRead);
                }
            }
            return bytesToHex(digest.digest());
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String contents = Files.readString(Path.of(path));
            byte[] encodedhash = digest.digest(contents.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedhash);
        } catch (Exception e) {

        }
        return null;
    }

    private static String bytesToHex(byte[] hash) { // https://www.baeldung.com/sha-256-hashing-java
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static boolean testHashFileEmptyFiles(String path) {
        File file = new File(path);
        return file.length() == 0;
    }

    public static boolean testHashFileLargeFiles(String path) {
        File file = new File(path);
        if (file.length() > 52428800) {
            return true;
        }
        return false;
    }

    public static String toString() {
        return "File name: MyFileWrtiter";
    }
}