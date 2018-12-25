package net.escendia.gui.controll;

import net.escendia.gui.EscendiaGUIPlugin;
import net.escendia.gui.model.logger.EscendiaLogger;
import net.escendia.ioc.InversionOfControl;
import net.escendia.ioc.Singleton;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@Singleton
public class FileService {

    private final ArrayList<String> mimeTypesAllowed;
    protected final ArrayList<String> urlLoaded;

    public FileService(){
        this.mimeTypesAllowed = new ArrayList<>();
        this.urlLoaded = new ArrayList<>();
    }

    public FileService(ArrayList<String> mimeTypesAllowed) {
        this.mimeTypesAllowed = mimeTypesAllowed;
        this.urlLoaded = new ArrayList<>();
    }



    public InputStream getFile(String url) throws Exception {
        String path = getConfigDir();


        String fileName = generateFileName(url);
        File file = new File(path+File.separator+url);

        if(file.exists())
            return new FileInputStream(file);
        else{
            file = new File(path+File.separator+fileName);
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



    private File getFileAsFile(String fileName) throws IOException {
        new File(getConfigDir()+ File.separator + fileName).mkdirs();
        File file = new File(getConfigDir()+ File.separator + fileName);
        if(!file.exists())file.createNewFile();
        return file;
    }

    private void writeDataToFile(String fileName, String content) throws IOException {
        File file = getFileAsFile(fileName);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getAbsolutePath(), true), "UTF-8"));
        writer.write(content);
        writer.newLine();
        writer.flush();
        writer.close();
    }

    public void writeStringToFile(String filename, String content){
        try {
            writeDataToFile(filename, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public String getConfigDir() {
        String path = InversionOfControl.get().build(EscendiaGUIPlugin.class).getDefaultConfig().getParent().toString();
        File tmpdir = new File(path + File.separator + InversionOfControl.get().build(EscendiaGUIPlugin.class).getPluginID());
        if(tmpdir.isDirectory() && !tmpdir.exists())tmpdir.mkdirs();
        return tmpdir.getAbsolutePath();
    }

    public void writeFile(String filePath, String textToWrite) {
        Path path = Paths.get(filePath);
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        File toWrite = new File(filePath);

        if(!toWrite.exists()) {
            try {
                Files.createFile(path);
                FileOutputStream os= null;
                try {

                    os = new FileOutputStream(toWrite);
                    BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os));
                    bw.append(textToWrite);
                    bw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
}
