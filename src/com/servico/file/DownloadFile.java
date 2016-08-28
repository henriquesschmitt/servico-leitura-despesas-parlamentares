package com.servico.file;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class DownloadFile {

    public static void downloadAndSaveFile(String stringUrl, String origem) {
        
        System.out.println("LOCALIZANDO DESPESAS NO PORTAL DO SENADO FEDERAL...");
        try {
            URL url = new URL(stringUrl);
            System.out.println("arquivo localizado, realizando download...");
            InputStream is = url.openStream();
            FileOutputStream fos = new FileOutputStream("./arquivos/" + origem);
            int umByte = 0;
            while ((umByte = is.read()) != -1) {
                fos.write(umByte);
            }
            is.close();
            fos.close();
            System.out.println("DOWNLOAD REALIZADO COM SUCESSO!");
        } catch (Exception e) {
            System.out.println("ERRO AO PROCESSAR DOWNLOAD DO ARQUIVO: " + e.getMessage());
        }
    }

}
