package fileloader.loaderapp.util;

import lombok.val;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ZipUtil {

    public static byte[] zip(byte[] data){
        try(val outputStream = new ByteArrayOutputStream(data.length)) {
            val deflater = new Deflater();
            deflater.setInput(data);
            deflater.finish();
            val buffer = new byte[1024];
            while (!deflater.finished()) {
                int count = deflater.deflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
