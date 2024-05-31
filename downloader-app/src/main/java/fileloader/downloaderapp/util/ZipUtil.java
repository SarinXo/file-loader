package fileloader.downloaderapp.util;

import lombok.val;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ZipUtil {

    public static byte[] unzip(byte[] compressedData){
        try(val outputStream = new ByteArrayOutputStream(compressedData.length)) {
            val inflater = new Inflater();
            inflater.setInput(compressedData);
            val buffer = new byte[1024];
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
