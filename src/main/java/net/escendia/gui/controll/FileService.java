package net.escendia.gui.controll;

import net.escendia.ioc.Singleton;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

@Singleton
public class FileService {

    private final ArrayList<String> mimeTypesAllowed;

    public FileService() {
        this.mimeTypesAllowed = new ArrayList<>();
    }

    public FileService(ArrayList<String> mimeTypesAllowed) {
        this.mimeTypesAllowed = mimeTypesAllowed;
    }



    public InputStream getFile(String url) throws Exception {
        String fileName = generateFileName(url);
        File file = new File(Paths.get("").toAbsolutePath().toString() + "/mods/Escendia_GUI_Mod/resources/"+url);

        if(file.exists())
            return new FileInputStream(file);
        else{
            file = new File(Paths.get("").toAbsolutePath().toString() + "/mods/Escendia_GUI_Mod/resources/"+fileName);
            ByteArrayInputStream bais = downloadFileByUrl(url);
            FileOutputStream fos;

            file.createNewFile();

            fos = new FileOutputStream(file);
            int i;

            while ((i = bais.read()) != -1)
                fos.write(i);

            fos.close();

            bais.reset();
            return bais;
        }
    }

    private long getFileLength(String fileUrl) throws Exception {
        URL url = new URL(fileUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        long length = con.getContentLengthLong();

        con.disconnect();

        return length;
    }

    private ByteArrayInputStream downloadFileByUrl(String fileUrl) throws Exception {
        URL url = new URL(fileUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        if(con.getContentLengthLong() <= 3145728 && mimeTypesAllowed.contains(con.getContentType().replace("\"", ""))) {
            InputStream is = con.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream((int) con.getContentLengthLong());
            int i;

            while((i = is.read()) != -1)
                baos.write(i);

            return new ByteArrayInputStream(baos.toByteArray());
        }

        return null;
    }

    private String generateFileName(String url) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(url.getBytes());
        byte array[] = messageDigest.digest();
        String name = "";

        for(int i = 0; i < array.length; i++)
            name += array[i]+"";

        return name;
    }
}
