import java.io.IOException;

public class Test {
    public static void main(String[] args) throws Exception {
        System.out.println(MyFileWriter.hashFile("aiden.txt"));
        //Should return: 2a8e9b700420598b7dd4e3c6fdcf5b9d8d9d082e21c64f01cf44a861ce132991
        System.out.println(MyFileWriter.hashFile("thegreatgatsby.txt"));
        //Should return: 0a3efed01531852c9b1594bbb98ab6e09a6e37ec0eb3dfc86676578e121fa29f
        System.out.println(MyFileWriter.hashFile("special.txt"));
        //Should return: 453cb3b789e7b403d4cb5342bc1469fb418be79e1ef74f30b79e6f699f20a39d
        System.out.println(MyFileWriter.hashFile("empty.txt"));
        //Should return: e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855
    }
}
