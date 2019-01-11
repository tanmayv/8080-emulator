import sun.misc.ClassLoaderUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReader {
  public static byte[] getProgram(String filePath) throws IOException, URISyntaxException {
    URL url = FileReader.class.getResource(filePath);
    Path path = Paths.get(url.toURI());
    return Files.readAllBytes(path);
  }
}
